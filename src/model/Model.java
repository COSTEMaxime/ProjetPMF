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
		for (i=0; i<30; i++){					// Permet de mettre les 30 premieres cases de la liste à 0
			temperatureRecords.add(0f); ;		// Le 0f signifie 0.00 -> C'est un float
		}
	}

	public float getTemperatureInt() {		// Getter de la température intérieure du frigo
		return temperatureInt;
	}

	public float getTemperatureExt() {		// Getter de la température extérieure du frigo
		return temperatureExt;
	}

	public float getTemperatureConsigne() {	// Getter de la température de consigne
		return temperatureConsigne;
	}

	public void setTemperatureConsigne(float temperatureConsigne) { 	// Setter de la température de consigne
		this.temperatureConsigne = temperatureConsigne;
	}

	public float getTemperatureRosee() {	// Getter de la température de rosée
		return temperatureRosee;
	}

	public void setTemperatureRosee(float temperatureRosee) { 	// Setter de la température intérieure du frigo
		this.temperatureRosee = temperatureRosee;
	}

	public float getHumidity() {		// Getter de l'hygrométrie du frigo
		return humidity;
	}

	public boolean isAlertRosee() {		// Getter de l'alerte de rosée
		return alertRosee;
	}

	public void setAlertRosee(boolean alertRosee) {		// Setter de l'alerte de rosée
		this.alertRosee = alertRosee;
	}

	public boolean isAlertHausse() {			// Non utilisé dans la vue, peut être implémenté lors de l'amélioration du programme
		return alertHausse;
	}

	public void setAlertHausse(boolean alertHausse) { // Non utilisé dans la vue, peut être implémenté lors de l'amélioration du programme
		this.alertHausse = alertHausse;
	}

	
	/* 
	 * Le updateData permet au contrôleur de passer à la vue toutes les informations à afficher.
	 * @param temperatureInt Correspond à la température extérieure
	 * @param temperatureExt Correspond à la température intérieure
	 * @param humidity 		 Correspond à la l'hygrométrie présente dans le frigo
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
