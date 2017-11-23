package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import contract.IView;

public class View implements IView {
	
	private JFrame frame;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	private Button lessButton;
	private Button plusButton;
	private JLabel lblTInt;
	private JLabel lblTExt;
	private JLabel lblTConsigne;
	private JLabel lblHygromtrie;
	private JLabel iconAlertUp;
	public int ordonnees = 0 ;
	
	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}

	
	
	@Override
	public Button getButton(String name) {
		if (name.equals("lessButton") ){
			return lessButton;
		}
		else if (name.equals("plusButton")){
			return plusButton;
		}
		return null;
	}

	@Override
	public JLabel getLabel(String name) {
		if (name.equals("outTemperatureInt") ){
			return lblTInt;
		}
		if (name.equals("outTemperatureExt")){
			return lblTExt;
		}
		if (name.equals("outTemperatureConsigne") ){
			return lblTConsigne;
		}
		if (name.equals("outHumidty")){
			return lblHygromtrie;
		}
		return null;
	}
	
	public void updateGraph(float temperatureInt, float temperatureExt, float humidity) {
		getDataset().addValue(temperatureInt, "TemperatureInt", String.valueOf(ordonnees));
		getDataset().addValue(temperatureExt, "temperatureExt", String.valueOf(ordonnees));
		getDataset().addValue(humidity, "Humidity", String.valueOf(ordonnees));
		ordonnees++;
		
		if(ordonnees > 100) {
			getDataset().removeColumn(0);
		}
	}


	public void alerteRosee (boolean Statement){
		if (Statement == false){
			iconAlertUp.setVisible(false);
			}
		else if (Statement == true){ 
			iconAlertUp.setVisible(true); 
		}
		return;
	}

	@Override
	public Frame getFrame() {
		return frame;
	}
	

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

		lessButton = new Button("-1");
		lessButton.setBounds(365, 32, 79, 46);
		frame.getContentPane().add(lessButton);

		plusButton = new Button("+1");
		plusButton.setBounds(365, 86, 79, 46);
		frame.getContentPane().add(plusButton);

		lblTExt = new JLabel("T\u00B0 Ext");
		lblTExt.setLabelFor(outTemperatureExt);
		lblTExt.setBounds(69, 138, 56, 16);
		frame.getContentPane().add(lblTExt);

		lblTInt = new JLabel("T\u00B0 Int");
		lblTInt.setLabelFor(outTemperatureInt);
		lblTInt.setBounds(546, 138, 56, 16);
		frame.getContentPane().add(lblTInt);

		lblTConsigne = new JLabel("T\u00B0 Consigne");
		lblTConsigne.setLabelFor(lblTConsigne);
		lblTConsigne.setBounds(260, 138, 78, 16);
		frame.getContentPane().add(lblTConsigne);

		lblHygromtrie = new JLabel("Hygrom\u00E9trie");
		lblHygromtrie.setBounds(52, 522, 79, 16);
		frame.getContentPane().add(lblHygromtrie);

		iconAlertUp = new JLabel("");
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
		
		JPanel JpanelGraph = new JPanel();
		JpanelGraph.setBounds(42, 168, 597, 212);
		frame.getContentPane().add(JpanelGraph);
		
		dataset = new DefaultCategoryDataset();
		chart = ChartFactory.createLineChart("Graphique des données", "Temps", "Temperature (°C)", dataset);
		chartPanel = new ChartPanel(chart);
		//chartPanel.setPreferredSize(new Dimension(800, 400));
		JpanelGraph.add(chartPanel, BorderLayout.CENTER);
	}

	

	
}

