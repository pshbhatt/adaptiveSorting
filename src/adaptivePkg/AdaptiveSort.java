package adaptivePkg;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class AdaptiveSort {
static String finalResult;
static String algorithm;

	public static void main(String[] args) throws Exception {
		PrintStream fileStream1 = new PrintStream(new File("adaptiveExecution.txt"));
		MergeSort ms = new MergeSort();
		QuickSort qs = new QuickSort();
		BucketSort bs = new BucketSort();
		TupleClass tc = new TupleClass();
		Integer[] arry = null;
		ms.mergeSorting("I",arry,tc);
		qs.quickSorting("I",arry,tc);
		bs.bucketSorting("I",arry,tc);
		int diceRoll;
		ArrayList<Integer> dice = new ArrayList<Integer>(); 
		for(int i=1;i<7;i++){
			dice.add(i);
		}
		
		Collections.shuffle(dice);
		diceRoll = dice.get(0);
		ProvideArray pa = new ProvideArray();
		Integer elementList[] = pa.arrayToSort();
		
		if(diceRoll == 1 || diceRoll == 4){
			long firstTimeStart = System.nanoTime();
			ms.mergeSorting("A", elementList, tc);
			long firstTimeEnd = System.nanoTime();
			fileStream1.println("Execution time for First Algorithm: " + (firstTimeEnd-firstTimeStart));
			
				algorithm = "Merge";
		} else if(diceRoll == 2|| diceRoll == 5){
			long firstTimeStart = System.nanoTime();
			qs.quickSorting("A", elementList,tc);
			long firstTimeEnd = System.nanoTime();
			fileStream1.println("Execution time for First Algorithm: " + (firstTimeEnd-firstTimeStart));
			algorithm = "Quick";
		} else {
			long firstTimeStart = System.nanoTime();
			bs.bucketSorting("A",elementList,tc);
			long firstTimeEnd = System.nanoTime();
			fileStream1.println("Execution time for First Algorithm: " + (firstTimeEnd-firstTimeStart));
			algorithm = "Bucket";
		}
		long adaptiveStartTime= System.nanoTime();
		if(tc.getFinalResult().equals("Switch")){
			if(algorithm.equals("Merge")){
				long firstTimeStart = System.nanoTime();
				Integer newArray[] = (Integer[]) tc.getSortedList().toArray(new Integer[tc.getSortedList().size()]);
				qs.quickSorting("A",newArray,tc);
				long firstTimeEnd = System.nanoTime();
				fileStream1.println("Execution time for Second Algorithm: " + (firstTimeEnd-firstTimeStart));
				if(tc.getFinalResult().equals("Switch")){
					long firstTimeStart1 = System.nanoTime();
					Integer newArray1[] = (Integer[]) tc.getSortedList().toArray(new Integer[tc.getSortedList().size()]);
					bs.bucketSorting("A", newArray1, tc);
					long firstTimeEnd1 = System.nanoTime();
					fileStream1.println("Execution time for Third Algorithm: " + (firstTimeEnd1-firstTimeStart1));
				}
				
				
			}
			if(algorithm.equals("Quick")){
				long firstTimeStart = System.nanoTime();
				Integer newArray[] = (Integer[]) tc.getSortedList().toArray(new Integer[tc.getSortedList().size()]);
				ms.mergeSorting("A",newArray,tc);
				long firstTimeEnd = System.nanoTime();
				fileStream1.println("Execution time for Second Algorithm: " + (firstTimeEnd-firstTimeStart));
				if(tc.getFinalResult().equals("Switch")){
					long firstTimeStart1 = System.nanoTime();
					Integer newArray1[] = (Integer[]) tc.getSortedList().toArray(new Integer[tc.getSortedList().size()]);
					bs.bucketSorting("A", newArray1, tc);
					long firstTimeEnd1 = System.nanoTime();
					fileStream1.println("Execution time for Third Algorithm: " + (firstTimeEnd1-firstTimeStart1));
					}
				}
				
			
			if(algorithm.equals("Bucket")){
				long firstTimeStart = System.nanoTime();
				Integer newArray[] = (Integer[]) tc.getSortedList().toArray(new Integer[tc.getSortedList().size()]);
				qs.quickSorting("A",newArray,tc);
				long firstTimeEnd = System.nanoTime();
				fileStream1.println("Execution time for Second Algorithm: " + (firstTimeEnd-firstTimeStart));
				if(tc.getFinalResult().equals("Switch")){
					long firstTimeStart1 = System.nanoTime();
					Integer newArray1[] = (Integer[]) tc.getSortedList().toArray(new Integer[tc.getSortedList().size()]);
					ms.mergeSorting("A", newArray1, tc);
					long firstTimeEnd1 = System.nanoTime();
					fileStream1.println("Execution time for Third Algorithm: " + (firstTimeEnd1-firstTimeStart1));
				}
			}
		}
		long adaptiveEndTime = System.nanoTime();
		System.out.println("The result for adaptiveSorting: ");
		for(int i=0;i<tc.getSortedList().size();i++){
			System.out.println(qs.quickArray[i]);
		}
		System.out.println("The execution time for adaptive sorting: " + (adaptiveEndTime - adaptiveStartTime));
		System.out.println("The asymptotic complexity for adaptive sorting: " + (ms.resultAsym + qs.resultAsym + bs.resultAsym)/3);
		fileStream1.close();
	}
}
