package mx.itesm.a01139626.p2.src;
//&p-FileLineSorter
//&b=99
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileLineSorter implements ErrorMessages{

	private ArrayList<String> sArrFileNames;
	private ArrayList<FileInfo> filArrFilesWithStats;
	private int iNumberOfFiles;
	private int iTotalWhiteLines;
	private int iTotalInfoLines;

	//&i
	/**
	 * @return the sArrFileNames
	 */
	public ArrayList<String> getsArrFileNames() {
		return sArrFileNames;
	}

	//&i
	/**
	 * @param sArrFileNames the sArrFileNames to set
	 */
	public void setsArrFileNames(ArrayList<String> sArrFileNames) {
		this.sArrFileNames = sArrFileNames;
	}

	//&i
	/**
	 * @return the filArrFilesWithStats
	 */
	public ArrayList<FileInfo> getFilArrFilesWithStats() {
		return filArrFilesWithStats;
	}

	//&i
	/**
	 * @param filArrFilesWithStats the filArrFilesWithStats to set
	 */
	public void setFilArrFilesWithStats(ArrayList<FileInfo> filArrFilesWithStats) {
		this.filArrFilesWithStats = filArrFilesWithStats;
	}

	//&i
	/**
	 * @return the iNumberOfFiles
	 */
	public int getiNumberOfFiles() {
		return iNumberOfFiles;
	}

	//&i
	/**
	 * @param iNumberOfFiles the iNumberOfFiles to set
	 */
	public void setiNumberOfFiles(int iNumberOfFiles) {
		this.iNumberOfFiles = iNumberOfFiles;
	}

	//&i
	/**
	 * @return the iTotalWhiteLines
	 */
	public int getiTotalWhiteLines() {
		return iTotalWhiteLines;
	}

	//&i
	/**
	 * @param iTotalWhiteLines the iTotalWhiteLines to set
	 */
	public void setiTotalWhiteLines(int iTotalWhiteLines) {
		this.iTotalWhiteLines = iTotalWhiteLines;
	}

	//&i
	/**
	 * @return the iTotalInfoLines
	 */
	public int getiTotalInfoLines() {
		return iTotalInfoLines;
	}

	//&i
	/**
	 * @param iTotalInfoLines the iTotalInfoLines to set
	 */
	public void setiTotalInfoLines(int iTotalInfoLines) {
		this.iTotalInfoLines = iTotalInfoLines;
	}

	//&i
	public FileLineSorter(){
		
		sArrFileNames = new ArrayList<String>();
		filArrFilesWithStats = new ArrayList<FileInfo>();
		initCounters();
		
	}

	//&i
	public void addFileName(String s){
		sArrFileNames.add(s);
		iNumberOfFiles += 1;
	}

	//&i
	public void initCounters(){
		iNumberOfFiles = 0;
		iTotalWhiteLines = 0;
		iTotalInfoLines = 0;
	}

	//&i
	public boolean parseFiles(){

		// create the FileInfo s
		for (String sName : sArrFileNames){
			filArrFilesWithStats.add(new FileInfo(sName));
		}
		
		// parse the files
		for (FileInfo filFile : filArrFilesWithStats){
			filFile.parseFile();
			if (!filFile.isbExisting()) {
				initCounters();
				return false; 
			}
			filFile.parseParts();
			this.iTotalInfoLines += filFile.getiTotalLOC();//&m
		}
		
		return true; 
	}

	//&i
	public void sortFiles(){
		getFilArrFilesWithStats().sort(new FileInfoComparator());
	}

	//&i
	//&d=13
	public void printFiles(PrintWriter priOut){
		
		// get all parts and total lines of code
		ArrayList<CodePart> codArrAll = new ArrayList<CodePart>();
		for (FileInfo filF : getFilArrFilesWithStats()) {
			
			codArrAll.addAll(filF.getCodArrParts());
			
		}
		
		// separate parts in Added, Base, and Reused
		ArrayList<CodePart> codArrAdded = new ArrayList<CodePart>();
		ArrayList<CodePart> codArrBase = new ArrayList<CodePart>();
		ArrayList<CodePart> codArrReused = new ArrayList<CodePart>();
		
		for (CodePart codC : codArrAll) {
		
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
		
		// print all in order
		priOut.println("PARTES BASE:");
		for (CodePart codC : codArrBase) {
			priOut.println("\t"+codC);
		}
		priOut.println("--------------------------------------------");
		
		priOut.println("PARTES NUEVAS:");
		for (CodePart codC : codArrAdded) {
			priOut.println("\t"+codC);
		}
		priOut.println("--------------------------------------------");
		
		priOut.println("PARTES REUSADAS:");
		for (CodePart codC : codArrReused) {
			priOut.println("\t"+codC);
		}
		priOut.println("--------------------------------------------");
		
		// print total LOC
		String sFormat = "Total de LDC=%d";
		priOut.println(String.format(sFormat, getiTotalInfoLines()));
		
	}
	
	//&i
	//&d=13
	

	//&i
	public String toString(){
		String format = "TOTALES:\n"
				+ "Cantidad de archivos: %d\n"
				+ "Cantidad total de líneas en blanco: %d\n"
				+ "Cantidad total de líneas con información: %d";
		return String.format(format, this.iNumberOfFiles, this.iTotalWhiteLines, this.iTotalInfoLines);
	}

	//&i
	public static void main(String[] args) throws IOException{
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufWriter = new BufferedWriter( new FileWriter("ConteoLDC.txt"));
		PrintWriter priWriter = new PrintWriter(bufWriter);
		PrintWriter priOut = new PrintWriter(System.out, true);
		
		Parser parValidator = new Parser();
		
		FileLineSorter filSorter = new FileLineSorter();
		String sFileNumber = null;
		int iFileNumber = 0; 
		
		// ask for how many files are to be sorted
		do {
			if (sFileNumber != null) {
				priOut.println(sINVALID_INTEGER);
			}
			priOut.println("Introduzca el numero de archivos a contar: ");//&m
			sFileNumber = bufIn.readLine();
		
		} while (!parValidator.isNumeric(sFileNumber));
		
		iFileNumber = Integer.parseInt(sFileNumber);
		
		// ask for file names separated by a new line with extension
		priOut.println("Introduzca los nombres de los archivos a contar, separados por " + "un salto de linea. (ejemplo: Archivo1.src)"); //&m
		
		// read number of files from input
		int iCount = 0; 
		String sFName = null;
		boolean bValid = true; 
		
		do {
			if (sFName != null){
				priOut.println (sINVALID_FILE_NAME); //&m
			}
			iCount = 0;
			bValid = true; 
			filSorter.setsArrFileNames(new ArrayList<String>());
			while (iCount < iFileNumber && bValid) {
				
				sFName = bufIn.readLine();
				bValid = parValidator.isValidFile(sFName);
				if (bValid){
					// add to file list
					filSorter.addFileName(sFName);
				} 
				
				iCount = (bValid) ? iCount + 1 : 0;
				
			}
		} while (iCount < iFileNumber); // keep reading until having at least one valid file

		// construct and parse all files in the file name arraylist
		if (filSorter.parseFiles()){
			//&d=2
			// print all files in FileInfo arraylist to console and to file
			filSorter.printFiles(priOut);
			filSorter.printFiles(priWriter);
			priWriter.close();
		}
		
		
	}

}
