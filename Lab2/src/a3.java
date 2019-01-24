import java.util.Scanner;

public class a3 {
	
	/* README
	 * An implementation of the algorithm Selection Sort. The program asks the user for the size of the input and then
	 * the input integers. It will sort the integers in ascending order and print out every step of the sorting process.
	 * The program will also keep track of the amount of swaps made, and print that out to standard output.
	 */
	
public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//Read the size of the input from standard in
		System.out.print("Size of the array: ");
		int size = sc.nextInt();
		Integer[] a = new Integer[size];
		
		//Read all the input integers from standard in
		for(int i = 0; i < a.length; i++) {
			System.out.print("Give the " + (i+1) + " element: ");
			a[i] = sc.nextInt();
		}
		
		sc.close();
		
		//Perform the sorting
		selectionSort(a);
	}
	
	//A method which takes an array of comparable objects and sorts them using Selection Sort
	public static void selectionSort(Comparable[] a) {
		
		//Initialize variable to keep track of swaps
		int swapCount = 0;
		//Loop through the whole array
		for(int i = 0; i < a.length; i++) {
			//Assume that the first unsorted element is the smallest
			int min = i;
			//Loop through the unsorted part of the array and find the smallest object
			for(int j = i+1; j < a.length; j++) {
				if(lessThan(a[j], a[min])) {
					min = j;
				}
			}
			/* If the first unsorted element isn't the smallest of the unsorted elements, swap the first element
			 * with the smallest, and increment the swap counter.
			 */
			if(i != min) {
				swap(a, i, min);
				swapCount++;
			}
			//Print out the current state of the array
			System.out.print("Current state of array: ");
			for(Comparable c: a) {
				System.out.print(c);
			}
			System.out.print('\n');
			
		}
		System.out.println("Number of swaps: " + swapCount);
	}
	
	//Return true if a is less than b, otherwise return false
	public static boolean lessThan(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	//Swap the elements with the given indexes of the given array
	public static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
