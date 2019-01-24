import java.util.Scanner;

/* README
 * An implementation of the algorithm Selection Sort. The program asks the user for the size of the input and then
 * the input integers. It will sort the integers in descending order and print out every step of the sorting process.
 */

public class a2 {

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
		
		//Loop through the whole array
		for(int i = 0; i < a.length; i++) {
			//Assume that the first unsorted element is the largest
			int max = i;
			//Loop through the unsorted part of the array and find the largest object
			//Added ! in front of lessThan to create descending order
			for(int j = i+1; j < a.length; j++) {
				if(!lessThan(a[j], a[max])) {
					max = j;
				}
			}
			//Swap the first unsorted object with the largest of the unsorted, indexes [0-i] are now sorted
			swap(a, i, max);
			//Print out the current state of the array
			System.out.print("Current state of the array: ");
			for(Comparable c: a) {
				System.out.print(c);
			}
			System.out.print('\n');
		}
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

