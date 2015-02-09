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
