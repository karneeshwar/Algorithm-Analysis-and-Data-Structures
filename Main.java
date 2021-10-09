//Program to perform insertion, inorder traversal and deletion in a BST
//Author:
    //Name: Karneeshwar, Sendilkumar Vijaya

//Class defined for the Tree
class Node
{
    int data;
    Node left;
    Node right;

    Node(int element)
    {
        data = element;
        left = null;
        right = null;
    }
}

public class Main
{
    //Function to insert elements into the tree recursively
    public static Node insertElement(Node root, int element)
    {
        if(root == null)
            return new Node(element);

        if(element <= root.data)
            root.left = insertElement(root.left, element);
        else
            root.right = insertElement(root.right,element);

        return root;
    }

    //Function to perform inorder traversal of the BST
    public static void inorderTraversal(Node root)
    {
        if(root == null)
            return;

        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }

    //Function to find the successor to replace the item to be deleted
    public static Node findSuccessor(Node root)
    {
        Node successor = root.right;
        while(successor != null && successor.left != null)
            successor = successor.left;
        return successor;
    }

    //Function to delete an element from the BST and replace it with successor
    public static Node deleteElement(Node root, int element)
    {
        if(root == null)
            return null;

        if(root.data > element)
            root.left = deleteElement(root.left, element);

        else if(root.data < element)
            root.right = deleteElement(root.right, element);

        else
        {
            if(root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else 
			{
                Node inorderSuccessor = findSuccessor(root);
                root.data = inorderSuccessor.data;
                root.right = deleteElement(root.right, inorderSuccessor.data);
            }
        }
        return root;
    }

    public static void main(String[] args)
    {
        System.out.print("\nCS5343.002 Assignment 2: Insertion, Inorder traversal and Deletion in BST\n");

        //Initializing Arrays of elements to be added in the BST
        int[] elements = new int[]{40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46};
        Node root = null;

        //Calling function to insert element to the BST
        for(int i: elements)
            root = insertElement(root, i);

        System.out.print("\nInitially the BST contains (Inorder Traversal):\n\t");
        inorderTraversal(root);

        root = deleteElement(root, 40); //Deleting element 40 from the BST
        System.out.print("\n\nThe BST after deleting 40 (Inorder Traversal):\n\t");
        inorderTraversal(root);

        root = deleteElement(root, 20); //Deleting element 20 from the BST
        System.out.print("\n\nThe BST after deleting 20 (Inorder Traversal):\n\t");
        inorderTraversal(root);

        System.out.print("\n\nEnd of Results!!\n");
    }
}
