package mx.itesm.A01139626.p5.test;
import static org.junit.Assert.*;
import mx.itesm.A01139626.p5.src.AreaUnderTDistribution;

import org.junit.Test;



public class AreaUnderTDistributionTest {
	AreaUnderTDistribution areObj;
	
	@Test
	public void testCorrectData() {
		double dErr = 0.00001;
		areObj = new AreaUnderTDistribution(0, 6, 0.20000);
		areObj.calculateX();
		assertEquals(areObj.getdX(), 0.55338, dErr);
		
		areObj = new AreaUnderTDistribution(0, 15, 0.45000);
		areObj.calculateX();
		assertEquals(areObj.getdX(), 1.75305, dErr);
		
		areObj = new AreaUnderTDistribution(0, 4, 0.49500);
		areObj.calculateX();
		assertEquals(areObj.getdX(), 4.60409, dErr);
	}


}
