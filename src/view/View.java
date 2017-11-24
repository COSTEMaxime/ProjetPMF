package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

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
	private JLabel outTemperatureInt;
	private JLabel outTemperatureExt;
	private JLabel outTemperatureConsigne;
	private JLabel outHumidity;
	private JLabel iconAlertUp;
	public int abscisses = 0;

	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}
	
	/*
	 * Fonction qui prend en entr�e un nom de Button et retourne le Button correspondant
	 */
	@Override
	public Button getButton(String name) {
		if (name.equals("lessButton")) {
			return lessButton;
		} else if (name.equals("plusButton")) {
			return plusButton;
		}
		return null;
	}
	
	/*
	 * Fonction qui prend en entr�e un nom de JLabel et retourne le JLabel correspondant
	 * Elle permet au contr�leur de r�cuper le bon JLabel et d'afficher la temp�rature sur la vue
	 */
	@Override
	public JLabel getLabel(String name) {
		if (name.equals("outTemperatureInt")) {
			return outTemperatureInt;
		}
		if (name.equals("outTemperatureExt")) {
			return outTemperatureExt;
		}
		if (name.equals("outTemperatureConsigne")) {
			return outTemperatureConsigne;
		}
		if (name.equals("outHumidity")) {
			return outHumidity;
		}
		return null;
	}

	/*
	 * Permet au contr�leur de placer les donn�es dans le graphique
	 * @param temperatureInt Correspond � la temp�rature ext�rieure
	 * @param temperatureExt Correspond � la temp�rature int�rieure
	 * @param humidity 		 Correspond � la l'hygrom�trie pr�sente dans le frigo
	 */
	public void updateGraph(float temperatureInt, float temperatureExt, float humidity) {
		getDataset().addValue(temperatureInt, "TemperatureInt", String.valueOf(abscisses));	// Ajoute au dataset la valeur de la temp�rature, le nom de la courbe et abscisse
		getDataset().addValue(temperatureExt, "temperatureExt", String.valueOf(abscisses)); // Ajoute au dataset la valeur de la temp�rature, le nom de la courbe et abscisse
		getDataset().addValue(humidity, "Humidity", String.valueOf(abscisses));				// Ajoute au dataset la valeur de la temp�rature, le nom de la courbe et abscisse
		abscisses++;

		if (abscisses > 100) {				// Permet de se d�placer sur le graphique et d'effacer les premi�res valeures quand l'abscisse est trop grande
			getDataset().removeColumn(0);
		}
	}
	/*
	 * Permet au contr�leur de lever l'alerte de ros�e
	 */
	public void alerteRosee(boolean Statement) {
		if (Statement == false) {											// Dans le cas ou il y a une alerte
			iconAlertUp.setVisible(false);									// L'icone du pouce vers le haut s'efface et montre l'icone en dessous (pouce vers le bas)
		} else if (Statement == true) {										// Dans le cas ou il n'y a pas d'alerte
			iconAlertUp.setVisible(true);									// L'icone du pouce vers le haut reste affich�e
		}
		return;
	}
	
	/*
	 * Permet au contr�leur d'acc�der � la frame
	 */
	@Override
	public Frame getFrame() {
		return frame;
	}

	/*
	 * Lance l'application.
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

	/*
	 * Cr�ation de l'application.
	 */
	public View() {
		initialize();
	}

	/*
	 * Initialise le contenu de la frame
	 */
	private void initialize() {
		
		frame = new JFrame();												// Instanciation de la fen�tre (Jframe)
		frame.setBounds(100, 100, 700, 600);								// Cr�ation des dimensions
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// Permet de fermer la fen�tre si on clique sur la croix
		frame.getContentPane().setLayout(null);								// D�finit le Layout -> Dans ce cas l� "null" 

		outTemperatureInt = new JLabel("");									// Instanciation du nouveau JLabel
		outTemperatureInt.setFont(new Font("Tahoma", Font.PLAIN, 20));		// D�finit la police et la taille
		outTemperatureInt.setBackground(Color.BLACK);						// D�finit le background
		outTemperatureInt.setBounds(43, 31, 100, 101);						// Cr�ation des dimensions
		frame.getContentPane().add(outTemperatureInt);						// Ajout du JLabel sur la fen�tre

		outTemperatureConsigne = new JLabel("");							// Instanciation du nouveau JLabel
		outTemperatureConsigne.setFont(new Font("Tahoma", Font.PLAIN, 20));	// D�finit la police et la taille
		outTemperatureConsigne.setBackground(Color.BLACK);					// D�finit le background
		outTemperatureConsigne.setBounds(247, 31, 100, 101);				// Cr�ation des dimensions
		frame.getContentPane().add(outTemperatureConsigne);					// Ajout du JLabel sur la fen�tre

		outTemperatureExt = new JLabel("");									// Instanciation du nouveau JLabel
		outTemperatureExt.setFont(new Font("Tahoma", Font.PLAIN, 20));		// D�finit la police et la taille
		outTemperatureExt.setBackground(Color.BLACK);						// D�finit le background
		outTemperatureExt.setBounds(509, 32, 100, 101);						// Cr�ation des dimensions
		frame.getContentPane().add(outTemperatureExt);						// Ajout du JLabel sur la fen�tre

		lessButton = new Button("-1");										// Instanciation du nouveau Button
		lessButton.setBounds(365, 86, 79, 46);								// Cr�ation des dimensions
		frame.getContentPane().add(lessButton);								// Ajout du Button sur la fen�tre

		outHumidity = new JLabel("");										// Instanciation du nouveau JLabel
		outHumidity.setFont(new Font("Tahoma", Font.PLAIN, 20));			// D�finit la police et la taille
		outHumidity.setBackground(Color.BLACK);								// D�finit le background
		outHumidity.setBounds(42, 415, 100, 101);							// Cr�ation des dimensions
		frame.getContentPane().add(outHumidity);							// Ajout du JLabel sur la fen�tre

		plusButton = new Button("+1");										// Instanciation du nouveau Button
		plusButton.setBounds(365, 34, 79, 46);								// Cr�ation des dimensions	
		frame.getContentPane().add(plusButton);								// Ajout du Button sur la fen�tre

		JLabel lblTExt = new JLabel("T\u00B0 Ext");							// Instanciation du nouveau JLabel
		lblTExt.setBounds(69, 138, 56, 16);									// Cr�ation des dimension
		frame.getContentPane().add(lblTExt);								// Ajout du JLabel sur la fen�tre

		JLabel lblTInt = new JLabel("T\u00B0 Int");							// Instanciation du nouveau JLabel
		lblTInt.setBounds(546, 138, 56, 16);								// Cr�ation des dimension
		frame.getContentPane().add(lblTInt);								// Ajout du JLabel sur la fen�tre

		JLabel lblTConsigne = new JLabel("T\u00B0 Consigne");				// Instanciation du nouveau JLabel
		lblTConsigne.setBounds(260, 138, 78, 16);							// Cr�ation des dimension
		frame.getContentPane().add(lblTConsigne);							// Ajout du JLabel sur la fen�tre

		JLabel lblHygromtrie = new JLabel("Hygrom\u00E9trie");				// Instanciation du nouveau JLabel
		lblHygromtrie.setBounds(52, 522, 79, 16);							// Cr�ation des dimension
		frame.getContentPane().add(lblHygromtrie);							// Ajout du JLabel sur la fen�tre

		iconAlertUp = new JLabel("");							         	// Instanciation du nouveau JLabel
		iconAlertUp.setForeground(new Color(255, 255, 255));				// D�finit le foreground en blanc		
		iconAlertUp.setIcon(new ImageIcon("src/View/pouce_up.png"));		// D�finit l'image d'alerte -> Dans ce cas l�, le pouce vers le haut (donc tout va bien)
		iconAlertUp.setBounds(521, 393, 118, 123);							// Cr�ation des dimension
		frame.getContentPane().add(iconAlertUp);							// Ajout du JLabel sur la fen�tre

		JLabel iconAlertDown = new JLabel("");								// Instanciation du nouveau JLabel
		iconAlertDown.setForeground(new Color(255, 255, 255));				// D�finit le foreground en blanc
		iconAlertDown.setIcon(new ImageIcon("src/View/pouce_down.jpg"));	// D�finit l'image d'alerte -> Dans ce cas l�, le pouce vers le bas (donc il y a alerte)
		iconAlertDown.setBounds(521, 393, 118, 123);						// Cr�ation des dimension
		frame.getContentPane().add(iconAlertDown);							// Ajout du JLabel sur la fen�tre

		JPanel JpanelGraph = new JPanel();									// Instanciation du nouveau JPanel
		JpanelGraph.setBounds(42, 168, 597, 212);							// Cr�ation des dimension
		frame.getContentPane().add(JpanelGraph);							// Ajout du JPanel sur la fen�tre
		dataset = new DefaultCategoryDataset();								// Instanciation du dataset 
		chart = ChartFactory.createLineChart("Graphique des donn�es", "Temps", "Temperature (�C)", dataset); // Instantiation du graphique 
		chartPanel = new ChartPanel(chart);									// Instanciation d'un conteneur (panel) avec le graphique � l'int�rieur
		// chartPanel.setPreferredSize(new Dimension(800, 400));	
		JpanelGraph.add(chartPanel, BorderLayout.CENTER);					// Ajout du panel qui contient le graphique, dans le JPanelGraph

		JLabel backgroundLabel = new JLabel("");							// Instanciation du nouveau JLabel
		backgroundLabel.setIcon(new ImageIcon(View.class.getResource("/view/Free-Desktop-Solid-Color-HD-Wallpapers.jpg")));		// D�finit l'image de fond 
		backgroundLabel.setBounds(0, 0, 682, 553);							// Cr�ation des dimension
		frame.getContentPane().add(backgroundLabel);						// Ajout du JLabel sur la fen�tre
	}
}
