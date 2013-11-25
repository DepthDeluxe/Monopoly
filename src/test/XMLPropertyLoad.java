package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import monopoly.XMLMonopoly;
import monopoly.Property;

public class XMLPropertyLoad {
	
	private static final double tolerance = 0.125;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void loadXMLProperty() {
		Property[] properties = XMLMonopoly.loadPropertiesFromFile("Properties.xml");
		
		// test the first property
		Property firstProperty = properties[0];
		
		// print out the values
		System.out.println("Property Name: " + firstProperty.getName());
		System.out.println("Price: " + firstProperty.getPrice());
		
		// make sure the name is good
		boolean nameCompare = firstProperty.getName().equals("Park Place");
		assertTrue(nameCompare);
		
		// make sure the values are equal
		assertEquals(firstProperty.getPrice(), 335.0, tolerance);
	}

	@After
	public void tearDown() {
		
	}
}
