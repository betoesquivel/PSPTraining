import static org.junit.Assert.assertEquals;
import mx.itesm.a01139626.p3.src.DataSetAnalyzer;

import org.junit.Test;


public class DataSetAnalyzerTest {

	@Test
	public void test1DataPoint () {
		DataSetAnalyzer datS = new DataSetAnalyzer();
		datS.setsFileName("Prueba.txt");
		datS.analyze();
		assertEquals(datS.getDatSet().getdYK(), 0,  0.00005);
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
		assertEquals(datS.getDatSet().getdYK(), -39, 0.00005);
	}

}
