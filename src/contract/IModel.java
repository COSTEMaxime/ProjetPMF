package contract;

import java.util.List;

public interface IModel {

	float getTemperatureConsigne();

	float getTemperatureInt();

	float getTemperatureExt();

	float getTemperatureRosee();

	float getHumidity();

	List<Float> getTemperatureRecords();

	void setTemperatureConsigne(float consigne);

	void updateData(float temperatureInt, float temperatureExt, float himidity);

}
