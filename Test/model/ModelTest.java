package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import contract.IModel;

public class ModelTest {

	@Test
	public void testSetTemperatureConsigne() {
		Model nouveauModel = new Model(40);
		float test = 50;
		nouveauModel.setTemperatureConsigne(50);			
		assertEquals(50, nouveauModel.getTemperatureConsigne(), 0f);	}

	
	@Test
	public void testSetTemperatureRosee() {
		float test = 50;
		setTemperatureRosee(50);			
		assertEquals(50, getTemperatureRosee(), 0f);	}
	}

	@Test
	public void testSetAlertRosee() {
		boolean test = true;
		setAlertRosee(true);
		assertArrayEquals(test, isAlertRosee());
	}


	@Test
	public void testSetAlertHausse() {
		boolean test = true;
		setAlertHausse(true);
		assertArrayEquals(test, isAlertHausse());
	}

	@Test
	public void testUpdateData() {
		float Tint = 1;
		float Text = 2;
		float Humidity = 3;
		updateData(Tint, Text, Humidity);
		assertEquals(Tint, getTemperatureInt(), 0f);
		assertEquals(Text, getTemperatureExt(), 0f);
		assertEquals(Humidity, getHumidity(), 0f);

		
	}


}
