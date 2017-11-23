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
		for (i=0; i<30; i++){
			temperatureRecords.add(0f); ;
		}
	}

	public float getTemperatureInt() {
		return temperatureInt;
	}

	public float getTemperatureExt() {
		return temperatureExt;
	}

	public float getTemperatureConsigne() {
		return temperatureConsigne;
	}

	public void setTemperatureConsigne(float temperatureConsigne) {
		this.temperatureConsigne = temperatureConsigne;
	}

	public float getTemperatureRosee() {
		return temperatureRosee;
	}

	public void setTemperatureRosee(float temperatureRosee) {
		this.temperatureRosee = temperatureRosee;
	}

	public float getHumidity() {
		return humidity;
	}

	public boolean isAlertRosee() {
		return alertRosee;
	}

	public void setAlertRosee(boolean alertRosee) {
		this.alertRosee = alertRosee;
	}

	public boolean isAlertHausse() {
		return alertHausse;
	}

	public void setAlertHausse(boolean alertHausse) {
		this.alertHausse = alertHausse;
	}

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
