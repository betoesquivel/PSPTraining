package mx.itesm.a01139626.p1.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInfo implements ErrorMessages {
	private String sFileName;
	private int iWhiteLines;
	private int iInfoLines;
	private int iTotalLines;
	private BufferedReader bufFileReader;
	private Parser parFinder; 
	private boolean bExisting; 

	/**
	 * FileInfo String constructor
	 * 
	 * Initializes a FileInfo object with a String received.
	 * 
	 * @param sFileName with a file name with extension.
	 */
	public FileInfo(String sFileName) {
		
		// init file name
		setsFileName(sFileName);
		
		// at first, the file is non existent
		setbExisting(false);
		
		// zero stat counters
		setiWhiteLines(0);
		setiInfoLines(0);
		setiTotalLines(0);
		
		// initialize the parser, compiling the regexps
		setParFinder(new Parser());
		
		// null the file reader
		this.bufFileReader = null;
		
	}

	/**
	 * parseFile
	 * 
	 * Reads the file line by line while filling the information of this object.
	 * The information is: (iWhiteLines, iInfoLines, iTotalLines);
	 * 
	 * @return <code>boolean</code> which is true if the file was found, and
	 *         false otherwise.
	 * @exception Exception
	 *                of type <code>FileNotFoundException</code> when file is
	 *                not found.
	 */
	public boolean parseFile() {
		// try to open the file for parsing
		try {
			bufFileReader = new BufferedReader(new FileReader(new File(
					this.sFileName)));
			setbExisting(true);
			try {
				int iChar;
				String sContent = ""; 
				// read the whole file
				while ((iChar = bufFileReader.read()) != -1) {
					sContent += (char)iChar; 
				}	

				// Count the info lines
				setiInfoLines(getParFinder().countInfoLines(sContent));
				// Count the white lines
				setiWhiteLines(getParFinder().countWhiteLines(sContent));
				
				// Set the total lines
				setiTotalLines(getiWhiteLines() + getiInfoLines());
				
			} catch (IOException e) {
				System.out.println(sIO_EXCEPTION);
			}
		} catch (FileNotFoundException e) {
			// Print appropriate message constant.
			System.out.println(sFILE_NOT_FOUND);
			// The state of this file is of Non-existing. 
			setbExisting(false);
			return false;
		}
		return true;
	}

	
	
	/**
	 * @return the sFileName
	 */
	public String getsFileName() {
		
		return sFileName;
		
	}

	/**
	 * @param sFileName the sFileName to set
	 */
	public void setsFileName(String sFileName) {
		
		this.sFileName = sFileName;
		
	}

	/**
	 * @return the iWhiteLines
	 */
	public int getiWhiteLines() {
		
		return iWhiteLines;
		
	}

	/**
	 * @param iWhiteLines the iWhiteLines to set
	 */
	public void setiWhiteLines(int iWhiteLines) {
		
		this.iWhiteLines = iWhiteLines;
		
	}

	/**
	 * @return the iInfoLines
	 */
	public int getiInfoLines() {
		
		return iInfoLines;
		
	}

	/**
	 * @param iInfoLines the iInfoLines to set
	 */
	public void setiInfoLines(int iInfoLines) {
		
		this.iInfoLines = iInfoLines;
		
	}

	/**
	 * @return the iTotalLines
	 */
	public int getiTotalLines() {
		
		return iTotalLines;
		
	}

	/**
	 * @param iTotalLines the iTotalLines to set
	 */
	public void setiTotalLines(int iTotalLines) {
		
		this.iTotalLines = iTotalLines;
		
	}

	/**
	 * @return the parFinder
	 */
	public Parser getParFinder() {
		
		return parFinder;
		
	}

	/**
	 * @param parFinder the parFinder to set
	 */
	public void setParFinder(Parser parFinder) {
		
		this.parFinder = parFinder;
		
	}

	/**
	 * @return the bExisting
	 */
	public boolean isbExisting() {
		return bExisting;
	}

	/**
	 * @param bExisting the bExisting to set
	 */
	public void setbExisting(boolean bExisting) {
		
		this.bExisting = bExisting;
		
	}

	/**
	 * toString
	 * 
	 * Contains the string representation of a FileInfo object.
	 * 
	 * @return <code>String</code> variable with the String representation
	 * of the object. 
	 */
	public String toString() {
		
		String sRepresentation = "Nombre del archivo: %s\n"
				+ "Cantidad de líneas en blanco: %d\n"
				+ "Cantidad de líneas con información: %d";
		
		// Set string to file not found, or format output accordingly.
		sRepresentation = (isbExisting()) ? 
			String.format(sRepresentation, getsFileName(), getiWhiteLines(), getiInfoLines()) : 
			String.format("The file named %s, was not found.", getsFileName());
		
		return sRepresentation;
		
	}
}
