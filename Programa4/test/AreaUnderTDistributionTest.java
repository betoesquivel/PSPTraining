import static org.junit.Assert.*;
import mx.itesm.A01139626.src.AreaUnderTDistribution;

import org.junit.Test;


public class AreaUnderTDistributionTest {
	AreaUnderTDistribution areObj;
	@Test
	public void testCorrectData() {
		double dErr = 0.00001;
		areObj = new AreaUnderTDistribution(1.1, 9);
		areObj.calculate();
		assertEquals(areObj.getdP(), 0.35006, dErr);
		
		areObj = new AreaUnderTDistribution(1.1812, 10);
		areObj.calculate();
		assertEquals(areObj.getdP(), 0.36757, dErr);
		
		areObj = new AreaUnderTDistribution(2.75, 30);
		areObj.calculate();
		assertEquals(areObj.getdP(), 0.49500, dErr);
	}


}
