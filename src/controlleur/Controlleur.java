package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import contract.ICAD;
import contract.IControlleur;
import contract.IModel;
import contract.IView;
import view.View;

public class Controlleur implements IControlleur, Observer {

	private IModel model;
	private IView view;
	private ICAD cad;

	private final int TEMP_MIN = -10;
	private final int TEMP_MAX = 50;

	public Controlleur(IModel model, ICAD cad) {
		this.model = model;
		this.cad = cad;

	}

	@Override
	public void update(Observable arg0, Object arg1) {

		String trame = "";
		// check si alimentation nécessaire
		// check si chute temperature
		// check si rosee

		cad.write(trame);
	}

	@Override
	public void run() {

		((Observable) model).addObserver(this);

		cad.init();
		new Thread(cad).start();

		view = new View();

		/*
		 * view.getButton("lessButton").addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * model.setTemperatureConsigne(checkConsigne(model.
		 * getTemperatureConsigne() - 1));
		 * view.getLabel("lessLabel").setText(Float.toString(model.
		 * getTemperatureConsigne())); } });
		 * 
		 * view.getButton("plusButton").addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * model.setTemperatureConsigne(checkConsigne(model.
		 * getTemperatureConsigne() + 1));
		 * view.getLabel("plusLabel").setText(Float.toString(model.
		 * getTemperatureConsigne())); } });
		 */
	}

	private float checkConsigne(float consigne) {

		if (consigne < TEMP_MIN) {
			return TEMP_MIN;
		} else if (consigne > TEMP_MAX) {
			return TEMP_MAX;
		} else
			return consigne;
	}
}
