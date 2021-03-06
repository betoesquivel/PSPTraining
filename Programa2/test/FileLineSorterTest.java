import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mx.itesm.a01139626.p2.src.CodePart;
import mx.itesm.a01139626.p2.src.FileLineSorter;

import org.junit.Test;


public class FileLineSorterTest {
	FileLineSorter filSorter;
	
	public String readContentFromFile(String sFName) {
		try {
			BufferedReader bufFileReader = new BufferedReader(new FileReader(new File(
					sFName)));
			try {
				int iChar;
				String sContent = ""; 
				// read the whole file
				while ((iChar = bufFileReader.read()) != -1) {
					sContent += (char)iChar; 
				}
				return sContent;
			} catch (IOException e) {
				System.out.println(e);
				return "invalid";
			}
		} catch (FileNotFoundException e) {
			// Print appropriate message constant.
			System.out.println(e);
			return "invalid";
		}

	}
	
	@Test
	public void pruebaComentarios1Parte1Item(){
		filSorter = new FileLineSorter();
		filSorter.addFileName("pruebaComentarios1Parte1Item.src");
		filSorter.parseFiles();
		
		assertEquals(15, filSorter.getFilArrFilesWithStats().get(0).getiTotalLOC());
	}
	@Test
	public void pruebaComentarios2Partes5Items(){
		filSorter = new FileLineSorter();
		filSorter.addFileName("pruebaEtiquetasComentarios2Partes5Items.src");
		filSorter.parseFiles();
		CodePart cod1 = filSorter.getFilArrFilesWithStats().get(0).getCodArrParts().get(0);
		CodePart cod2 = filSorter.getFilArrFilesWithStats().get(0).getCodArrParts().get(1);
		assertEquals(6, cod1.getiTotalLOC());
		assertEquals(3, cod2.getiTotalLOC());
	}
	@Test
	public void pruebaEtiquetasStrings1Parte1Item(){
		filSorter = new FileLineSorter();
		filSorter.addFileName("pruebaEtiquetasStrings1Parte1Item.src");
		filSorter.parseFiles();
		
		assertEquals(8, filSorter.getFilArrFilesWithStats().get(0).getiTotalLOC());
	}
	@Test
	public void pruebaVacio(){
		filSorter = new FileLineSorter();
		filSorter.addFileName("pruebaVacio.src");
		filSorter.parseFiles();
		
		assertEquals(0, filSorter.getiTotalInfoLines());
	}
	@Test
	public void pruebaVacio1Parte(){
		filSorter = new FileLineSorter();
		filSorter.addFileName("pruebaVacio1Parte.src");
		filSorter.parseFiles();
		
		assertEquals(0, filSorter.getiTotalInfoLines());
	}

}

