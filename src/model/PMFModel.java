package model;

import java.util.List;
import java.util.Observable;

public class PMFModel extends Observable implements IPMFModel {
	private float temperatureInt;
	private float temperatureExt;
	private float temperatureConsigne;
	private float temperatureRosee;
	private float humidity;
	private boolean alertRosee;
	private boolean alertHausse;
	
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
		// TODO Auto-generated method stub
		return null;
	}

}