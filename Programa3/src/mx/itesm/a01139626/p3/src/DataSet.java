package mx.itesm.a01139626.p3.src;
//&p-DataSet
public class DataSet implements ErrorMessages {
	private double dXK;
	private double dSumX;
	private double dSumY;
	private double dSumXY;
	private double dSumX2;
	private double dSumY2;
	private int iN;
	private double dXAvg;
	private double dYAvg;
	private double dR;
	private double dR2;
	private double dB0;
	private double dB1;
	private double dYK;

	//&i
	/**
	 *  DataSet constructor to initialize all DataSet
	 *  values with the default 0 value. 
	 */
	public DataSet() {
		this.dXK = 0;
		this.dSumX = 0;
		this.dSumY = 0;
		this.dSumXY = 0;
		this.dSumX2 = 0;
		this.dSumY2 = 0;
		this.iN = 0;
		this.dXAvg = 0;
		this.dYAvg = 0;
		this.dR = 0;
		this.dR2 = 0;
		this.dB0 = 0;
		this.dB1 = 0;
		this.dYK = 0;
	}

	//&i
	/**
	 * addPair
	 * 
	 * This method adds a pair to the data set, modifying all 
	 * accumulators in the class, in order to calculate the correlation
	 * and the betas.
	 * 
	 * @param dX
	 * @param dY
	 */
	public void addPair(double dX, double dY) {
		dSumX += dX;
		dSumY += dY;
		dSumXY += dX * dY;
		dSumX2 += Math.pow(dX, 2);
		dSumY2 += Math.pow(dY, 2);
		iN += 1;
	}

	//&i
	/**
	 * Calculate
	 * 
	 * Calculates all the statistical data of this dataset.
	 * Makes sure that the number of points in the DataSet is more
	 * than 1.
	 * 
	 * @return variable of type <code>boolean</code> that is true if the calculation was successful
	 */
	public boolean calculate() {
		if ( iN > 1 ) {
			// calculate dAvgX
			dXAvg = dSumX / iN;
			
			// calculate dAvgY
			dYAvg = dSumY / iN;
			
			// calculate dB1
			dB1 = ( dSumXY - iN * dXAvg * dYAvg ) / ( dSumX2 - iN * Math.pow(dXAvg, 2) ); 
			
			// calculate dB0
			dB0 = dYAvg - dB1 * dXAvg; 
			
			// calculate dR
			dR = ( iN * dSumXY - dSumX * dSumY ) / Math.sqrt ( ( iN * dSumX2 - Math.pow(dSumX, 2) ) * ( iN * dSumY2 - Math.pow(dSumY, 2) ) );               
			
			// calculate dR2
			dR2 = Math.pow(dR, 2);
			
			// calculate dYK
			dYK = dB0 + dB1 * dXK;
			
			return true;
		} else {
			System.out.println(sEMPTY_DATA_SET);
			return false; 
		}
	}

	//&i
	/**
	 * @return the dXK
	 */
	public double getdXK() {
		return dXK;
	}

	//&i
	/**
	 * @param dXK the dXK to set
	 */
	public void setdXK(double dXK) {
		this.dXK = dXK;
	}

	//&i
	/**
	 * @return the dSumX
	 */
	public double getdSumX() {
		return dSumX;
	}

	//&i
	/**
	 * @param dSumX the dSumX to set
	 */
	public void setdSumX(double dSumX) {
		this.dSumX = dSumX;
	}

	//&i
	/**
	 * @return the dSumY
	 */
	public double getdSumY() {
		return dSumY;
	}

	//&i
	/**
	 * @param dSumY the dSumY to set
	 */
	public void setdSumY(double dSumY) {
		this.dSumY = dSumY;
	}

	//&i
	/**
	 * @return the dSumXY
	 */
	public double getdSumXY() {
		return dSumXY;
	}

	//&i
	/**
	 * @param dSumXY the dSumXY to set
	 */
	public void setdSumXY(double dSumXY) {
		this.dSumXY = dSumXY;
	}

	//&i
	/**
	 * @return the dSumX2
	 */
	public double getdSumX2() {
		return dSumX2;
	}

	//&i
	/**
	 * @param dSumX2 the dSumX2 to set
	 */
	public void setdSumX2(double dSumX2) {
		this.dSumX2 = dSumX2;
	}

	//&i
	/**
	 * @return the dSumY2
	 */
	public double getdSumY2() {
		return dSumY2;
	}

	//&i
	/**
	 * @param dSumY2 the dSumY2 to set
	 */
	public void setdSumY2(double dSumY2) {
		this.dSumY2 = dSumY2;
	}

	//&i
	/**
	 * @return the iN
	 */
	public int getiN() {
		return iN;
	}

	//&i
	/**
	 * @param iN the iN to set
	 */
	public void setiN(int iN) {
		this.iN = iN;
	}

	//&i
	/**
	 * @return the dXAvg
	 */
	public double getdXAvg() {
		return dXAvg;
	}

	//&i
	/**
	 * @param dXAvg the dXAvg to set
	 */
	public void setdXAvg(double dXAvg) {
		this.dXAvg = dXAvg;
	}

	//&i
	/**
	 * @return the dYAvg
	 */
	public double getdYAvg() {
		return dYAvg;
	}

	//&i
	/**
	 * @param dYAvg the dYAvg to set
	 */
	public void setdYAvg(double dYAvg) {
		this.dYAvg = dYAvg;
	}

	//&i
	/**
	 * @return the dR
	 */
	public double getdR() {
		return dR;
	}

	//&i
	/**
	 * @param dR the dR to set
	 */
	public void setdR(double dR) {
		this.dR = dR;
	}

	//&i
	/**
	 * @return the dR2
	 */
	public double getdR2() {
		return dR2;
	}

	//&i
	/**
	 * @param dR2 the dR2 to set
	 */
	public void setdR2(double dR2) {
		this.dR2 = dR2;
	}

	//&i
	/**
	 * @return the dB0
	 */
	public double getdB0() {
		return dB0;
	}

	//&i
	/**
	 * @param dB0 the dB0 to set
	 */
	public void setdB0(double dB0) {
		this.dB0 = dB0;
	}
	
	//&i
	/**
	 * @return the dB1
	 */
	public double getdB1() {
		return dB1;
	}

	//&i
	/**
	 * @param dB1 the dB1 to set
	 */
	public void setdB1(double dB1) {
		this.dB1 = dB1;
	}

	//&i
	/**
	 * @return the dYK
	 */
	public double getdYK() {
		return dYK;
	}

	//&i
	/**
	 * @param dYK the dYK to set
	 */
	public void setdYK(double dYK) {
		this.dYK = dYK;
	}

	//&i
	public String toString() {
		String sFormat = "N  = %d\n"
					   + "xk = %.0f\n"
					   + "r  = %.5f\n"
					   + "r2 = %.5f\n"
					   + "b0 = %.5f\n"
					   + "b1 = %.5f\n"
					   + "yk = %.5f";
		return String.format(sFormat, getiN(), getdXK(), getdR(), getdR2(), getdB0(), getdB1(), getdYK());
	}
	
	
}
