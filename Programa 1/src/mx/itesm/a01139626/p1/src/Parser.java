package mx.itesm.a01139626.p1.src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ErrorMessages{
	private Pattern patInfo; 
	private Pattern patJustWhiteSpace; 
	private Pattern patValidFileName;
	private Pattern patNumeric;
	private Matcher matMatcher; 
	
	public Parser(){
		
		// finds non-space characters
		this.patInfo = Pattern.compile("\\S");
		// matches strings with only spaces
		this.patJustWhiteSpace = Pattern.compile("\\s*");
		// matches strings with only one file name with extension
		this.patValidFileName = Pattern.compile("[a-zA-Z0-9_][a-zA-Z0-9]*\\.txt");
		// matches integers
		this.patNumeric = Pattern.compile("\\d+");
		
	}
	
	public boolean isWhiteLine(String s){
	
		matMatcher = patJustWhiteSpace.matcher(s);
		
		return matMatcher.matches();
		
	}
	
	public boolean isInfoLine(String s){
		
		matMatcher = patInfo.matcher(s);
		
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
	
}
