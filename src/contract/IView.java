package contract;

import java.awt.Button;
import java.awt.Frame;

import javax.swing.JLabel;

public interface IView {

	Button getButton(String name);

	JLabel getLabel(String name);
	
	void updateGraph(float temperatureInt, float temperatureExt, float humidity);
	
	void alerteRosee (boolean Statement);
	
	Frame getFrame();
}
	
