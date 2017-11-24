package view;

import static org.junit.Assert.*;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

public class ViewTest {

	@Test
	public void testGetButton() {
		View view = new View();
		
		String name = "lessButton";
		Button test = new Button("-1");
		test.setBounds(365, 86, 79, 46);	
		assertEquals(test, view.getButton(name));
		
	}

	@Test
	public void testGetLabel() {
		View view = new View();
		String name = "outTemperatureExt";
		JLabel test = new JLabel("");
		test.setFont(new Font("Tahoma", Font.PLAIN, 20));		
		test.setBackground(Color.BLACK);						
		test.setBounds(509, 32, 100, 101);	
		assertEquals(test, view.getLabel(name));
	}

}
