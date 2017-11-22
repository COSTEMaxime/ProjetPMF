package contract;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface IView {

	JButton getButton(String name);

	JLabel getLabel(String name);
}
