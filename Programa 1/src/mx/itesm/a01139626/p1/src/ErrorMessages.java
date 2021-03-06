package mx.itesm.a01139626.p1.src;
//&p-ErrorMessages
public interface ErrorMessages {
	public final String sFILE_NOT_FOUND = "Al menos uno de los archivos no fue encontrado.\n"
			+ "Por favor introduzca solamente nombres de archivos"
			+ " que existan.";
	
	public final String sIO_EXCEPTION = "Hubo problema con la lectura del archivo.\n"
			+ "Por favor verifique que el archivo sea válido.";
	
	public final String sINVALID_FILE_NAME = "Al menos uno de los nombres de los archivos es invalido.\n"
			+ "Recuerde agregar la extension del archivo en el nombre (solo txt).\n"
			+ "Intente de nuevo.";
	
	public final String sINVALID_INTEGER = "El valor no es numerico. Intente de nuevo.";
}
