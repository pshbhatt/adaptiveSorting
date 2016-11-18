package adaptivePkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {
	private Integer array[];
	private Integer array1[];
	static Integer quickArray[];
    private int length;
    private int length1;
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
	
    /**
     * 
     * @param identifier
     * @param input
     * @param tc
     * @return
     * @throws IOException
     */
    public String quickSorting(String identifier, Integer[] input, TupleClass tc) throws IOException{
    	
        QuickSort qs = new QuickSort();
        Integer[] arrayToSort = null;
        if(identifier.equals("I")){
    		ProvideArray pa = new ProvideArray();
    		arrayToSort = pa.arrayToSort();
    		}
    		if(identifier.equals("A")){
    			arrayToSort = input;
    		}
        long startTime  = System.nanoTime();
        Integer sortedList[] = qs.sort(arrayToSort,identifier);
        if(identifier.equals("A")){
        tc.setFinalResult(finalResult);
        ArrayList<Integer> sort = new ArrayList<Integer>(Arrays.asList(sortedList));
        tc.setSortedList(sort);
        }
        long executionTime = System.nanoTime()-startTime;
        if(identifier.equals("I")){
    		System.out.println("Output for Individual Quick Sort: ");
    		quickArray =  sortedList;
        	for(int i=0;i<sortedList.length;i++){
        		
        		System.out.println(sortedList[i]);
        	}
        	System.out.println("Execution Time for Quick Sort: " + executionTime);
        	resultAsym = count*(Math.log(count)/Math.log(2));
        	System.out.println("Asymptotic Complexity for Quick Sort: " + resultAsym);
    	}
        return finalResult;
    }
    public Integer[] sort(Integer[] inputArr, String identifier) throws FileNotFoundException {
    	PrintStream fileStream = new PrintStream(new File("quickResults.txt"));
    	PrintStream fileStream1 = new PrintStream(new File("executionTimesQuick.txt"));
        if (inputArr == null || inputArr.length == 0) {
            return null;
        }
        if(identifier.equals("A")){
        	this.array1 = inputArr;
            length1 = inputArr.length;
            quickSort(0, length1 - 1, identifier, fileStream, fileStream1);
            return array1;
        } else {
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1, identifier, fileStream, fileStream1);
        return inputArr;
        }
    }
 
    private void quickSort(int lowerIndex, int higherIndex, String identifier, PrintStream fileStream, PrintStream fileStream1) throws FileNotFoundException {
         
        int i = lowerIndex;
        int j = higherIndex;
        int pivot;
        String scenario;
        
        if(identifier.equals("I")){
         pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        } else {
        pivot = array1[lowerIndex+(higherIndex-lowerIndex)/2];
        }
        
        while (i <= j) {
        	complexity = "n";
        	count = count+1;
        	long loopStartTime = System.nanoTime();
            if(identifier.equals("I")){
        	while (array[i] < pivot) {
            	
            	complexity = complexity + "log(n)";
                i++;
            }
            
            while (array[j] > pivot) {
            	complexity = complexity + "log(n)";
                j--;
            }
            } else {
            	while (array1[i] < pivot) {
                	
                	complexity = complexity + "log(n)";
                    i++;
                }
                
                while (array1[j] > pivot) {
                	complexity = complexity + "log(n)";
                    j--;
                }
            }
            long loopEndTime = System.nanoTime();
            
                
                
                executionTime  = executionTime + (loopEndTime-loopStartTime);
                count = count+1;
                if(identifier.equals("A")){
                if(count%100==0){
                	if(count/100==1){
                	resultAsym = count*(Math.log(count)/Math.log(2));
                	executionTimeBefore = executionTime;
                	}else {
                		resultAfter = count*(Math.log(count)/Math.log(2));
                		
                		asympResult = resultAsym/resultAfter;
                		fileStream.print("\nAsymptotic Result:" + asympResult);
                		resultAsym = count*(Math.log(count)/Math.log(2));
                		fileStream1.println("Asymptotic: " + resultAsym);
                		fileStream1.println("Executiom Times: " + executionTime);
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
            
            if (i <= j) {
            	
                exchangeNumbers(i, j, identifier);
                i++;
                j--;
            }
            if(finalResult.equals("Switch")){
            	break;
            } else {
            	continue;
            }
        }
        
        if (lowerIndex < j)
            quickSort(lowerIndex, j, identifier, fileStream, fileStream1);
        if (i < higherIndex)
            quickSort(i, higherIndex, identifier, fileStream, fileStream1);
        fileStream.close();
        fileStream1.close();
    }
 
    private void exchangeNumbers(int i, int j, String identifier) {
        if(identifier.equals("I")){
    	int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        } else {
        	int temp = array1[i];
            array1[i] = array1[j];
            array1[j] = temp;
        }
    }
     
   
}
