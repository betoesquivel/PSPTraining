package mx.itesm.a01139626.p2.src;
//&p-Parser
//&b=?
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ErrorMessages{
	private Pattern patInfoLines; 
	private Pattern patWhiteLines;
	
	private Pattern patLines;
	private Pattern patJustWhiteSpace;
	private Pattern patValidFileName;
	private Pattern patNumeric;

	private String sInfoLine = "((\\A.*\\S.*\\z)|(\\A.*\\S.*)(?=(\\r?\\n))|(\\r?\\n)(.*\\S.*)(?=(\\r?\\n))|(\\r?\\n)(.*\\S.*)\\z)";
	private String sWhiteLine = "((\\A[ \\t]+\\z)|(\\A[ \\t]+)(?=(\\r?\\n))|(\\r?\\n)([ \\t]*)(?=(\\r?\\n))|(\\r?\\n)([ \\t]*)\\z)";

	private String sCommentLine = "";
	private String sSingleBracket = "";
	private String sStringLiterals = "";
	private String sTags = "";
	private String sPTags = "";
	private String sBTags = "";
	private String sDTags = "";
	private String sITags = "";
	private String sMTags = "";
	
	private Matcher matMatcher; 
	
	public Parser(){
		
		// matches lines with information
		this.patInfoLines = Pattern.compile(sInfoLine);
		// matches lines with only spaces
	    this.patWhiteLines = Pattern.compile(sWhiteLine);
		// matches strings with only one file name with extension
		this.patValidFileName = Pattern.compile("/?[a-zA-Z0-9_/]*\\.\\w+");
		// matches integers
		this.patNumeric = Pattern.compile("\\d+");
		
	}
	
	public String removeComments(String s){
		
		return s; 
		
	}
	
	public String removeWhiteLines(String s){
		
		return s;
		
	}
	
	public String removeSingleBrackets(String s){
		
		return s;
		
	}
	
	public String removeTags(String s){
		
		return s;
		
	}
	
	public String replaceStringLiterals(String s){
		
		return s;
		
	}
	
	public int sumBTags(String s){
		
		return 0;
		
	}
	
	public int sumDTags(String s){
		
		return 0;
		
	}
	
	public int countITags(String s){
		
		return 0;
		
	}
	
	public int countMTags(String s){
		
		return 0;
		
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
