package model;

import java.util.List;
import java.util.Observable;
import contract.IModel;

public class Model extends Observable implements IModel {

	@Override
	public float getConsigne() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTemperatureInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTemperatureExt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTemperatureRosee() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHumidity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Float> getTemperatureRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConsigne(float consigne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNewData(float temperatureInt, float temperatureExt, float himidity) {
		// TODO Auto-generated method stub
		
	}

}
