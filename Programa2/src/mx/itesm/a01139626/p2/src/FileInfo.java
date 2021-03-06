
package mx.itesm.a01139626.p2.src;
//&p-FileInfo
//&b=73
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileInfo implements ErrorMessages {
	private String sFileName;
	//&d=2
	private int iTotalLOC;//&m
	private BufferedReader bufFileReader;
	private Parser parFinder; 
	private boolean bExisting; 
	private ArrayList<CodePart> codArrParts;


	//&i
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
		//&d=2
		setiTotalLOC(0);
		
		// initialize the parser, compiling the regexps
		setParFinder(new Parser());
		
		// null the file reader
		this.bufFileReader = null;
		
		// create the array list
		this.codArrParts = new ArrayList<CodePart>();
		
	}

	//&i
	/**
	 * printParts
	 * 
	 * prints each CodePart in the class array
	 * 
	 */
	public void printParts() {
		
		ArrayList<CodePart> codArrAdded = new ArrayList<CodePart>();
		ArrayList<CodePart> codArrBase = new ArrayList<CodePart>();
		ArrayList<CodePart> codArrReused = new ArrayList<CodePart>();
		for (CodePart codC : this.codArrParts) {
		
			String sType = codC.getsType();
			switch (sType) {
			case "ADDED": codArrAdded.add(codC); 
				break;
			case "BASE":  codArrBase.add(codC);
				break;
			case "REUSED": codArrReused.add(codC);
				break;
			}
			
		}
		
		
		
	}

	//&i
	/**
	 * parseParts
	 * 
	 * Calls the method parse on each CodePart in the array
	 * 
	 */
	public void parseParts() {
		
		for (CodePart codC : this.codArrParts) {
			codC.parsePart();
		}
		
	}

	//&i
	/**
	 * createParts
	 * 
	 * Fills the ArrayList codArrParts with instances of the class CodePart
	 * based on the info received as a parameter. 
	 * 
	 * @param parsed content that has no spaces, or comments, just tags
	 */
	public void createParts(String sContent) {
		
		ArrayList<String> sArrPartsContent = new ArrayList<String>();
		ArrayList<String> sArrPartsName = new ArrayList<String>();
		
		parFinder.splitIntoParts(sContent, sArrPartsContent, sArrPartsName);
		String sPartName;
		String sPartContent;
		if (sArrPartsContent.size() > 0){
			for (int iIndex = 0; iIndex < sArrPartsName.size(); iIndex++){
	
				sPartName = sArrPartsName.get(iIndex);
				sPartContent = sArrPartsContent.get(iIndex);
				
				// create the CodePart and add it to the array
				CodePart codNew = new CodePart(sPartName, sPartContent);
				this.codArrParts.add(codNew);
				
			}
		} else {
			if (sArrPartsName.size() > 0) {
				sPartName = sArrPartsName.get(0);
				sPartContent = "";
				
				// create the CodePart and add it to the array
				CodePart codNew = new CodePart(sPartName, sPartContent);
				this.codArrParts.add(codNew);
			}
		}
		
	}

	//&i
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
				// Remove comments, without deleting tags
				sContent = parFinder.removeComments(sContent);
				// Remove white lines
				sContent = parFinder.removeWhiteLines(sContent);
				// Remove single brackets 
				sContent = parFinder.removeSingleBrackets(sContent);
				// Replace strings "" with STRING
				sContent = parFinder.replaceStringLiterals(sContent);
				// Copy sContent to new variable that will be used to break content into parts
				String tmpContent = sContent;
				// Call method createParts with this new variable
				createParts(tmpContent);
				// Remove all tags
				sContent = parFinder.removeTags(sContent);
				// Count total lines of code
				setiTotalLOC(parFinder.countInfoLines(sContent));

				//&d=3
				
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

	

	//&i
	/**
	 * @return the sFileName
	 */
	public String getsFileName() {
		
		return sFileName;
		
	}
	
	//&i
	/**
	 * @param sFileName the sFileName to set
	 */
	public void setsFileName(String sFileName) {
		
		this.sFileName = sFileName;
		
	}
	//&d=8


	//&i
	/**
	 * @return the iTotalLOC
	 */
	public int getiTotalLOC() {
		
		return iTotalLOC;
		
	}
	
	//&i
	/**
	 * @param iTotalLOC the iTotalLines to set
	 */
	public void setiTotalLOC(int iTotalLOC) {
		
		this.iTotalLOC = iTotalLOC;
		
	}

	//&i
	/**
	 * @return the parFinder
	 */
	public Parser getParFinder() {
		
		return parFinder;
		
	}

	//&i
	/**
	 * @param parFinder the parFinder to set
	 */
	public void setParFinder(Parser parFinder) {
		
		this.parFinder = parFinder;
		
	}

	//&i
	/**
	 * @return the bExisting
	 */
	public boolean isbExisting() {
		return bExisting;
	}

	//&i
	/**
	 * @param bExisting the bExisting to set
	 */
	public void setbExisting(boolean bExisting) {
		
		this.bExisting = bExisting;
		
	}

	//&i
	/**
	 * @return the codArrParts
	 */
	public ArrayList<CodePart> getCodArrParts() {
		return codArrParts;
	}

	//&i
	/**
	 * @param codArrParts the codArrParts to set
	 */
	public void setCodArrParts(ArrayList<CodePart> codArrParts) {
		this.codArrParts = codArrParts;
	}

	//&i
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
		//&d=1
		// Set string to file not found, or format output accordingly.
		if (isbExisting()) {
			sRepresentation = "Representation";
		}else {
			sRepresentation = "Not found";
		}
		
		return sRepresentation;
		
	}
}
