/*Program to perform Topological sorting using DFS and BFS in the given graphs
Author
Name    : Karneeshwar, Sendilkumar Vijaya
NetID   : KXS200001
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Graph
{
    //Global variables for storing the vertices to be printed after sorting
    static Stack<Integer> stack = new Stack<Integer>();
    static ArrayList<Integer> Sorted = new ArrayList<Integer>();

    //Function to create edges to the graphs
    static void createEdge(ArrayList<ArrayList<Integer>> adj, int u, int v)
    {
        adj.get(u).add(v);
    }

    //Function to perform Topological Sort using DFS recursively
    static boolean DFSTopological(ArrayList<ArrayList<Integer>> adj,int v, boolean[] called, boolean[] visited)
    {
        //The current vertex(v) is visited and called by the function, becomes true
        visited[v] = true;
        called[v] = true;

        //for all adjacent vertices to v
        for(int i :adj.get(v))
        {
            //if v is visited
            if(!visited[i] && DFSTopological(adj, i,called,visited))
                return true;
            //if v is recursively called by the function
            else if(called[i])
                return true;
        }
        called[v] = false;
        //push the vertex into the stack
        stack.push(v);
        return false;
    }

    //Function to determine cyclic or acyclic graph using DFS
    static boolean DFS(ArrayList<ArrayList<Integer>> adj, int v)
    {
        //Variable to keep track of visited vertices
        boolean[] visited = new boolean[v];
        //Initializing to false
        for(int i = 0; i< v; i++)
            visited[i] = false;

        //Variable to keep track of vertices that went through the recursive call
        boolean[] called = new boolean[v];
        //Initializing to false
        for(int i = 0; i< v; i++)
            called[i] = false;

        //For all vertices
        for(int i = 0; i< v; i++)
        {
            //If not visited, perform DFS topological sort
            if(!visited[i])
                if(DFSTopological(adj,i,called,visited))
                    return true;
        }
        return false;
    }

    //Function to perform Topological Sort using BFS recursively
    static boolean BFSTopological(ArrayList<ArrayList<Integer>> adj, int v)
    {
        int[] degree =  new int[v];
        Queue<Integer> queue = new LinkedList<>();

        //for all vertices, their adjacent vertices are counted
        for (int i = 0; i < v; i++)
            for (int x:adj.get(i))
                degree[x]++;

        //for all vertices
        for (int i = 0; i < v; i++)
            //if the degree of current vertex 0
            if (degree[i] == 0)
                //add to queue
                queue.add(i);

        //Variable to count the vertices visited
        int visited = 0;

        //while the queue is not empty
        while (!queue.isEmpty())
        {
            //dequeue each element
            int x = queue.poll();
            //store it in a list for printing
            Sorted.add(x);
            //for all the adjacent vertices of current element
            for (int i: adj.get(x))
                //decrement degree and move shorted the queue
                if (--degree[i] == 0)
                    queue.add(i);
            visited++;
        }
        //if the value of visited is not equal to the number of vertices, then graph is cyclic, else acycle
        //return true or false
        return visited != v;
    }

    //Function to get corresponding integer values of alphabets and using them as vertices
    static int charToInt(char c)
    {
        //m = 22-22 = 0, z = 35 - 22. Range of vertices = 0 to 13
        return Character.getNumericValue(c) - 22;
    }

    public static void main(String[] args)
    {
        System.out.print("\nCS5343.002 Assignment 5: Topological Sorting in Graph\n");

        //Graph 1 with vertices 1 to 8
        int v1 = 8;
        ArrayList<ArrayList<Integer> > adjMat = new ArrayList<ArrayList<Integer>>(v1);

        //Initializing the list with indices 0 to 7 for ease of use instead of 1 to 8
        for (int i = 0; i < v1; i++)
            adjMat.add(new ArrayList<Integer>());

        //Creating edges for graph 1
        createEdge(adjMat,0, 1);
        createEdge(adjMat,0, 4);
        createEdge(adjMat,0, 5);
        createEdge(adjMat,1, 2);
        createEdge(adjMat,1, 4);
        createEdge(adjMat,2, 3);
        createEdge(adjMat,3, 4);
        createEdge(adjMat,4, 6);
        createEdge(adjMat,4, 7);
        createEdge(adjMat,5, 7);
        createEdge(adjMat,6, 3);
        createEdge(adjMat,6, 7);

        //Performing DFS Topological sort and printing results for graph 1
        System.out.print("\nGraph 1 with vertices 1 to 8\n DFS Topological Sort: ");

        //If the function returns true the graph is cyclic and topological sort doesn't exist
        if(DFS(adjMat,v1))
            System.out.print("The Graph is Cyclic");
        //If the function returns false the graph is acyclic and topological sort is printed
        else
            while(!stack.empty())
                //Vertices are printed by adding 1 so the final result ranges from 1 to 8 as seen in image1.png
                System.out.print(stack.pop()+1 + " ");

        //Performing BFS Topological sort and printing results for graph 1
        System.out.print("\n BFS Topological Sort: ");

        //If the function returns true the graph is cyclic and topological sort doesn't exist
        if(BFSTopological(adjMat,v1))
            System.out.print("The Graph is Cyclic");
        //If the function returns false the graph is acyclic and topological sort is printed
        else
            for(int i: Sorted)
                //Vertices are printed by adding 1 so the final result ranges from 1 to 8 as seen in image1.png
                System.out.print(i+1 + " ");

        //Clearing the Sorted Arraylist so it can be used by graph 2
        Sorted.clear();

        //Graph 2 with vertices m to z

        //Alphabetical vertices are converted to integer values for ease of use
        int v2 = Character.getNumericValue('z') - Character.getNumericValue('m') + 1; //v2 = 14
        adjMat = new ArrayList<ArrayList<Integer>>(v2);

        //Initializing the list with indices 0 to 7 for ease of use instead of 1 to 8
        for (int i = 0; i < v2; i++)
            adjMat.add(new ArrayList<Integer>());

        //Creating edges for graph 2, charToInt function is used to find the respective integer values of vertices
        createEdge(adjMat, charToInt('m'), charToInt('q'));
        createEdge(adjMat, charToInt('m'), charToInt('r'));
        createEdge(adjMat, charToInt('m'), charToInt('x'));
        createEdge(adjMat, charToInt('n'), charToInt('q'));
        createEdge(adjMat, charToInt('n'), charToInt('u'));
        createEdge(adjMat, charToInt('n'), charToInt('o'));
        createEdge(adjMat, charToInt('o'), charToInt('r'));
        createEdge(adjMat, charToInt('o'), charToInt('v'));
        createEdge(adjMat, charToInt('o'), charToInt('s'));
        createEdge(adjMat, charToInt('p'), charToInt('o'));
        createEdge(adjMat, charToInt('p'), charToInt('s'));
        createEdge(adjMat, charToInt('p'), charToInt('z'));
        createEdge(adjMat, charToInt('q'), charToInt('t'));
        createEdge(adjMat, charToInt('r'), charToInt('u'));
        createEdge(adjMat, charToInt('r'), charToInt('y'));
        createEdge(adjMat, charToInt('s'), charToInt('r'));
        createEdge(adjMat, charToInt('u'), charToInt('t'));
        createEdge(adjMat, charToInt('v'), charToInt('x'));
        createEdge(adjMat, charToInt('v'), charToInt('w'));
        createEdge(adjMat, charToInt('w'), charToInt('z'));
        createEdge(adjMat, charToInt('y'), charToInt('v'));

        //Performing DFS Topological sort and printing results for graph 2
        System.out.print("\n\nGraph 2 with vertices m to z\n DFS Topological Sort: ");

        //If the function returns true the graph is cyclic and topological sort doesn't exist
        if(DFS(adjMat,v2))
            System.out.print("The Graph is Cyclic");
        //If the function returns false the graph is acyclic and topological sort is printed
        else
            while(!stack.empty())
                //Vertices are printed in alphabets as seen in image2.png
                System.out.print(Character.forDigit(stack.pop() + 22, 36) + " ");

        //Performing BFS Topological sort and printing results for graph 2
        System.out.print("\n BFS Topological Sort: ");

        //If the function returns true the graph is cyclic and topological sort doesn't exist
        if(BFSTopological(adjMat,v2))
            System.out.print("The Graph is Cyclic");
        //If the function returns false the graph is acyclic and topological sort is printed
        else
            for(int i: Sorted)
                //Vertices are printed in alphabets as seen in image2.png
                System.out.print(Character.forDigit(i + 22, 36) + " ");

        System.out.print("\n\nEnd of Results!!\n");
    }
}
