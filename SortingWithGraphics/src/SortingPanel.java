// Project: Sorting With Graphics.
// Written by: Mr. Swope
// Date: 3/16/20
// Description: This project illustrates the sorting of numbers from a text file using several sorting algorithms. Each number in the array is drawn
//              as a rectangle on the panel, were the height of the rectangle represents the size of the number - the bigger the number, the taller
//              the rectangle. There will also be two red rectangles drawn on the panel that will illustrate which two rectangles are currently being 
//              compared.
//
//				You will update this code by improving bubble sort and implementing selection sort, insertion sort and two other sorting algorithms of
//              your choice. Sorting algorithms like bogosort
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JPanel;

public class SortingPanel extends JPanel{

	// Constants used for the project. 
	private static final int WIDTH = 2200;					// The width of the panel
	private static final int HEIGHT = 700;					// The height of the panel

	private static final String FILE_NAME = "50numbers.txt"; // This String is used by the createArray() function to get 
	// integers from a text file. You can change this value to 
	// a different file name if you want to read from a file
	// that has more or less numbers.

	// Variables used for the sorting process.
	private int[] numbers;									// The array of numbers. This array will be initialized and
	// filled with numbers in the function createArray().

	private boolean start;									// start and finish are booleans that are used to start the
	private boolean finish;									// sorting process and to draw the the total number of comparisons
	// once all sorting algorithms have finished.

	// Variables used to display info on the panel.
	private int comparisons;								// comparisons should keep track of how many comparisons the current
	// sorting algorithm has made.

	private int compareIndex1;								// compareIndex1 and compareIndex2 are used to highlight which two
	private int compareIndex2;								// numbers are bring compared. You must update these variables
	// every time a comparison is made.

	private String sortingName;								// used to display the current sorting algorithm's name.

	private int bubbleComparisons;							// This variable will be used to keep track of how many comparisons
	private int selectionComparisons;
	private int insertionComparisons;
	
	// bubble sort required. You'll need to add similar variables for
	// sorting algorithm that you write.


	// Sorting Panel Constructor
	public SortingPanel(){
		sortingName = "";
		comparisons = 0;
		bubbleComparisons = 0;

		compareIndex1 = 0;
		compareIndex2 = 0;
		start = false;
		finish = false;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		numbers = new int[100];

		createArray();
	}


	// method: swap
	// description: This method will swap two elements in array numbers and then repaint the panel.
	//              Your code should call this method whenever you want to swapt two elements.
	// parameters: int i and int j - the two indexes that of the elements that will be swapped.
	private void swap(int i, int j){
		int temp = numbers[i];  
		numbers[i] = numbers[j];  
		numbers[j] = temp;
		paintImmediately(getVisibleRect());
	}

	private void selectionSort() {
		



	}

	private void insertionSort() {

	}
	
	// method: BubbleSort
	// description: This method will sort the integers in array numbers using the bubble sort algorithm. As it sorts the 
	//              array it will set the two instance fields compareIndex1 and compareIndex2 to the indexes of the two
	//              elements that are bring compared. These variables are used to draw the red 
	private void bubbleSort(){
		boolean isSorted = false;
		
		int j = 0;
		while(j < numbers.length - 1 && !isSorted ) {
			isSorted = true;
			for (int i=0; i<(numbers.length - j -1); i++) {
				comparisons++;								// update the comparisons counter. 
				compareIndex1 = i;							// update the two compare indexes so that the numbers that
				compareIndex2 = i+1;						// are currently being compared are highlighted.
				this.paintImmediately(getVisibleRect());	// repaint the panel. Your code should repaint the panel after each
				// comparison and swap.

				if (numbers[i] > numbers[i+1]) {
					swap(i, i+1);							// exchange elements
					isSorted = false;
				}
			}
			j++;
		}
	}

	// method: resizeArray
	// description: This function is used by the createArray. It resizes the array as the numbers are read in from the
	//              text file by either doubling the size if more space is needed or shrinking the size of the array
	//              after all numbers have been added. You shouldn't modify or call this function.
	// parameters: int newSize - the size for the array.
	private void resizeArray(int newSize){
		int newArray[] = new int[newSize];
		if(numbers.length <= newSize){
			for(int i = 0 ; i < numbers.length ; i++){
				newArray[i] = numbers[i];
			}
		}
		else{
			for(int i = 0 ; i < newSize ; i++){
				newArray[i] = numbers[i];
			}
		}
		numbers = newArray;
	} 

	// method: createArray
	// description: This function will add all of the numbers from the text file FILE_NAME into the array numbers.
	//              It should be called before each sorting algorithm begins.
	void createArray() {
		comparisons = 0;
		Scanner file;
		try {

			file = new Scanner(new File(FILE_NAME));
			int counter = 0;
			while (file.hasNext()){
				if(numbers.length == counter)
					resizeArray(counter*2);
				numbers[counter] = file.nextInt();
				counter++;
			}		
			resizeArray(counter);

		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		}

	}

	// method: toString 
	// description: Prints the contents of the numbers array. You could use this function if you need to debug your code.
	public String toString(){
		String s = new String();
		for(int i = 0; i<numbers.length; i++)
			s = s + numbers[i] + "\n";
		return s;
	}

	// method: paintComponent
	// description: This function paints all contents onto the panel. This is the only function where you can draw. It will
	//              be called every time you make a comparison or swap two values.
	// parameters: Graphics g - This object is used for drawing on JPanels.
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		// finish is initially false. It is set to true once all sorting algorithms have completed.
		// Once all of the sorting algorithms have completed your code should display the total
		// number of comparisons each sorting algorithm required.
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, WIDTH, HEIGHT);

		if(!finish){
			// code that draws the rectangles during the sorting process
			for(int i=0; i<numbers.length; i++){							// iterate through the array
				if(compareIndex1 == i || compareIndex2 == i)				// set the color to red if current element is one
					g2.setColor(Color.RED);									// of the two numbers that are currently being compared.
				else
					g2.setColor(Color.CYAN);								// otherwise set it to Cyan. You can off course change this.

				// Draw a rectangle for the current number. Height is based on the number's value.
				g2.fillRect(i*WIDTH/numbers.length, HEIGHT-numbers[i], WIDTH/numbers.length, numbers[i]);

				// This draws a outline around the red or cyan rectangle.
				g2.setColor(Color.BLUE);
				g2.drawRect(i*WIDTH/numbers.length, HEIGHT-numbers[i], WIDTH/numbers.length, numbers[i]);	
			}

			// draws the name of the current sorting algorithm and the comparisons count.
			g2.setColor(Color.BLACK);
			g2.setFont(new Font ("Arial", Font.BOLD, 35));
			g2.drawString(sortingName, WIDTH-275, HEIGHT-10);
			g2.drawString(String.valueOf(comparisons), 10, HEIGHT-10);
		}
		else{
			// this code will run once all sorting algorithms have finished
			for(int i=0; i<numbers.length; i++){							// iterate through the array

				// Draw a rectangle for the current number. Height is based on the number's value.
				g2.setColor(Color.CYAN);									
				g2.fillRect(i*WIDTH/numbers.length, HEIGHT-numbers[i], WIDTH/numbers.length, numbers[i]);

				// This draws a outline around the cyan rectangle.
				g2.setColor(Color.BLUE);
				g2.drawRect(i*WIDTH/numbers.length, HEIGHT-numbers[i], WIDTH/numbers.length, numbers[i]);	
			}

			// This code currently writes out how many comparisons bubble sort required. You will update it
			// by adding counts for each of the sorting algorithms that you implement.
			g2.setColor(Color.BLACK);
			g2.setFont(new Font ("Arial", Font.BOLD, 35));
			
			g2.drawString("Bubble Sort", WIDTH/2-227, HEIGHT/2-100);
			g2.drawString("Comparisons = " + String.valueOf(bubbleComparisons), WIDTH/2-20, HEIGHT/2-100);
			
			g2.drawString("Selection Sort", WIDTH/2-263, HEIGHT/2);
			g2.drawString("Comparisons = " + String.valueOf(selectionComparisons), WIDTH/2-20, HEIGHT/2);
		
			g2.drawString("Insertion Sort", WIDTH/2-263, HEIGHT/2 + 100);
			g2.drawString("Comparisons = " + String.valueOf(insertionComparisons), WIDTH/2-20, HEIGHT/2 + 100);
		
		}

		if(!start){
			start =!start;

			// These next four lines of code contain everything that is necessary to call the bubble sort process.
			// You'll follow the same process for each of the other sorting algorithms that you write.
			createArray();						// create an array of unsorted integers from the text file.
			sortingName = "Bubble Sort";		// change sortingName variable to "Bubble Sort" so that the panel
			// will say bubble sort while this sorting algorithm is running.
			bubbleSort();						// start the bubble sort process.
			bubbleComparisons = comparisons;    // store the number of comparisons that bubble sort required.


			createArray();						// create an array of unsorted integers from the text file.
			sortingName = "Selection Sort";		// change sortingName variable to "Bubble Sort" so that the panel
			selectionSort();						// start the bubble sort process.
			selectionComparisons = comparisons;    // store the number of comparisons that bubble sort required.

			createArray();						// create an array of unsorted integers from the text file.
			sortingName = "Insertion Sort";		// change sortingName variable to "Bubble Sort" so that the panel
			insertionSort();						// start the bubble sort process.
			insertionComparisons = comparisons;    // store the number of comparisons that bubble sort required.

			// Insert calls to your other sorting algorithms. Remember to recreate your array before sorting.


			finish = true;						// finished is changed to true once all sorting algorithms have finished.
			this.paintImmediately(getVisibleRect());
		}
	}

}
