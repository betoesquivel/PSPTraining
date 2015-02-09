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

	/**
	 * FileInfo String constructor
	 * 
	 * Initializes a FileInfo object with a String received.
	 * 
	 * @param sFileName with a file name with extension.
	 */
	public FileInfo(String sFileName) {
		this.sFileName = sFileName;
		this.iWhiteLines = 0;
		this.iTotalLines = 0;
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
			
			try {
				String sLine = null;
				while ((sLine = bufFileReader.readLine()) != null) {
					sLine = sLine.trim();
					if (sLine == "") {
						this.iWhiteLines += 1;
					}else {
						this.iInfoLines += 1;
					}
					this.iTotalLines += 1; 
				}	
			} catch (IOException e) {
				System.out.println(sIO_EXCEPTION);
			}
		} catch (FileNotFoundException e) {
			// Print appropriate message constant.
			System.out.println(sFILE_NOT_FOUND);
			return false;
		}
		return true;
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
		return String.format(sRepresentation, this.sFileName, this.iWhiteLines,
				this.iInfoLines);
	}
}
