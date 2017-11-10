/**
 * 
 */
package model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Thomas
 *
 */
public class CustomerTest {

	@Test
	public void constructorTest() {
		Customer c = Customer.newCustomer("Test@Email.com", "Meow", "Woof", 
									  "1 Meow Lane", "Woof Town", "CA7 DO9", 
									  "Animal Kingdom", "Me0w_wOOf");
		assertEquals("test@email.com", c.getEmail());
		assertEquals("Meow", c.getFirstName());
		assertEquals("Woof", c.getLastName());
		assertEquals("1 Meow Lane", c.getAddressLine1());
		assertEquals("Woof Town", c.getAddressLine2());
		assertEquals("CA7 DO9", c.getPostcode());
		assertEquals("Animal Kingdom", c.getCountry());
		assertEquals("Me0w_wOOf", c.getPassword());
		
		System.out.println(c);
	}

}
