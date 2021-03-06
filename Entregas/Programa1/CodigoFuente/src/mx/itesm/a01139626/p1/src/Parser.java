package mx.itesm.a01139626.p1.src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ErrorMessages{
	private Pattern patInfoLines; 
	private Pattern patWhiteLines;
	private Pattern patLines;
	private String sInfoLine = "((\\A.*\\S.*\\z)|(\\A.*\\S.*)(?=(\\r?\\n))|(\\r?\\n)(.*\\S.*)(?=(\\r?\\n))|(\\r?\\n)(.*\\S.*)\\z)";
	private String sWhiteLine = "((\\A[ \\t]+\\z)|(\\A[ \\t]+)(?=(\\r?\\n))|(\\r?\\n)([ \\t]*)(?=(\\r?\\n))|(\\r?\\n)([ \\t]*)\\z)";
	private Pattern patJustWhiteSpace;
	private Pattern patValidFileName;
	private Pattern patNumeric;
	private Matcher matMatcher; 
	
	public Parser(){
		
		// matches lines with information
		this.patInfoLines = Pattern.compile(sInfoLine);
		// matches lines with only spaces
	    this.patWhiteLines = Pattern.compile(sWhiteLine);
		// matches strings with only one file name with extension
		this.patValidFileName = Pattern.compile("[a-zA-Z0-9_]*\\.txt");
		// matches integers
		this.patNumeric = Pattern.compile("\\d+");
		
	}
	
	public boolean isWhiteLine(String s){
	
		matMatcher = patJustWhiteSpace.matcher(s);
		
		return matMatcher.matches();
		
	}
	
	public boolean isInfoLine(String s){
		
		matMatcher = patInfoLines.matcher(s);
		
		return matMatcher.find();
		
	}
	
	public boolean isValidFile(String s){
		
		matMatcher = patValidFileName.matcher(s);
		
		return matMatcher.matches();
	}
	public boolean isNumeric(String s){
		
		matMatcher = patNumeric.matcher(s);
		
		return matMatcher.matches();
	}
	
	public int countInfoLines(String s){
		matMatcher = patInfoLines.matcher(s);
		int iCount = 0;
		while(matMatcher.find()){
			iCount += 1;
		}
		return iCount;
	}
	public int countWhiteLines(String s){
		matMatcher = patWhiteLines.matcher(s);
		int iCount = 0;
		while(matMatcher.find()){
			iCount += 1;
		}
		return iCount;
	}
	
}
