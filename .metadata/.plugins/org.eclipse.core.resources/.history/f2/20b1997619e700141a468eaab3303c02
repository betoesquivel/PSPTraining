package mx.itesm.A01139626.p5.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//&p-IOHandler
//&b=22
public class IOHandler implements ErrorMessages {
	private BufferedReader bufInput; 
	
	//&i
	public IOHandler() {
	}
	
	//&i
	public String readValue(String sPrompt, String sErrorMessage, Pattern patValidStructure){
		
		String sValue = "";
		Matcher matValidator;
		
		bufInput = new BufferedReader( new InputStreamReader( System.in ) );
		
		System.out.println (sPrompt);
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
