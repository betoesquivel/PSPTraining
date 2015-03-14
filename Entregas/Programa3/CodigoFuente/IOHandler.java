package mx.itesm.a01139626.p3.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
//&p-IOHandler
public class IOHandler implements ErrorMessages{
	private Parser parParser;

	//&i
	public IOHandler() {
		
		parParser = new Parser();
		
	}

	//&i
	public String readFileName() {
		
		String sFN = "";
		BufferedReader bufInput; 
		bufInput = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.println ("Input the file name that contains the Dataset. (Archivo.txt)");
		try {
			sFN = bufInput.readLine();
		} catch (IOException e) {
			System.out.println(sIO_EXCEPTION);
		}
		
		while ( !parParser.isValidFile(sFN) ) {
		
			try {
				sFN = bufInput.readLine();
			} catch (IOException e) {
				System.out.println(sIO_EXCEPTION);
			}
			
		}
		return sFN;
	}

	//&i	
	public boolean readDataSetFromFile(String sFName, DataSet datSet) {
		BufferedReader bufInput; 
		
		try {
			
			bufInput = new BufferedReader( new FileReader ( new File(sFName) ) );
			String sLine = "";
			String sX, sY;
			double dX, dY; 
			
			try {
				
				sX = bufInput.readLine();
				if (sX != null) { 
					sX = sX.trim();
					// falta parsear sX y agregarlo a xXK... por ende me falta poner los setters y getters de la clase. 
					if ( parParser.isDouble(sX) ) {
				
						dX = Double.parseDouble(sX);
						datSet.setdXK(dX);
						
					}
				
					while ( ( sLine = bufInput.readLine() ) != null ) {
						
						// split line using the comma as separator
						ArrayList<String> sArrPoints = new ArrayList<String>( Arrays.asList( sLine.split(",") ) );
						sX = sArrPoints.get(0).trim();
						sY = sArrPoints.get(1).trim();
						if ( parParser.isDouble(sX) && parParser.isDouble(sY) ) {
					
							dX = Double.parseDouble(sX);
							dY = Double.parseDouble(sY);
							datSet.addPair(dX, dY);
							
						}
						
					}
				}
				bufInput.close();
				return true;
				
			} catch (NumberFormatException e) {
				
				System.out.println(sINVALID_DOUBLE);
				return false;
				
			} catch (IOException e) {
				
				System.out.println(sIO_EXCEPTION);
				return false;
				
			}
			
		} catch (FileNotFoundException e) {
			
			System.out.println(sFILE_NOT_FOUND);
			return false;
		}	
		
	}
	
	
}