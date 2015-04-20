import static org.junit.Assert.*;

import org.junit.Test;

import mx.itesm.a01139626.p6.src.DataSetAnalyzer;


public class DataSetAnalyzerTest {

	@Test
	public void test1DataPoint () {
		DataSetAnalyzer datS = new DataSetAnalyzer();
		datS.setsFileName("Arch1.txt");
		datS.analyze();
		assertEquals(datS.getDatSet().getdYK(), 644.42938,  0.00005);
	}
	@Test
	public void testEmpty () {
		DataSetAnalyzer datS = new DataSetAnalyzer();
		datS.setsFileName("Vacio.txt");
		datS.analyze();
		assertEquals(datS.getDatSet().getdYK(), 0,  0.00005);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testStringsInFile () {
		DataSetAnalyzer datS = new DataSetAnalyzer();
		datS.setsFileName("datosStrings.txt");
		datS.analyze();
		assertEquals(datS.getDatSet().getdYK(), 0, 0.00005);
	}

}
