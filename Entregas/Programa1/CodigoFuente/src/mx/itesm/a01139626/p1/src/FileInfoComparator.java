package mx.itesm.a01139626.p1.src;

import java.util.Comparator;

public class FileInfoComparator implements ErrorMessages, Comparator<FileInfo>{
    
	@Override
    public int compare(FileInfo f1, FileInfo f2) {
    	
    	if (f1.getiInfoLines() == f2.getiInfoLines()) return 0;
    	if(f1.getiInfoLines() > f2.getiInfoLines()){
    		return 1;
    	}else{
    		return -1;
    	}
    	
    }
    
}
