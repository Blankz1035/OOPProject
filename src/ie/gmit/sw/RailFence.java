package ie.gmit.sw;

/* Basic implementation of the Rail Fence Cypher using a 2D char array 
 * Note that there are more efficient ways to encrypt and decrypt, but the following implementation illustrates the steps
 * involved in each process and shows how the zig-zagging works. Feel free to change / adapt. 
 */

public class RailFence {
	
	//***** Encrypt a String called cypherText using an integer key ***** 
	public String encrypt(String cypherText, int key){
		//Declare a 2D array of key rows and text length columns
		char[][] cypherMatrix = new char[key][cypherText.length()]; //The array filled with chars with initial values of zero, i.e. '0'.
		
		//Fill the array
		int row = 0; //Used to keep track of rows
		boolean downwards = true; //Used to zigzag
		for (int i = 0; i < cypherText.length(); i++){ //Loop over the plaintext
			cypherMatrix[row][i] = cypherText.charAt(i); //Add the next character in the plaintext to the array
			
			if (downwards){ //If we are moving downwards the array
				row++;
				if (row == cypherMatrix.length){ //Reached the bottom
					row = cypherMatrix.length - 2; //Move to the row above
					downwards = false; //Switch to moving up
				} 
			}else{ //We are moving up the array
				row--;
				
				if (row == -1){ //Reached the top
					row = 1; //Move to the first row
					downwards = true; //Switch to moving downwards
				}
			}
		}
		
		//printMatrix(cypherMatrix); //Output the cypherMatrix (debug)
		
		//Extract the cypher text
		StringBuffer stringBuf = new StringBuffer(); //A string buffer allows a string to be built efficiently
		for (row = 0; row < cypherMatrix.length; row++){ //Loop over each row in the cypherMatrix
			for (int col = 0; col < cypherMatrix[row].length; col++){ //Loop over each column in the cypherMatrix
				if (cypherMatrix[row][col] > '0') stringBuf.append(cypherMatrix[row][col]); //Extract the character at the row/col position if it's not 0.
			}
		}
		
		return stringBuf.toString(); //Convert StringBuffer to a String and return it
	}
	
	//***** Decrypt a String cypherText using an integer key ***** 
	public String decrypt(String cypherText, int key){
		//Declare a 2D array of key rows and text length columns
		char[][] cypherMatrix = new char[key][cypherText.length()]; //The array is filled with chars with initial values of zero, i.e. '0'.
		
		//Fill the array
		int targetRow = 0;
		int index = 0;
		do{
			int row = 0; //Used to keep track of rows		
			boolean downwards = true; //Method of zigzaging through the cypherMatrix
			for (int i = 0; i < cypherText.length(); i++){ //Looping the plaintext
				if (row == targetRow){
					cypherMatrix[row][i] = cypherText.charAt(index); //Add the next character in the plaintext to the array
					index++;
				}
				
				if (downwards){ //If moving downwards in the array
					row++;
					if (row == cypherMatrix.length){ //Reached the bottom
						row = cypherMatrix.length - 2; //Move to the row above
						downwards = false; //Switch to moving up
					} 
				}else{ //Navigating up the array
					row--;
					
					if (row == -1){ //Reached the top
						row = 1; //Navigate to the first row
						downwards = true; //Change to moving downwarsd
					}
				}
			}

			targetRow++;
		}while (targetRow < cypherMatrix.length);
		
		//printMatrix(cypherMatrix); //This can Output the cypherMatrix (debug)
		
		//Extract the text from the cypher
		StringBuffer stringBuf = new StringBuffer(); //A string buffer allows a string to be built efficiently
		int row = 0;
		boolean downwards = true; //Used to zigzag
		for (int col = 0; col < cypherMatrix[row].length; col++){ //Loop over each single column in the cypherMatrix
			stringBuf.append(cypherMatrix[row][col]); //Extract the character at any row or col position if it's not 0.
			
			if (downwards){ //If we are moving downwards the array
				row++;
				if (row == cypherMatrix.length){ //Reached the bottom
					row = cypherMatrix.length - 2; //Move to the row above
					downwards = false; //Switch to moving up
				} 
			}else{ //We are moving up the array
				row--;
				
				if (row == -1){ //At the Top
					row = 1; //Navigate to the first row
					downwards = true; //Change to moving Downwards
				}
			}

		}
		
		return stringBuf.toString(); //Convert StringBuffer into a String and return it
	}
	
	
	//***** Output the 2D array in CSV format ***** 
	private void printMatrix(char[][] cypherMatrix){
		for (int row = 0; row < cypherMatrix.length; row++){ //Loop over each row in the cypherMatrix
			for (int col = 0; col < cypherMatrix[row].length; col++){ //Loop over each column in the cypherMatrix
				System.out.print(cypherMatrix[row][col]); //Output the value at row/column index
				if (col < cypherMatrix[row].length - 1) System.out.print(",");
			}
			System.out.println();
		}
	}
	
	
}