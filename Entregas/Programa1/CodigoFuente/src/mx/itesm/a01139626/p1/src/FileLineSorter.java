package mx.itesm.a01139626.p1.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileLineSorter implements ErrorMessages{

	private ArrayList<String> sArrFileNames;
	private ArrayList<FileInfo> filArrFilesWithStats;
	private int iNumberOfFiles;
	private int iTotalWhiteLines;
	private int iTotalInfoLines;
	
	/**
	 * @return the sArrFileNames
	 */
	public ArrayList<String> getsArrFileNames() {
		return sArrFileNames;
	}

	/**
	 * @param sArrFileNames the sArrFileNames to set
	 */
	public void setsArrFileNames(ArrayList<String> sArrFileNames) {
		this.sArrFileNames = sArrFileNames;
	}

	/**
	 * @return the filArrFilesWithStats
	 */
	public ArrayList<FileInfo> getFilArrFilesWithStats() {
		return filArrFilesWithStats;
	}

	/**
	 * @param filArrFilesWithStats the filArrFilesWithStats to set
	 */
	public void setFilArrFilesWithStats(ArrayList<FileInfo> filArrFilesWithStats) {
		this.filArrFilesWithStats = filArrFilesWithStats;
	}

	/**
	 * @return the iNumberOfFiles
	 */
	public int getiNumberOfFiles() {
		return iNumberOfFiles;
	}

	/**
	 * @param iNumberOfFiles the iNumberOfFiles to set
	 */
	public void setiNumberOfFiles(int iNumberOfFiles) {
		this.iNumberOfFiles = iNumberOfFiles;
	}

	/**
	 * @return the iTotalWhiteLines
	 */
	public int getiTotalWhiteLines() {
		return iTotalWhiteLines;
	}

	/**
	 * @param iTotalWhiteLines the iTotalWhiteLines to set
	 */
	public void setiTotalWhiteLines(int iTotalWhiteLines) {
		this.iTotalWhiteLines = iTotalWhiteLines;
	}

	/**
	 * @return the iTotalInfoLines
	 */
	public int getiTotalInfoLines() {
		return iTotalInfoLines;
	}

	/**
	 * @param iTotalInfoLines the iTotalInfoLines to set
	 */
	public void setiTotalInfoLines(int iTotalInfoLines) {
		this.iTotalInfoLines = iTotalInfoLines;
	}

	public FileLineSorter(){
		
		sArrFileNames = new ArrayList<String>();
		filArrFilesWithStats = new ArrayList<FileInfo>();
		initCounters();
		
	}
	
	public void addFileName(String s){
		sArrFileNames.add(s);
		iNumberOfFiles += 1;
	}
	
	public void initCounters(){
		iNumberOfFiles = 0;
		iTotalWhiteLines = 0;
		iTotalInfoLines = 0;
	}
	
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
			iTotalWhiteLines += filFile.getiWhiteLines();
			iTotalInfoLines += filFile.getiInfoLines();
		}
		
		return true; 
	}
	
	public void sortFiles(){
		getFilArrFilesWithStats().sort(new FileInfoComparator());
	}
	
	public void printFiles(){
		
		for (FileInfo filFile : filArrFilesWithStats){
			System.out.println(filFile);
			System.out.println("--------------------------------------------");
		}
	}
	
	
	public String toString(){
		String format = "TOTALES:\n"
				+ "Cantidad de archivos: %d\n"
				+ "Cantidad total de líneas en blanco: %d\n"
				+ "Cantidad total de líneas con información: %d";
		return String.format(format, this.iNumberOfFiles, this.iTotalWhiteLines, this.iTotalInfoLines);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
		Parser parValidator = new Parser();
		
		FileLineSorter filSorter = new FileLineSorter();
		String sFileNumber = null;
		int iFileNumber = 0; 
		
		// ask for how many files are to be sorted
		do {
			if (sFileNumber != null) {
				System.out.println(sINVALID_INTEGER);
			}
			System.out.println("Introduzca el numero de archivos a ordenar: ");
			sFileNumber = bufIn.readLine();
		
		} while (!parValidator.isNumeric(sFileNumber));
		
		iFileNumber = Integer.parseInt(sFileNumber);
		
		// ask for file names separated by a new line with extension
		System.out.println("Introduzca los nombres de los archivos a ordenar, separados por "
				+ "un salto de linea. (ejemplo: Archivo1.txt)");
		
		// read number of files from input
		int iCount = 0; 
		String sFName = null;
		boolean bValid = true; 
		
		do {
			if (sFName != null){
				System.out.println (sINVALID_FILE_NAME);
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
			// sort all files in FileInfo arraylist
			filSorter.filArrFilesWithStats.sort(new FileInfoComparator());
			// print all files in FileInfo arraylist
			filSorter.printFiles();
			// print totals
			System.out.println(filSorter);
		}
		
		
	}

}
