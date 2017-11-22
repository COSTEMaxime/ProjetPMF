package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Button;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import contract.IView;

public class View implements IView {

	@Override
	public JButton getButton(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JLabel getLabel(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public void alerteRosee (boolean Up){ if (Up == false){
	 * iconAlertUp.setVisible(false); } else { break; }
	 * 
	 */

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		TextField outTemperatureInt = new TextField();
		outTemperatureInt.setBounds(42, 32, 100, 100);
		frame.getContentPane().add(outTemperatureInt);

		TextField outTemperatureConsigne = new TextField();
		outTemperatureConsigne.setBounds(250, 32, 97, 100);
		frame.getContentPane().add(outTemperatureConsigne);

		TextField outTemperatureExt = new TextField();
		outTemperatureExt.setBounds(509, 32, 100, 100);
		frame.getContentPane().add(outTemperatureExt);

		TextField outHumidity = new TextField();
		outHumidity.setText("\r\n");
		outHumidity.setBounds(42, 416, 100, 100);
		frame.getContentPane().add(outHumidity);

		Button addDegres = new Button("-1");
		addDegres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setTemperatureConsigne(getTemperatureConsigne()+-1);
			}
		});
		addDegres.setBounds(365, 32, 79, 46);
		frame.getContentPane().add(addDegres);

		Button removeDegres = new Button("+1");
		removeDegres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setTemperatureConsigne(getTemperatureConsigne()+1);
			}
		});
		removeDegres.setBounds(365, 86, 79, 46);
		frame.getContentPane().add(removeDegres);

		JLabel lblT = new JLabel("T\u00B0 Ext");
		lblT.setLabelFor(outTemperatureExt);
		lblT.setBounds(69, 138, 56, 16);
		frame.getContentPane().add(lblT);

		JLabel lblTInt = new JLabel("T\u00B0 Int");
		lblTInt.setLabelFor(outTemperatureInt);
		lblTInt.setBounds(546, 138, 56, 16);
		frame.getContentPane().add(lblTInt);

		JLabel lblTConsigne = new JLabel("T\u00B0 Consigne");
		lblTConsigne.setLabelFor(lblTConsigne);
		lblTConsigne.setBounds(260, 138, 78, 16);
		frame.getContentPane().add(lblTConsigne);

		JLabel lblHygromtrie = new JLabel("Hygrom\u00E9trie");
		lblHygromtrie.setBounds(52, 522, 79, 16);
		frame.getContentPane().add(lblHygromtrie);

		JLabel iconAlertUp = new JLabel("");
		iconAlertUp.setBackground(Color.WHITE);
		iconAlertUp.setForeground(new Color(255, 255, 255));
		iconAlertUp.setIcon(new ImageIcon("C:\\Users\\Pierre GILLY\\workspace\\Mini-frigo\\src\\Vue\\pouce_up.png"));
		iconAlertUp.setBounds(521, 393, 118, 123);
		frame.getContentPane().add(iconAlertUp);

		JLabel iconAlertDown = new JLabel("");
		iconAlertDown.setForeground(new Color(255, 255, 255));
		iconAlertDown
				.setIcon(new ImageIcon("C:\\Users\\Pierre GILLY\\workspace\\Mini-frigo\\src\\Vue\\pouce_down.jpg"));
		iconAlertDown.setBounds(521, 393, 118, 123);
		frame.getContentPane().add(iconAlertDown);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\Pierre GILLY\\workspace\\Mini-frigo\\src\\Vue\\Free-Desktop-Solid-Color-HD-Wallpapers.jpg"));
		lblNewLabel.setBounds(0, 0, 682, 553);
		frame.getContentPane().add(lblNewLabel);

	}
}

// fen.setVisible(false);