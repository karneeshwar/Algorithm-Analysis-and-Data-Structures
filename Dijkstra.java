/*Program to Implement Dijkstra's Algorithm
Author
Name    : Karneeshwar, Sendilkumar Vijaya
NetID   : KXS200001
*/

public class Dijkstra
{
    //Function to print the vertices and edges of initial graph
    static void printInitGraph(int[][] g)
    {
        int v = g[0].length;
        //Printing vertices which is simply the number of rows in the Adj. Matrix
        System.out.print("\nThe List of Vertices in Graph are: \n");
        for(int i = 0; i < v; i++)
            System.out.print(i + " ");

        //Printing the edges based on the non-zero weights in upper half of the matrix, only half is considered as the matrix is undirected
        System.out.print("\n\nThe List of Edges in Graph are: \n");
        for(int j = 0; j < v; j++)
            for(int k = 0; k < v; k++)
                if(g[j][k] > 0 && k > j)
                    System.out.print("(" + j + ", " + k + ") ");
    }

    //Function for finding the shortest path using Dijkstra's algorithm and printing the vertices and edges of the resulting path
    static void DijkstraAlgo(int[][] g, int start)
    {
        //Source vertex is considered as 0 = start
        int ver = g[0].length;
        int[] leastDistance = new int[ver];     //Array to store the least distance of each vertex form the source, i.e, 0
        boolean[] visited = new boolean[ver];   //Flag to check if a vertex is visited or not
        int[] parent = new int[ver];            //Array to store the parent of vertices that make the shortest path

        //Initializing leastDistance of each vertex to the maximum value, infinity theoretically and making all vertices unvisited
        for(int v = 0; v < ver; v++)
        {
            leastDistance[v] = Integer.MAX_VALUE;
            visited[v] = false;
        }

        //Distance of source from source is 0 and source doesn't have a parent
        leastDistance[start] = 0;
        parent[start] = -1;

        for(int i = 0; i < ver; i++)
        {
            int closestV = -1;
            int leastD = Integer.MAX_VALUE;
            //Finding the shortest distance of each vertex from the source
            for(int v = 0; v < ver; v++)
            {
                if(!visited[i] && leastDistance[i] < leastD)
                {
                    closestV = i;
                    leastD = leastDistance[i];
                }
            }

            visited[closestV] = true;

            //Finding the parent of each vertex forming the shortest path
            for(int v = 0; v < ver; v++)
            {
                int weight = g[closestV][v];
                if(weight > 0 && (leastD + weight) < leastDistance[v])
                {
                    parent[v] = closestV;
                    leastDistance[v] = leastD + weight;
                }
            }
        }

        //Printing the vertices that form the final shortest path
        System.out.print("\n\nThe List of Vertices making the shortest path are: \n");
        for(int v = 0; v < ver; v++)
            System.out.print(v + " ");

        //Printing the edges that form the final shortest path
        System.out.print("\n\nThe List of Edges making the shortest path are: \n");
        for(int p = 1; p < ver; p++)
        {
            System.out.print("(" + parent[p] + ", " + p + ") ");
        }
    }

    public static void main(String[] args)
    {
        System.out.print("\nCS5343.002 Assignment 4: Implementing Dijkstra's Algorithm\n");

        //Undirected Graph of 12 vertices and 21 edges represented as adjacency matrix
        //Vertices range from 0 to 11
        int[][] graph = { {0,20,8,5,0,0,0,0,0,0,0,0},
                          {20,0,0,0,18,16,0,0,0,0,0,0},
                          {8,0,0,16,0,0,15,0,0,0,0,0},
                          {5,0,16,0,0,0,0,10,0,0,0,0},
                          {0,18,0,0,0,15,0,24,20,0,0,0},
                          {0,16,0,0,15,0,0,0,14,0,0,24},
                          {0,0,15,0,0,0,0,25,0,15,0,0},
                          {0,0,0,10,24,0,25,0,0,9,25,0},
                          {0,0,0,0,20,14,0,0,0,0,22,15},
                          {0,0,0,0,0,0,15,9,0,0,6,0},
                          {0,0,0,0,0,0,0,25,22,6,0,5},
                          {0,0,0,0,0,24,0,0,15,0,5,0} };

        //Function call for printing the vertices and edges of initial graph
        printInitGraph(graph);

        //Function call for finding the shortest path using Dijkstra's algorithm and printing the vertices and edges of the resulting path
        DijkstraAlgo(graph, 0);

        System.out.print("\n\nEnd of Results!!\n\n");
    }
}
