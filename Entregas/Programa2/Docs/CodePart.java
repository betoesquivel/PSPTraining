package mx.itesm.a01139626.p2.src;
//&p-CodePart
public class CodePart {
	
	private String sPartName;
	private String sContent;
	private String sType;
	private Parser parParser; 
	
	private int iTotalLOC;
	private int iItems;
	private int iBase;
	private int iRemoved;
	private int iModified;
	private int iAdded;

	//&i
	/**
	 * CodePart name and content constructor
	 * 
	 * Initializes a CodePart object with the specified name and content.
	 * 
	 * @param sFileName with a file name with extension.
	 */
	public CodePart(String sName, String sContent) {
		
		this.sPartName = sName;
		this.sContent = sContent;
		this.parParser = new Parser();
		this.iBase = 0;
		this.iModified = 0;
		this.iRemoved = 0;
		this.iAdded = 0;
		this.iTotalLOC = 0;
		this.iItems = 0;
		this.sType = "";
		
	}

	//&i
	/**
	 * parsePart
	 * 
	 * Parses the string filling the information of this object.
	 * The information is: (iTotalLOC, iItems, iBase, iRemoved, iModified, iAdded);
	 * Based on the information gathered, it also sets the sType to one of these different 
	 * values: ("ADDED", "BASE", "REUSED")
	 * 
	 */
	public void parsePart() {
		String tmpContent = this.sContent;
		// Sum(x) para todas las etiquetas //&b=x y asignar resultado a variable con líneas base
		this.iBase = parParser.sumBTags(tmpContent);
		// Contar todas las etiquetas //&i y asignar resultado a variable con items
		this.iItems = parParser.countITags(tmpContent);
		// Contar todas las etiquetas //&m y asignar resultado a variable con líneas modificadas
		this.iModified = parParser.countMTags(tmpContent);
		// Sum(x) para todas las etiquetas //&d=x y asignar resultado a variable con iRemoved
		this.iRemoved = parParser.sumDTags(tmpContent);
		// Determinar el tipo de la parte y asignarlo a sType
		if (iBase == 0) {
			
			this.sType = "ADDED";
			
		}else if (iModified == 0 && iRemoved == 0) {
			
			this.sType = "REUSED";
			
		}else {
			
			this.sType = "BASE";
			
		}
		
		// Eliminar todas las etiquetas de tmpContent
		tmpContent = parParser.removeTags(tmpContent);
		// Contar el total de líneas de código de tmpContent
		this.iTotalLOC = parParser.countInfoLines(tmpContent);
		// Calcular iAdded Total - Base + Deleted
		if (this.sType == "BASE"){
			this.iAdded = this.iTotalLOC - this.iBase + this.iRemoved;
		}

	}

	//&i
	/**
	 * toString
	 * 
	 * Contains the string representation of a CodePart object.
	 * 
	 * @return <code>String</code> variable with the String representation
	 * of the object. 
	 */
	public String toString() {
		
		String sFormat = "%s: T=%d, I=%d";
		String sRepresentation; 
		
		// Set next part of string depending on its type
		
		/*  If it is not ADDED, then it is either BASE or REUSED. And they 
		both have in common the string B=%d */
		if (sType != "ADDED") {
			
			sFormat += ", B=%d";
			
			// If it is base, then I need to add the rest of the format
			if (sType == "BASE") {
				
				sFormat += ", D=%d, M=%d, A=%d";
				sRepresentation = String.format(sFormat, this.sPartName, this.iTotalLOC, this.iItems, this.iBase, this.iRemoved, this.iModified, this.iAdded);
				
			} else {
				
				sRepresentation = String.format(sFormat, this.sPartName, this.iTotalLOC, this.iItems, this.iBase);
				
			}
			
		} else {
			
			sRepresentation = String.format(sFormat, this.sPartName, this.iTotalLOC, this.iItems);
			
		}
		
		return sRepresentation;
		
	}

	//&i
	/**
	 * @return the sPartName
	 */
	public String getsPartName() {
		return sPartName;
	}

	//&i
	/**
	 * @param sPartName the sPartName to set
	 */
	public void setsPartName(String sPartName) {
		this.sPartName = sPartName;
	}

	//&i
	/**
	 * @return the sContent
	 */
	public String getsContent() {
		return sContent;
	}

	//&i
	/**
	 * @param sContent the sContent to set
	 */
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}

	//&i
	/**
	 * @return the sType
	 */
	public String getsType() {
		return sType;
	}

	//&i
	/**
	 * @param sType the sType to set
	 */
	public void setsType(String sType) {
		this.sType = sType;
	}

	//&i
	/**
	 * @return the parParser
	 */
	public Parser getParParser() {
		return parParser;
	}

	//&i
	/**
	 * @param parParser the parParser to set
	 */
	public void setParParser(Parser parParser) {
		this.parParser = parParser;
	}

	//&i
	/**
	 * @return the iTotalLOC
	 */
	public int getiTotalLOC() {
		return iTotalLOC;
	}

	//&i
	/**
	 * @param iTotalLOC the iTotalLOC to set
	 */
	public void setiTotalLOC(int iTotalLOC) {
		this.iTotalLOC = iTotalLOC;
	}

	//&i
	/**
	 * @return the iItems
	 */
	public int getiItems() {
		return iItems;
	}

	//&i
	/**
	 * @param iItems the iItems to set
	 */
	public void setiItems(int iItems) {
		this.iItems = iItems;
	}

	//&i
	/**
	 * @return the iBase
	 */
	public int getiBase() {
		return iBase;
	}

	//&i
	/**
	 * @param iBase the iBase to set
	 */
	public void setiBase(int iBase) {
		this.iBase = iBase;
	}

	//&i
	/**
	 * @return the iRemoved
	 */
	public int getiRemoved() {
		return iRemoved;
	}

	//&i
	/**
	 * @param iRemoved the iRemoved to set
	 */
	public void setiRemoved(int iRemoved) {
		this.iRemoved = iRemoved;
	}

	//&i
	/**
	 * @return the iModified
	 */
	public int getiModified() {
		return iModified;
	}

	//&i
	/**
	 * @param iModified the iModified to set
	 */
	public void setiModified(int iModified) {
		this.iModified = iModified;
	}

	//&i
	/**
	 * @return the iAdded
	 */
	public int getiAdded() {
		return iAdded;
	}

	//&i
	/**
	 * @param iAdded the iAdded to set
	 */
	public void setiAdded(int iAdded) {
		this.iAdded = iAdded;
	}
	
	
	
	
}
