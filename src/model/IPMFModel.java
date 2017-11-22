package Modèle;

public interface IPMFModel {

	public float getTemperatureInt ();
	public float getTemperatureExt ();
	public float getTemperatureConsigne ();
	public float getTemperatureRosee ();
	public float getHumidity ();
	public void updateData (float temperatureInt, float temperatureExt, float humidity);
}
