package contract;

import java.util.List;

public interface IModel {
	
	float getConsigne();
	float getTemperatureInt();
	float getTemperatureExt();
	float getTemperatureRosee();
	float getHumidity();
	List<Float> getTemperatureRecords();
	
	void setConsigne(float consigne);
	void setNewData(float temperatureInt, float temperatureExt, float himidity);

}
