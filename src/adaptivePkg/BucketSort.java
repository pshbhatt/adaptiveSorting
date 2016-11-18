package adaptivePkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class BucketSort {
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
	public String bucketSorting(String identifier, Integer[] elementList, TupleClass tc) throws IOException {
		
		BucketSort bs = new BucketSort();
		int bucketCount = 5;
		Integer arrayToSort[] = null;
		if(identifier.equals("I")){
		ProvideArray pa = new ProvideArray();
		arrayToSort = pa.arrayToSort();
		}
		if(identifier.equals("A")){
			arrayToSort = elementList;
		}
		long startTime = System.nanoTime();
		Integer sortedArray[] = bs.bucketSort(arrayToSort, bucketCount, identifier);
		
		long executionTime = System.nanoTime() - startTime;
		if(identifier.equals("I")){
    		System.out.println("Output for Individual Bucket Sort: ");
        	for(int i=0;i<sortedArray.length;i++){
        		
        		System.out.println(sortedArray[i]);
        	}
        	System.out.println("Execution Time for Bucket Sort: " + executionTime);
        	resultAsym = (count+bucketCount);
        	System.out.println("Asymptotic Complexity for Bucket Sort: " + resultAsym);
    	}
		//System.out.println("execution Time: " + executionTime);
		if(identifier.equals("A")){
		tc.setFinalResult(finalResult);
		/*for(int i=0;i<sortedArray.length;i++){
			System.out.println(sortedArray[i]);
		}*/
		ArrayList<Integer> sort = new ArrayList<Integer>(Arrays.asList(sortedArray));
        tc.setSortedList(sort);
		}
		return finalResult;

	}

	public Integer[] bucketSort(Integer[] array, int bucketCount, String identifier) throws FileNotFoundException {
		String scenario;
        PrintStream fileStream = new PrintStream(new File("bucketResults.txt"));
        PrintStream fileStream1 = new PrintStream(new File("executionTimesBucket.txt"));
		if (bucketCount <= 0)
			throw new IllegalArgumentException("Invalid bucket count");
		if (array.length <= 1)
			return array;

		int high = array[0];
		int low = array[0];
		for (int i = 1; i < array.length; i++) { 
			if (array[i] > high)
				high = array[i];
			if (array[i] < low)
				low = array[i];
		}
		double interval = ((double) (high - low + 1)) / bucketCount;

		ArrayList<Integer> buckets[] = new ArrayList[bucketCount];
		for (int i = 0; i < bucketCount; i++) { 
			buckets[i] = new ArrayList<Integer>();
		}
		long loopStartTime = System.nanoTime();
		for (int i = 0; i < array.length; i++) { 
			count = count + 1;
			buckets[(int) ((array[i] - low) / interval)].add(array[i]);
		}
		int pointer = 0;
		for (int i = 0; i < buckets.length; i++) {
			count = count + 1;
			Collections.sort(buckets[i]); 
			for (int j = 0; j < buckets[i].size(); j++) {
				count = count +1;
				array[pointer] = buckets[i].get(j);
				pointer++;
				if(identifier.equals("A")){
				long loopEndTime = System.nanoTime();
				executionTime  = executionTime + (loopEndTime-loopStartTime);
				 if(count%100==0){
	                	if(count/100==1){
	                	resultAsym = (count+bucketCount);
	                	executionTimeBefore = executionTime;
	                	}else {
	                		resultAfter = (count+bucketCount);;
	                		fileStream1.println("Execution Time: " + executionTime);
	                		
	                		asympResult = resultAsym/resultAfter;
	                		fileStream.print("\nAsymptotic Result:" + asympResult);
	                		resultAsym = (count+bucketCount);
	                		fileStream1.println("Asymptotic Result: " + resultAsym);
	                		executionTimeAfter = executionTime;
	                		executionResult = executionTimeBefore/executionTimeAfter;
	                		fileStream.print("\nExecution Time:" + executionResult);
	                		if(executionResult>asympResult){
	                			scenario = "Worse";
	                			finalResult = "Switch";
	                			System.out.println(scenario);
	                			break;
	                		} else if(executionResult<asympResult){
	                			scenario = "Best";
	                			//System.out.println(scenario);
	                		} else {
	                			scenario = "Average";
	                			//System.out.println(scenario);
	                		}
	                		executionTimeBefore = executionTimeAfter;
	                	}
	                }
				}
			}
			fileStream.close();
			if(finalResult.equals("Switch")){
				break;
			} else {
				continue;
			}
		}
		
		return array;
	}

}
