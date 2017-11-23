package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
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
	private boolean alerteRosee;
	private boolean alerteChuteTemperature;

	private final int TEMP_MIN = -10;
	private final int TEMP_MAX = 50;

	public Controlleur(IModel model, ICAD cad) {
		this.model = model;
		this.cad = cad;
		
		alerteRosee = false;
		alerteChuteTemperature = false;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		view.getLabel("outTemperatureInt").setText(Float.toString(model.getTemperatureInt()));
		view.getLabel("outTemperatureExt").setText(Float.toString(model.getTemperatureExt()));
		view.getLabel("outHumidity").setText(Float.toString(model.getHumidity()) + "%");

		Collections.rotate(model.getTemperatureRecords(), -1);
		model.getTemperatureRecords().set(model.getTemperatureRecords().size() - 1, model.getTemperatureInt());

		view.updateGraph(model.getTemperatureInt(), model.getTemperatureExt(), model.getHumidity());

		if (Math.abs(model.getTemperatureRecords().get(model.getTemperatureRecords().size() - 1)
				- model.getTemperatureRecords().get(model.getTemperatureRecords().size() - 5)) > 1.5 && !alerteChuteTemperature) {

			System.out.println("Alerte temperature");
		}
		else if (alerteChuteTemperature)
		{
			alerteChuteTemperature = false;
		}

		if (model.getTemperatureInt() <= model.getTemperatureRosee() && !alerteRosee) {

			System.out.println("Alerte rosée");
			alerteRosee = true;
		}
		else if (alerteRosee)
		{
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

		((Observable) model).addObserver(this);

		cad.init();
		new Thread(cad).start();

		view = new View();

		view.getButton("lessButton").addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setTemperatureConsigne(checkConsigne(model.getTemperatureConsigne() - 1));
				view.getLabel("outTemperatureConsigne").setText(Float.toString(model.getTemperatureConsigne()));
			}
		});

		view.getButton("plusButton").addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setTemperatureConsigne(checkConsigne(model.getTemperatureConsigne() + 1));
				view.getLabel("outTemperatureConsigne").setText(Float.toString(model.getTemperatureConsigne()));
			}
		});
		
		view.getLabel("outTemperatureConsigne").setText(Float.toString(model.getTemperatureConsigne()));
		view.getLabel("outTemperatureInt").setText("-");
		view.getLabel("outTemperatureExt").setText("-");
		view.getLabel("outHumidity").setText("- %");

		view.getFrame().setVisible(true);
		
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
