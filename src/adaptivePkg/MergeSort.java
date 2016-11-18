package adaptivePkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
	static String complexity;
	static int count;
	
	static double resultAsym;
	static double resultAfter;
	static long executionTime;
	static double executionTimeBefore;
	static double executionTimeAfter;
	static double executionResult;
	static double asympResult;
	static String finalResult="";
	
	public Integer[] mergeSort(Integer [] list, String identifier, PrintStream fileStream,PrintStream fileStream1) throws FileNotFoundException {
        if (list.length <= 1) {
            return list;
        }
        
        if(identifier.equals("I")){
        Integer[] first = new Integer[list.length / 2];
        Integer[] second = new Integer[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);
        
        mergeSort(first, identifier, fileStream, fileStream1);
        mergeSort(second, identifier, fileStream, fileStream1);
        
        merge(first, second, list, identifier, fileStream, fileStream);
        return list;
        } else {
        	Integer[] first1 = new Integer[list.length / 2];
            Integer[] second1 = new Integer[list.length - first1.length];
            System.arraycopy(list, 0, first1, 0, first1.length);
            System.arraycopy(list, first1.length, second1, 0, second1.length);
            
            mergeSort(first1, identifier, fileStream, fileStream1);
            mergeSort(second1, identifier, fileStream, fileStream1);
            
            finalResult=merge(first1, second1, list, identifier, fileStream, fileStream1);
            return list;
        }
        
        
    }
    
    private static String merge(Integer[] first, Integer[] second, Integer [] result, String identifier, PrintStream fileStream, PrintStream fileStream1) throws FileNotFoundException {
       
    	int iFirst = 0;
        int iSecond = 0;
        int j = 0;
        complexity = "log(n)";
        String scenario;
        
       
        
        while (iFirst < first.length && iSecond < second.length) {
        	
            long loopStartTime = System.nanoTime();
        	if (first[iFirst] < second[iSecond]) {
                result[j] = first[iFirst];
                iFirst++;
                } else {
                result[j] = second[iSecond];
                iSecond++;
                
                
            }
        	
            j++;
            
            long loopEndTime = System.nanoTime();
            //System.out.println("count: "+count);
            executionTime  = executionTime + (loopEndTime-loopStartTime);
            
            count = count+1;
            if(identifier.equals("A")){
            if(count%100==0){
            	if(count/100==1){
            	resultAsym = count*(Math.log(count)/Math.log(2));
            	executionTimeBefore = executionTime;
            	}else {
            		fileStream1.println("Execution Time: " + executionTime);
            		
            		resultAfter = count*(Math.log(count)/Math.log(2));
            		
            		asympResult = resultAsym/resultAfter;
            		fileStream.print("\nAsymptotic Result:" + asympResult);
            		resultAsym = count*(Math.log(count)/Math.log(2));
            		fileStream1.println("Asymptotic Result: " + resultAsym);
            		executionTimeAfter = executionTime;
            		executionResult = executionTimeBefore/executionTimeAfter;
            		fileStream.print("\nExecution Time:" + executionResult);
            		if(executionResult>asympResult){
            			scenario = "Worse";
            			finalResult = "Switch";
            			break;
            		} else if(executionResult<asympResult){
            			scenario = "Best";
            		} else {
            			scenario = "Average";
            		}
            		executionTimeBefore = executionTimeAfter;
            	}
            }
            
        }
            
            if(finalResult.equals("Switch")){
            	break;
            } else {
            	continue;
            }
        }
        
        System.arraycopy(first, iFirst, result, j, first.length - iFirst);
        System.arraycopy(second, iSecond, result, j, second.length - iSecond);
		return finalResult;
    }

    public void mergeSorting(String identifier,Integer elementlist[], TupleClass tc) throws Exception
    {
        MergeSort ms= new MergeSort();
        PrintStream fileStream = new PrintStream(new File("mergeResults.txt"));
        PrintStream fileStream1 = new PrintStream(new File("executionTimesMerge.txt"));
        Integer[] arrayToSort = null;
        if(identifier.equals("I")){
    		ProvideArray pa = new ProvideArray();
    		arrayToSort = pa.arrayToSort();
    		}
    		if(identifier.equals("A")){
    			arrayToSort = elementlist;
    		}
        long startTime  = System.nanoTime();
        Integer sortedList[]=ms.mergeSort(arrayToSort, identifier, fileStream, fileStream1);
        long executionTime = System.nanoTime()-startTime;
        if(identifier.equals("I")){
        	System.out.println("Output for Individual Merge Sort: ");
        	for(int i=0;i<sortedList.length;i++){
        		
        		System.out.println(sortedList[i]);
        	}
        	System.out.println("Execution Time for Merge Sort: " + executionTime);
        	resultAsym = count*(Math.log(count)/Math.log(2));
        	System.out.println("Asymptotic Complexity for Metge Sort: " + resultAsym);
        }
        //System.out.println("Complexity :" + complexity + "Count: " + count + "n");
        fileStream.close();
        fileStream1.close();
        if(identifier.equals("A")){
        tc.setFinalResult(finalResult);
        ArrayList<Integer> sort = new ArrayList<Integer>(Arrays.asList(sortedList));
        tc.setSortedList(sort);
        }
    }
}
