package mx.itesm.a01139626.p6.src;
//&p-DataSetAnalyzer
public class DataSetAnalyzer {
	private String sFileName;
	private DataSet datSet; 
	//&i
	public DataSetAnalyzer() {
		this.sFileName = "";
		this.datSet = new DataSet();
	}
	
	//&i
	public boolean analyze() {

		IOHandler iohIn = new IOHandler();
		
		if ( iohIn.readDataSetFromFile(getsFileName(), getDatSet() ) ) {
			
			return getDatSet().calculate();
			
		} else {
			
			return false;
			
		}
		
	}

	//&i
	/**
	 * @return the sFileName
	 */
	public String getsFileName() {
		return sFileName;
	}

	//&i
	/**
	 * @param sFileName the sFileName to set
	 */
	public void setsFileName(String sFileName) {
		this.sFileName = sFileName;
	}

	//&i
	/**
	 * @return the datSet
	 */
	public DataSet getDatSet() {
		return datSet;
	}

	//&i
	/**
	 * @param datSet the datSet to set
	 */
	public void setDatSet(DataSet datSet) {
		this.datSet = datSet;
	}

	//&i
	public static void main(String[] args) {
		
		DataSetAnalyzer datAnalyzer = new DataSetAnalyzer();
		IOHandler iohIn = new IOHandler();
		
		datAnalyzer.setsFileName ( iohIn.readFileName() );
		
		if ( datAnalyzer.analyze() ) {
			
			System.out.println ( datAnalyzer.getDatSet() );
			
		}
		
	}

}
