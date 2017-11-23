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

public class CAD implements ICAD {

	private SerialPort serialPort;
	private InputStream in;
	private OutputStream out;
	private IModel model;

	private final int refreshTime = 2000;

	public CAD(IModel model) {

		this.model = model;
	}

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

			serialPort.addEventListener(new SerialPortEventListener() {

				@Override
				public void serialEvent(SerialPortEvent arg0) {
					if (arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
						try {
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

	private void parseDataIntoModel(String input) throws Exception {

		String[] values = input.split(";");
		if (values.length != 3) {
			throw new Exception("Trame reçue non valide : " + input);
		}

		model.updateData(Float.parseFloat(values[0]), Float.parseFloat(values[1]), Float.parseFloat(values[2]));
	}

	private String getPortName() throws NoSuchPortException {

		CommPortIdentifier serialPortID;
		Enumeration<?> enumComm = CommPortIdentifier.getPortIdentifiers();
		while (enumComm.hasMoreElements()) {

			serialPortID = (CommPortIdentifier) enumComm.nextElement();
			if (serialPortID.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				return serialPortID.getName();
			}

		}
		throw new NoSuchPortException();
	}

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

	@Override
	public boolean updateData() {
		return write("update");
	}

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
