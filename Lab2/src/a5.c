#include <stdio.h>

/* README
 * A program which implements a function which takes an array and its length
 * and sorts the array by putting the negative numbers on the smallest indices
 * and the positive on the largest.
*/

//Define the functions
int * signSort();
void swap(int *a, int *b);

int main(){

  //Define a integer array and sort it
  int a[] = {1, -2, -3, 4, -2, 1, -4, 2, 2, -2};
  signSort(a, sizeof(a)/sizeof(int));

  for(int i = 0; i < sizeof(a)/sizeof(int); i++){
    printf("%d ", a[i]);
  }
  return 0;
}

int * signSort(int * a, int arrLength){

  int count = 0;
  for(int i = 0; i < arrLength; i++){
    //Invariant: a[0-i] is divided between positive and negative numbers

    //If a negative number is found, swap it with the first non-sorted element
    if(a[i] < 0){
      swap(&a[count++], &a[i]);
    }
  }
}

//A function which swaps two integer pointers.
void swap(int *a, int *b){
  int temp = *a;
  *a = *b;
  *b = temp;
}
