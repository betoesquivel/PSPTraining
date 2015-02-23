package mx.itesm.a01139626.p2.src;
//&p-CodePart
public class CodePart {
	
	private String sPartName;
	private String sContent;
	private String sType;
	
	private int iTotalLOC;
	private int iItems;
	private int iBase;
	private int iRemoved;
	private int iModified;
	private int iAdded;
	
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
		
	}
	
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
	
		// Sum(x) para todas las etiquetas //&b=x y asignar resultado a variable con líneas base
		
		// Contar todas las etiquetas //&i y asignar resultado a variable con items
		
		// Contar todas las etiquetas //&m y asignar resultado a variable con líneas modificadas
		
		// Sum(x) para todas las etiquetas //&d=x y asignar resultado a variable con iRemoved
		
		// Determinar el tipo de la parte y asignarlo a sType
		
		// Guardar contenido content en otra variable para manipularlo mientras se parsea

		// Eliminar todas las etiquetas de tmpContent
		
		// Contar el total de líneas de código de tmpContent

	}
	
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
	
	
	
	
}
