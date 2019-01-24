#include <stdio.h>

/* README:
  Two functions which reads characters from standard input until
  the user presses enter and outputs the characters to standrad
  output in reverse order. One function solves this problem
  recursively and one iteratively.
  */

//Define recursive method
void recursiveReverseChars();
void iterativeReverseChars();

int main(){
  recursiveReverseChars();
  putchar('\n');
  iterativeReverseChars();
  return 0;
}

void recursiveReverseChars(){

  // Read the next character written to standard input and save it in the variable a
  char a;
  a = getchar();

  /* If the character is not a new line character, call the method recursivly
  and read another character */
  if(a != '\n'){
    recursiveReverseChars();
  }

  /* Whenever a new line character is read, the characters are printed out in reverse
  order because of the recursive order they were read in. */
  putchar(a);
}

void iterativeReverseChars(){

  //Allocate memory space for the cahracters
  int chars[32];
  int i;
  int count = 0;

  //Read characters from standard in until a new line apperas and keep track of how many is read using count
  for(i = 0; i < sizeof(chars)/sizeof(int); i++){
    char b = getchar();
    if(b != '\n'){
      chars[i] = b;
      count++;
    }
    else{
      break;
    }
  }

  //Print out the chars in reverse order
  for(i = count-1; i >= 0; i--){
    putchar(chars[i]);
  }

}
