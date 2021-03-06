import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import mx.itesm.a01139626.p1.src.FileInfo;
import mx.itesm.a01139626.p1.src.FileLineSorter;

import org.junit.Test;


public class FileLineSorterTest {
	FileLineSorter filSorter;
	
	
	@Test
	public void testWhiteLineCounter(){
		System.out.println("\nWHITELINES");		
		filSorter = new FileLineSorter();
		filSorter.addFileName("Prueba5LineasBlanco.txt");
		filSorter.parseFiles();
		filSorter.sortFiles();
		filSorter.printFiles();
		System.out.println(filSorter);
		
		assertEquals(5, filSorter.getFilArrFilesWithStats().get(0).getiWhiteLines());
		assertEquals(5, filSorter.getFilArrFilesWithStats().get(0).getiTotalLines());
		
	}
	@Test
	public void testInfoLineCounter(){
		System.out.println("\nINFOLINES");
		filSorter = new FileLineSorter();
		filSorter.addFileName("Prueba5LineasInformacion.txt");
		filSorter.parseFiles();
		filSorter.sortFiles();
		filSorter.printFiles();
		System.out.println(filSorter);
		
		assertEquals(5, filSorter.getFilArrFilesWithStats().get(0).getiInfoLines());
		
	}
	@Test
	public void testSortingEquivalentFiles(){
		System.out.println("\nSORTING");
		filSorter = new FileLineSorter();
		filSorter.addFileName("PruebaArchivoIgual1.txt");
		filSorter.addFileName("PruebaArchivoIgual2.txt");
		filSorter.parseFiles();
		filSorter.sortFiles();
		filSorter.printFiles();
		System.out.println(filSorter);
		
		
		FileInfo filF1 = filSorter.getFilArrFilesWithStats().get(0);
		FileInfo filF2 = filSorter.getFilArrFilesWithStats().get(1);
		
		// Correct order
		assertEquals("PruebaArchivoIgual1.txt", filF1.getsFileName());
		
		// Same stats from files.
		assertEquals(filF1.getiWhiteLines(), filF2.getiWhiteLines());
		assertEquals(filF1.getiInfoLines(), filF2.getiInfoLines());
		
		// Total lines working properly
		assertEquals(10, filSorter.getiTotalWhiteLines());
		assertEquals(10, filSorter.getiTotalInfoLines());
		
	}

}
