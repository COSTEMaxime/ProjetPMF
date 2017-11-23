package cad;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import contract.ICAD;
import contract.IModel;

/**
 * Classe qui g�re l'acc�s aux donn�es, elle s'occupe de la communication avec
 * l'Arduino sur le port s�rie
 * 
 * @author Coste Maxime
 *
 */

public class CAD implements ICAD {

	// port s�rie de l'Arduino
	private SerialPort serialPort;

	// flux d'�criture/lecture du port s�rie
	private InputStream in;
	private OutputStream out;
	private IModel model;

	// temps minimum entre deuyx rafraichissements (en ms)
	private final int refreshTime = 2000;

	public CAD(IModel model) {

		this.model = model;
	}

	/**
	 * Permet d'initialiser la communication entre le java et l'Arduino
	 * 
	 */
	@Override
	public void init() {

		try {

			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(getPortName());
			CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

			serialPort = (SerialPort) commPort;
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			in = serialPort.getInputStream();
			out = serialPort.getOutputStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));

			// actionListener sur le port s�rie
			serialPort.addEventListener(new SerialPortEventListener() {

				@Override
				public void serialEvent(SerialPortEvent arg0) {
					if (arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
						try {
							// on attends que la data soit disponible sur le
							// port s�rie, puis on la parse dans le mod�le
							while (!input.ready())
								;
							parseDataIntoModel(input.readLine());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			serialPort.notifyOnDataAvailable(true);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * V�rifie si la chaine re�ue est valide, et va modifier le mod�le
	 * 
	 * @param input
	 *            la chaine de caract�re � parser
	 * 
	 * @throws Exception
	 *             si la chaine n'est pas valide
	 */
	private void parseDataIntoModel(String input) throws Exception {

		String[] values = input.split(";");
		if (values.length != 3) {
			throw new Exception("Trame re�ue non valide : " + input);
		}

		model.updateData(Float.parseFloat(values[0]), Float.parseFloat(values[1]), Float.parseFloat(values[2]));
	}

	/**
	 * Retourne automatiquement le nom du port s�rie branch�
	 * 
	 * @return string the name of the serial port
	 * 
	 * @throws NoSuchPortException
	 *             si aucun port s�rie n'a �t� trouv�
	 */
	private String getPortName() throws NoSuchPortException {

		CommPortIdentifier serialPortID;
		Enumeration<?> enumComm = CommPortIdentifier.getPortIdentifiers();
		// on teste chaque port branch� au PC, et on retourne le nom du premier
		// port de type s�rie trouv�
		while (enumComm.hasMoreElements()) {

			serialPortID = (CommPortIdentifier) enumComm.nextElement();
			if (serialPortID.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				return serialPortID.getName();
			}

		}
		// exception pour indiquer qu'aucun port s�rie n'a �t� trouv�
		throw new NoSuchPortException();
	}

	/**
	 * Ecrit une trame sur le port s�rie
	 * 
	 * @param trame
	 *            la trame � �crire sur le port s�rie
	 * 
	 * @return boolean success
	 */
	@Override
	public boolean write(String trame) {

		try {
			out.write(trame.getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Lib�re le port s�rie
	 * 
	 */
	@Override
	public void close() {
		try {
			serialPort.notifyOnDataAvailable(false);
			in.close();
			out.close();
			serialPort.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Envoie une trame sp�ciale qui va demander � l'Arduino d'envoyer de
	 * nouvelles donn�es
	 * 
	 * @return boolean success
	 */
	@Override
	public boolean updateData() {
		return write("update");
	}

	/**
	 * Boucle infinie qui envoie un signal d'update sur le port s�rie
	 * 
	 */
	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(refreshTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			updateData();
		}
	}
}
