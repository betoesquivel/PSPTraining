package mx.itesm.a01139626.p2.src;
//&p-Parser
//&b=41
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements ErrorMessages{
	private Pattern patInfoLines; 
	private Pattern patWhiteLines;
	
	private Pattern patLines;
	private Pattern patJustWhiteSpace;
	private Pattern patValidFileName;
	private Pattern patNumeric;
	
	private Pattern patMultiComments;
	private Pattern patSingleComments;
	private Pattern patSingleBracket;
	private Pattern patStringLiterals;
	private Pattern patTags;
	private Pattern patPTags;
	private Pattern patBTags; 
	private Pattern patDTags;
	private Pattern patITags;
	private Pattern patMTags;

	private String sInfoLine = "((\\A.*\\S.*\\z)|(\\A.*\\S.*)(?=(\\r?\\n))|(\\r?\\n)(.*\\S.*)(?=(\\r?\\n))|(\\r?\\n)(.*\\S.*)\\z)";
	private String sWhiteLine = "((\\A[ \\t]+\\z)|(\\A[ \\t]+)(?=(\\r?\\n))|(\\r?\\n)([ \\t]*)(?=(\\r?\\n))|(\\r?\\n)([ \\t]*)\\z)";

	private String sMultiComments  = "/\\*([^*]|(\\r?\\n)|(\\*+([^*/]|(\\r?\\n))))*\\*+/"; // this has to be used with Pattern.DOTALL
	private String sSingleComments = "//([^&].*)?$"; // this has to be used with Pattern.MULTILINE
	private String sSingleBracket  = "^([ \\t]*)[{}]([ \\t]*)$"; // this has to be used with Pattern.MULTILINE
	private String sStringLiterals = "(\".*\")|('.*')";
	private String sTags           = "//&[pbdmi].*";
	private String sPTags          = "(//&p-)(\\w+)";
	private String sBTags          = "(//&b=)(\\d+)";
	private String sDTags          = "(//&d=)(\\d+)";
	private String sITags          = "//&i";
	private String sMTags          = "//&m";
	
	private Matcher matMatcher; 
	
	//&i
	public Parser(){
		
		// matches multi comments
		this.patMultiComments = Pattern.compile(sMultiComments, Pattern.DOTALL);
		// matches single comments
		this.patSingleComments = Pattern.compile(sSingleComments, Pattern.MULTILINE);
		// matches single bracket
		this.patSingleBracket = Pattern.compile(sSingleBracket, Pattern.MULTILINE);
		// matches string literals
		this.patStringLiterals = Pattern.compile(sStringLiterals);
		// matches any tag
		this.patTags = Pattern.compile(sTags);
		// matches p tags
		this.patPTags = Pattern.compile(sPTags);
		// matches b tags
		this.patBTags = Pattern.compile(sBTags);
		// matches d tags
		this.patDTags = Pattern.compile(sDTags);
		// matches i tags
		this.patITags = Pattern.compile(sITags);
		// matches m tags
		this.patMTags = Pattern.compile(sMTags);
		
		// matches lines with information
		this.patInfoLines = Pattern.compile(sInfoLine);
		// matches lines with only spaces
	    this.patWhiteLines = Pattern.compile(sWhiteLine);
		// matches strings with only one file name with extension
		this.patValidFileName = Pattern.compile("/?[a-zA-Z0-9_/]*\\.\\w+"); //&m
		// matches integers
		this.patNumeric = Pattern.compile("\\d+");
		
	}
	//&i
	public void splitIntoParts(String s, ArrayList<String> sArrContents, ArrayList<String> sArrNames){
		
		sArrContents.addAll(Arrays.asList(this.patPTags.split(s)));
		
		// remove first empty part resulting from the split
		if (sArrContents.size() > 0)
			sArrContents.remove(0);
		
		matMatcher = this.patPTags.matcher(s);
		while(matMatcher.find()) {
			sArrNames.add (matMatcher.group(2));
		}
		
	}
	//&i
	public String removeComments(String s){
		
		// remove multi line comments
		matMatcher = patMultiComments.matcher(s);
		String parsed = matMatcher.replaceAll("");
		// remove single line comments. THAT ARE NOT TAGS!
		matMatcher = patSingleComments.matcher(parsed);
		parsed = matMatcher.replaceAll("");
		return parsed; 
		
	}
	//&i
	public String removeWhiteLines(String s){

		matMatcher = patWhiteLines.matcher(s);
		String parsed = matMatcher.replaceAll("");
		return parsed;
		
	}

	//&i
	public String removeSingleBrackets(String s){

		matMatcher = patSingleBracket.matcher(s);
		String parsed = matMatcher.replaceAll("");
		return parsed;
		
	}
	
	//&i
	public String removeTags(String s){
		
		matMatcher = patTags.matcher(s);
		String parsed = matMatcher.replaceAll("");
		return parsed;
		
	}
	
	//&i
	public String replaceStringLiterals(String s){
		
		matMatcher = patStringLiterals.matcher(s);
		String parsed = matMatcher.replaceAll("STRING");
		return parsed;
		
	}
	
	//&i
	public int sumBTags(String s){

		matMatcher = patBTags.matcher(s);
		int iSum = 0;
		while(matMatcher.find()){
			// matMatcher.group(2) contains the int part of the tag
			iSum += Integer.parseInt(matMatcher.group(2));
		}
		return iSum;
		
	}
	
	//&i
	public int sumDTags(String s){

		matMatcher = patDTags.matcher(s);
		int iSum = 0;
		while(matMatcher.find()){
			// matMatcher.group(2) contains the int part of the tag
			iSum += Integer.parseInt(matMatcher.group(2));
		}
		return iSum;
		
	}
	
	//&i
	public int countITags(String s){
		
		matMatcher = patITags.matcher(s);
		int iCount = 0;
		while(matMatcher.find()){
			iCount += 1;
		}
		return iCount;
		
	}
	
	//&i
	public int countMTags(String s){

		matMatcher = patMTags.matcher(s);
		int iCount = 0;
		while(matMatcher.find()){
			iCount += 1;
		}
		return iCount;
		
	}

	//&i
	public boolean isWhiteLine(String s){
	
		matMatcher = patJustWhiteSpace.matcher(s);
		
		return matMatcher.matches();
		
	}

	//&i
	public boolean isInfoLine(String s){
		
		matMatcher = patInfoLines.matcher(s);
		
		return matMatcher.find();
		
	}

	//&i
	public boolean isValidFile(String s){
		
		matMatcher = patValidFileName.matcher(s);
		
		return matMatcher.matches();
	}

	//&i
	public boolean isNumeric(String s){
		
		matMatcher = patNumeric.matcher(s);
		
		return matMatcher.matches();
	}

	//&i
	public int countInfoLines(String s){
		matMatcher = patInfoLines.matcher(s);
		int iCount = 0;
		while(matMatcher.find()){
			iCount += 1;
		}
		return iCount;
	}

	//&i
	public int countWhiteLines(String s){
		matMatcher = patWhiteLines.matcher(s);
		int iCount = 0;
		while(matMatcher.find()){
			iCount += 1;
		}
		return iCount;
	}
	
}
