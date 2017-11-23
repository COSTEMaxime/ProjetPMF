package controlleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import contract.ICAD;
import contract.IControlleur;
import contract.IModel;
import contract.IView;
import view.View;

/**
 * Classe qui s'occupe de traiter les actions de l'utilisateur, et de modifier
 * les données et la vue
 * 
 * @author Coste Maxime
 *
 */

public class Controlleur implements IControlleur, Observer {

	private IModel model;
	private IView view;
	private ICAD cad;
	private boolean alerteRosee;
	private boolean alerteChuteTemperature;

	// Températures minimales et maximales de consigne
	private final int TEMP_MIN = -10;
	private final int TEMP_MAX = 50;

	public Controlleur(IModel model, ICAD cad) {
		this.model = model;
		this.cad = cad;

		alerteRosee = false;
		alerteChuteTemperature = false;
	}

	/**
	 * Méthode déclenchée par l'update des données dans le modèle
	 * 
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		// modifie le texte des label
		view.getLabel("outTemperatureInt").setText(Float.toString(model.getTemperatureInt()));
		view.getLabel("outTemperatureExt").setText(Float.toString(model.getTemperatureExt()));
		view.getLabel("outHumidity").setText(Float.toString(model.getHumidity()) + "%");

		// update le graph
		Collections.rotate(model.getTemperatureRecords(), -1);
		model.getTemperatureRecords().set(model.getTemperatureRecords().size() - 1, model.getTemperatureInt());

		view.updateGraph(model.getTemperatureInt(), model.getTemperatureExt(), model.getHumidity());

		if (Math.abs(model.getTemperatureRecords().get(model.getTemperatureRecords().size() - 1)
				- model.getTemperatureRecords().get(model.getTemperatureRecords().size() - 5)) > 1.5
				&& !alerteChuteTemperature) {

			System.out.println("Alerte temperature");
		} else if (alerteChuteTemperature) {
			alerteChuteTemperature = false;
		}

		if (model.getTemperatureInt() <= model.getTemperatureRosee() && !alerteRosee) {

			System.out.println("Alerte rosée");
			alerteRosee = true;
		} else if (alerteRosee) {
			alerteRosee = false;
		}

		String trame;
		if (Math.abs(model.getTemperatureInt() - model.getTemperatureConsigne()) > 0.2) {
			trame = "1";
		} else {
			trame = "0";
		}

		cad.write(trame);
	}

	@Override
	public void run() {

		// le controlleur observe le modèle
		((Observable) model).addObserver(this);

		// initialisation de la cad, puis on la met dans un thread à part
		cad.init();
		new Thread(cad).start();

		view = new View();

		// actionListener sur le bouton "-", il update la valeur de la consigne
		// dans le modèle
		view.getButton("lessButton").addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setTemperatureConsigne(checkConsigne(model.getTemperatureConsigne() - 1));
				view.getLabel("outTemperatureConsigne").setText(Float.toString(model.getTemperatureConsigne()));
			}
		});

		// actionListener sur le bouton "+", il update la valeur de la consigne
		// dans le modèle
		view.getButton("plusButton").addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setTemperatureConsigne(checkConsigne(model.getTemperatureConsigne() + 1));
				view.getLabel("outTemperatureConsigne").setText(Float.toString(model.getTemperatureConsigne()));
			}
		});

		// appelle la fonction close() de la CAD lorqu'on ferme la fenêtre
		view.getFrame().addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent windowEvent) {
				cad.close();
			}
		});

		// texte de base dans les Label
		view.getLabel("outTemperatureConsigne").setText(Float.toString(model.getTemperatureConsigne()));
		view.getLabel("outTemperatureInt").setText("-");
		view.getLabel("outTemperatureExt").setText("-");
		view.getLabel("outHumidity").setText("- %");

		// affichage de la fenêtre
		view.getFrame().setVisible(true);

	}

	/**
	 * Check si la valeur de consigne est n'est pas trop élevée/trop faible
	 * 
	 * @param consigne
	 *            la valeur à évaluer
	 * @return float la nouvelle consigne
	 */
	private float checkConsigne(float consigne) {

		if (consigne < TEMP_MIN) {
			return TEMP_MIN;
		} else if (consigne > TEMP_MAX) {
			return TEMP_MAX;
		} else
			return consigne;
	}
}
