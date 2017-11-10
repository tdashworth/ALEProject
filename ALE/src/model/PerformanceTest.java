package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PerformanceTest {

	@Test
	public void constructorTest() {
		Performance p = Performance.newPerformance(1, "MeowChoir", "A choir of meows.", "Musical", 
				"Catish", 50.0, "3hr");
		assertEquals("MeowChoir", p.getTitle());
		assertEquals("A choir of meows.", p.getDescription());
		assertEquals("Musical", p.getType());
		assertEquals("Catish", p.getLanguages());
		assertEquals(50.0, p.getPrice(), 0.00);
		assertEquals("3hr", p.getDuration());

		System.out.println(p);
	}

}
