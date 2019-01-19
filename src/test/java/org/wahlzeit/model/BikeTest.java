package org.wahlzeit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BikeTest {
	private Bike regularBike;
	
	@Before
	public void initRegularBike() {
		regularBike = new Bike(BikeType.getInstance("sport"), "CBR 500 R", "Honda", 48);
	}
	
	@Test
	public void testBikeConstructorOnException() {
		assertThrows(IllegalArgumentException.class, ()->{
			new Bike(null, "CBR 500 R", "Honda", 48);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Bike(BikeType.getInstance("sport"), "", "Honda", 48);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Bike(BikeType.getInstance("sport"), "CBR 500 R", "", 48);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Bike(BikeType.getInstance("sport"), "", "Honda", 20001);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Bike(BikeType.getInstance("sport"), "", "Honda", 0);
		});
	}
	
	@Test
	public void testGetBikeType() {
		assertTrue(BikeType.getInstance("sport") == regularBike.getType());
	}
	
	@Test
	public void testGetModel() {
		assertEquals("CBR 500 R", regularBike.getModel());
	}
	
	@Test
	public void testGetBrande() {
		assertEquals("Honda", regularBike.getBrande());
	}
	
	@Test
	public void testGetHorsepower() {
		assertEquals(48, regularBike.getHorsepower());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetTypeOnException() {
		regularBike.setType(null);
	}
	
	@Test
	public void testSetType() {
		regularBike.setType(BikeType.getInstance("supersport"));
		assertTrue(BikeType.getInstance("supersport") == regularBike.getType());
	}
	
	@Test
	public void testSetHorsepowerOnException() {
		assertThrows(IllegalArgumentException.class, ()->{
			regularBike.setHorsepower(0);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			regularBike.setHorsepower(20001);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			regularBike.setHorsepower(-10);
		});
	}
	
	@Test
	public void testSetHorsepower() {
		regularBike.setHorsepower(980);
		assertEquals(980, regularBike.getHorsepower());
	}
}
