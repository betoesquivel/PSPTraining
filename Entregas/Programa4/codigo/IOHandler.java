package mx.itesm.A01139626.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//&p-IOHandler
public class IOHandler implements ErrorMessages {
	private BufferedReader bufInput; 
	
	//&i
	public IOHandler() {
		//&b=2
		//&d
	}
	
	//&i
	public String readValue(String sPrompt, String sErrorMessage, Pattern patValidStructure){
		
		//&b=3
		String sValue = ""; //&m
		Matcher matValidator;
		
		bufInput = new BufferedReader( new InputStreamReader( System.in ) );
		
		System.out.println (sPrompt); //&m
		try{
			sValue = bufInput.readLine().trim();
		} catch (IOException e) {
			System.out.println (sIO_EXCEPTION);
		}
		matValidator = patValidStructure.matcher(sValue);
		while (!matValidator.matches()) {
			System.out.println (sErrorMessage);
			System.out.println (sPrompt);
			try{
				sValue = bufInput.readLine().trim();
			} catch (IOException e) {
				System.out.println (sIO_EXCEPTION);
			}
			matValidator = patValidStructure.matcher(sValue);
		}
		
		return sValue;
	}
}
