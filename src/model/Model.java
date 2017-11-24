package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import contract.IModel;

public class Model extends Observable implements IModel {
	
	private float temperatureInt;
	private float temperatureExt;
	private float temperatureConsigne;
	private float temperatureRosee;
	private float humidity;
	private boolean alertRosee;
	private boolean alertHausse;
	List<Float> temperatureRecords;
	
	
	
	public Model(float consigne) {
		int i = 0;					
		setTemperatureConsigne(consigne);
		temperatureRecords = new ArrayList<Float>();
		for (i=0; i<30; i++){					// Permet de mettre les 30 premieres cases de la liste � 0
			temperatureRecords.add(0f); ;		// Le 0f signifie 0.00 -> C'est un float
		}
	}

	public float getTemperatureInt() {		// Getter de la temp�rature int�rieure du frigo
		return temperatureInt;
	}

	public float getTemperatureExt() {		// Getter de la temp�rature ext�rieure du frigo
		return temperatureExt;
	}

	public float getTemperatureConsigne() {	// Getter de la temp�rature de consigne
		return temperatureConsigne;
	}

	public void setTemperatureConsigne(float temperatureConsigne) { 	// Setter de la temp�rature de consigne
		this.temperatureConsigne = temperatureConsigne;
	}

	public float getTemperatureRosee() {	// Getter de la temp�rature de ros�e
		return temperatureRosee;
	}

	public void setTemperatureRosee(float temperatureRosee) { 	// Setter de la temp�rature int�rieure du frigo
		this.temperatureRosee = temperatureRosee;
	}

	public float getHumidity() {		// Getter de l'hygrom�trie du frigo
		return humidity;
	}

	public boolean isAlertRosee() {		// Getter de l'alerte de ros�e
		return alertRosee;
	}

	public void setAlertRosee(boolean alertRosee) {		// Setter de l'alerte de ros�e
		this.alertRosee = alertRosee;
	}

	public boolean isAlertHausse() {			// Non utilis� dans la vue, peut �tre impl�ment� lors de l'am�lioration du programme
		return alertHausse;
	}

	public void setAlertHausse(boolean alertHausse) { // Non utilis� dans la vue, peut �tre impl�ment� lors de l'am�lioration du programme
		this.alertHausse = alertHausse;
	}

	
	/* 
	 * Le updateData permet au contr�leur de passer � la vue toutes les informations � afficher.
	 * @param temperatureInt Correspond � la temp�rature ext�rieure
	 * @param temperatureExt Correspond � la temp�rature int�rieure
	 * @param humidity 		 Correspond � la l'hygrom�trie pr�sente dans le frigo
	*/
	@Override
	public void updateData(float temperatureInt, float temperatureExt, float humidity) {
		this.temperatureInt = temperatureInt;
		this.temperatureExt = temperatureExt;
		this.humidity = humidity;
		setChanged();
		notifyObservers();
	}

	@Override
	public List<Float> getTemperatureRecords() {
		return temperatureRecords;
	}
}
