import javax.swing.SwingUtilities;

import cad.CAD;
import contract.ICAD;
import contract.IControlleur;
import contract.IModel;
import controlleur.Controlleur;
import model.Model;

public class Main {

	public static void main(String[] args) {

		IModel model = new Model(15);
		ICAD cad = new CAD(model);
		IControlleur ctrl = new Controlleur(model, cad);

		//la vue est le thread parent du controlleur, si on ferme la vue, le thread du controlleur se ferme automatiquement
		SwingUtilities.invokeLater(ctrl);
	}
}
