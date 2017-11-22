import javax.swing.SwingUtilities;

import cad.CAD;
import contract.ICAD;
import contract.IControlleur;
import contract.IModel;
import controlleur.Controlleur;
import model.PMFModel;

public class Main {

	public static void main(String[] args) {
		
		IModel model = new PMFModel(15);
		ICAD cad = new CAD(model);
		IControlleur ctrl = new Controlleur(model, cad);
		
		SwingUtilities.invokeLater(ctrl);
	}
}
