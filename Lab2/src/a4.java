import java.util.Scanner;

/* README
 * An implementation of the algorithm Selection Sort. The program asks the user for the size of the input and then
 * the input integers. It will sort the integers in ascending order and print out every step of the sorting process.
 * The program will also check the amount of inversions of the given array, and print these out to standard output.
 */

public class a4 {
	
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
		
		//Check how many inversions the given array has, then perform the sorting
		checkInversions(a);
		selectionSort(a);
	}
	
	//A method which takes an array of comparable objects and sorts them using Selection Sort
	public static void selectionSort(Comparable[] a) {
		
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
			if(i != min) {
				swap(a, i, min);
				swapCount++;
			}
			//Print out the current state of the array
			System.out.print("Current state of the array: ");	
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
	
	//A method to check the amount of inversions in a comparable array, and prints these out to standard output
	public static void checkInversions(Comparable[] a) {
		
		int inversionCount = 0;
		/* For every element, compare it to the elements with higher index, if one of these are smaller than the
		 * original element, there is an inversion and we print that out to standard output. We also increment the 
		 * inversion counter.
		 */
		for(int i = 0; i < a.length-1; i++) {
			for(int j = i+1; j < a.length; j++) {
				if(lessThan(a[j], a[i])) {
					System.out.print("[[" + i + "," + a[i] + "]-[" + j + "," + a[j] + "]], ");
					inversionCount++;
				}
			}
		}
		
		//Print out the amount of inversions
		System.out.println("\nTotal number of inversions: " + inversionCount);
		
	}

}
