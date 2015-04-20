package mx.itesm.A01139626.p5.src;

import java.util.regex.Pattern;
//&p-AreaUnderTDistribution
//&b=68
public class AreaUnderTDistribution implements ErrorMessages {
	private double dX;
	private int iDof;
	private double dP;
	
	//&i
	/**
	 * Empty constructor
	 */
	public AreaUnderTDistribution() {
		super();
	}

	//&i
	/**
	 * Constructor with values
	 * @param dX
	 * @param iDof
	 */
	public AreaUnderTDistribution(double dX, int iDof, double dP) { //&m
		super();
		this.dX = dX;
		this.iDof = iDof;
		this.dP = dP;
	}

	//&i
	/**
	 * @return the dX
	 */
	public double getdX() {
		return dX;
	}

	//&i
	/**
	 * @param dX the dX to set
	 */
	public void setdX(double dX) {
		this.dX = dX;
	}

	//&i
	/**
	 * @return the iDof
	 */
	public int getiDof() {
		return iDof;
	}

	//&i
	/**
	 * @param iDof the iDof to set
	 */
	public void setiDof(int iDof) {
		this.iDof = iDof;
	}

	//&i
	/**
	 * @return the dP
	 */
	public double getdP() {
		return dP;
	}

	//&i
	/**
	 * @param dP the dP to set
	 */
	public void setdP(double dP) {
		this.dP = dP;
	}
	
	//&i
	/**
	 * toString
	 * 
	 * @return the <code>String</code> representation of the object.
	 */
	public String toString() {
		
		String sFormat;
		sFormat = "p   = %.10f\ndof = %d\nx   = %.10f"; //&m
		return String.format(sFormat, getdP(), getiDof(), getdX()); //&m
		
	}
	
	//&i
	/**
	 * 
	 * gamma
	 * 
	 * @param dX of type <code>double</code> the value for which gamma is to be given
	 * @return the value of type <code>double</code> for gamma given the parameter dX
	 */
	public double gamma(double dX) {
		
		if ( dX == 1 ) {
			return 1.0;
		} else if (dX == 0.5) {
			return Math.sqrt(Math.PI);
		} else {
			return (dX - 1) * gamma(dX - 1);
		}
		
	}

	//&i
	/**
	 * 
	 * tStudent
	 * 
	 * @param dX of type <code>double</code> the value for which tStudent is to be given
	 * @return the value of type <code>double</code> for tStudent given the parameter dX
	 */
	public double tStudent(double dX) {
		
		return ( gamma( (iDof + 1) / 2.0 ) / ( Math.pow((iDof*Math.PI),0.5) * gamma( iDof / 2.0 ) ) ) * Math.pow( ( 1 + (Math.pow(dX, 2) / iDof) ), ( (iDof + 1)/(-2.0) ) );
		
	}
	
	//&i
	/**
	 * 
	 * simpson
	 * 
	 * calculates a simpson iteration of a simpson integration
	 * 
	 * @param dX of type <code>double</code> the value for which simpson iteration is to be given
	 * @param dW of type <code>double</code> width of each simpson segment
	 * @param iNumSeg of type <code>int</code>
	 * 
	 * @return the value of type <code>double</code> for simpson given the parameter dX
	 */
	public double simpson(double dX, double dW, int iNumSeg) {
		
		double dSum4W = 0.0;
		double dSum2W = 0.0;
		
		int iCont = 1;
		while ( iCont <= (iNumSeg-1) ) {
			dSum4W += 4 * tStudent( iCont * dW );
			iCont += 2;
		}
		
		iCont = 2;
		while ( iCont <= (iNumSeg-2) ) {
			dSum2W += 2 * tStudent( iCont * dW );
			iCont += 2;
		}
		
		return ( dW / 3 ) * ( tStudent(0) + dSum4W + dSum2W + tStudent(dX) );
		
	}

	//&i
	/**
	 * 
	 * calculate
	 * 
	 * calculates a area under tdistribution described by dX and iDof using Simpson's method
	 * 
	 */
	public void calculate() {
		
		int iNumSeg = 8;
		double dW = dX / iNumSeg;
		double dE = 0.0000001;
		double dPreviousP;
		
		dP = simpson(dX, dW, iNumSeg);
		do {
			
			dPreviousP = dP;
			iNumSeg *= 2;
			dW = dX / iNumSeg;
			dP = simpson(dX, dW, iNumSeg);
			
		} while ( Math.abs(dP - dPreviousP) > dE );
		
	}
	
	//&i
	/**
	 * calculateX
	 * 
	 * calculates the dX that corresponds the current value of dP guessing values for
	 * dX until dTargetP is close enough (determined by an error double variable) to dP
	 */
	public void calculateX() {
	
		double dTargetP = getdP();
		double dError = 0.000000001;
		double dD = 0.5; 
		
		setdX(1.0);
		calculate();
		double dPreviousError = dTargetP - getdP();
		
		while ( Math.abs(dTargetP - getdP()) > dError ) {
			setdX( ( getdP() > dTargetP ) ? (getdX() - dD) : (getdX() + dD) );
			calculate();
			dD = ( dPreviousError * (dTargetP - getdP()) < 0 ) ? (dD / 2) : dD; 
			dPreviousError = dTargetP - getdP(); 
		}
		
	}
	
	//&i
	/**
	 * main
	 * 
	 * Contains the main logic of the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		AreaUnderTDistribution areXCalculator = new AreaUnderTDistribution(); //&m
		IOHandler ioHandler = new IOHandler(); 
		
		String sP = ioHandler.readValue("Introduce el valor del área para la cuál se calculará x: (debe de ser numérico real, entre 0 y 0.5)", sINVALID_PVALUE, Pattern.compile("((0+)?(\\.(([0-4]\\d*)|50*)))|((0+)(\\.(([0-4]\\d*)|50*))?)")); //&m
		areXCalculator.setdP( Double.parseDouble(sP) ); //&m

		String sDof = ioHandler.readValue("Introduce el valor de los grados de libertad dof: (debe de ser numérico entero y mayor a 0)", sINVALID_INTEGER, Pattern.compile("\\d*[1-9]\\d*"));
		areXCalculator.setiDof( Integer.parseInt(sDof) ); //&m
		
		areXCalculator.calculateX(); //&m
		System.out.println(areXCalculator); //&m		
	}

}
