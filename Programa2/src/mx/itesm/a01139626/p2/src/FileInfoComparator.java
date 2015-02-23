package mx.itesm.a01139626.p2.src;
//&p-FileInfoComparator
//&b=9
import java.util.Comparator;

public class FileInfoComparator implements ErrorMessages, Comparator<FileInfo>{

	//&i
	@Override
    public int compare(FileInfo f1, FileInfo f2) {
    	
    	if (f1.getiTotalLOC() == f2.getiTotalLOC()) return 0; //&m
    	if(f1.getiTotalLOC() > f2.getiTotalLOC()){ //&m
    		return 1;
    	}else{
    		return -1;
    	}
    	
    }
    
}
