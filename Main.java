/*
Program to build a minheap using Floyd's algorithm and sort it in descending order
Author
Name	: Karneeshwar, Sendilkumar Vijaya
NetID	: KXS200001 
*/

public class Main
{
    //Function to make an array into a minheap using Floyd's algorithm
    static void minHeap(int[] arr, int parent, int l)
    {
        int tmpPos = parent;  							//Variable to track the position of the smallest value, assuming its in current parent initially
        int lChild = 2*parent + 1; 						//Position of left child of the current parent
        int rChild = 2*parent + 2; 						//Position of right child of the current parent

        if (lChild < l && arr[lChild] < arr[tmpPos]) 	//If left child exists and if it's smaller than the one at current position
            tmpPos = lChild; 							//Then new position of the smallest node is the position of left child

        if (rChild < l && arr[rChild] < arr[tmpPos]) 	//If right child exists and if it's smaller than the one at current position
            tmpPos = rChild; 							//Then new position of the smallest node is the position of right child

        if (tmpPos != parent) 							//If the current position is not same as parent node's position
        {
            int tmp = arr[parent]; 						//Then swap the smallest one with the parent
            arr[parent] = arr[tmpPos];
            arr[tmpPos] = tmp;
            minHeap(arr, tmpPos, l); 					//Recursively call minHeap to check the sub-tress as well
        }
    }

    //Function to perform heapsort on the array
    static void heapSort(int[] arr, int len)
    {
        for(int i = len-1; i>0; i--) 					//Reducing the length of the array at each iteration to complete sorting
        {
            int tmp = arr[0]; 							//Swapping the root and last leaf node elements
            arr[0] = arr[i];
            arr[i] = tmp;

            minHeap(arr,0, i); 							//Calling function to convert the new array to a heap
        }
    }

    //Function to find the last parent and iterate the heap building process to subtrees
    static void buildMinHeap(int[] arr, int len)
    {
        int lastParent = len/2 - 1; 					//last parent of the heap

        for (int i = lastParent; i >= 0; i--) 			//Moving towards root by iterating through each parent
            minHeap(arr, i, len);
    }

    //Function to print the elements of the heap stored as an array
    static void printHeapAsArray(int[] arr, int len)
    {
        for(int i = 0; i < len; i++)
            System.out.print(arr[i] + " ");
        System.out.print("\n");
    }

    //Main function
    public static void main(String[] args)
    {
        System.out.print("\nCS5343.002 Assignment 3: Building and Sorting Heap\n");

        //Initializing Arrays of elements to be added in the heap
        int[] array = {40, 60, 20, 80, 50, 10, 30, 14, 65, 35, 25, 45, 55, 32, 16};
        int length = array.length;

        //Printing initial array before building heap
        System.out.print("\nThe number of elements in the heap = " + length);
        System.out.print("\nSequence of elements before building the heap: \n");
        printHeapAsArray(array, length);

        //Building heap
        buildMinHeap(array, length);
        //Printing array after building the heap
        System.out.print("\nThe number of elements in the heap = " + length);
        System.out.print("\nSequence of elements after building the heap: \n");
        printHeapAsArray(array, length);

        //Heapsort
        heapSort(array, length);
        //Printing the array after sorting the heap
        System.out.print("\nThe number of elements in the heap = " + length);
        System.out.print("\nSequence of elements after sorting the heap: \n");
        printHeapAsArray(array, length);
		
		System.out.print("\nEnd of Results!!\n\n");
    }
}
