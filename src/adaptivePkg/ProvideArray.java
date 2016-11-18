package adaptivePkg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProvideArray {

	public Integer[] arrayToSort() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("file.txt"));
		 String str;

	        List<Integer> list = new ArrayList<Integer>();
	        while((str = in.readLine()) != null){
	            try{
	        	list.add(Integer.parseInt(str));
	            } catch(NumberFormatException e){
	            	continue;
	            }
	        }
	        Integer[] toSortArray = new Integer[list.size()];
	        for(int i = 0;i < toSortArray.length;i++){
	        	toSortArray[i] = list.get(i);
	        }
	       return toSortArray;
	 
	}
}
