//Program to perform selection sort on a linked list by swapping nodes
//Author:
    //Name: Karneeshwar, Sendilkumar Vijaya

#include <iostream>

using namespace std;

//Node definition
struct Node
{
    int val;
    Node *next;
};

//Function to add elements to the front of linked list
struct Node *createL(struct Node* hd, int nval)
{
    Node* nwnode = new Node();
    nwnode->val = nval;
    nwnode->next = hd;
    hd = nwnode;
    return hd;
}

//Function to print the elements in the linked list
void printL(Node *printnd)
{
    while (printnd != NULL)
    {
        cout<<printnd->val;
        if(printnd->next != NULL) cout<<" -> ";
        printnd = printnd->next;
    }
}

//Function to swap nodes for sorting
struct Node* swapN(struct Node* hdd, struct Node* firstN, struct Node* secondN, struct Node* prevtoSN)
{
    hdd = secondN;
    prevtoSN->next = firstN;
    Node* temp = secondN->next;
    secondN->next = firstN->next;
    firstN->next = temp;

    return hdd;
}

//Function to traverse and find the nodes with smallest value using recursion
struct Node* rselsortL(struct Node* hd)
{
    if (hd == NULL || hd->next == NULL) return hd; //Check if list is empty or the list has only one element

    Node* valMin = hd; //node to store the node of smallest value, equals to first node initially
    Node* prevofMin = NULL; //node to store the previous node of the smallest value, equals to NULL initially
    Node* travNd = hd; //node to traverse the given list

    /* Traversing the list to find
    the node with the smallest value,
    and the node preceding the smallest value */

    while (travNd->next != NULL)
    {
        if (travNd->next->val < valMin->val)
        {
            valMin = travNd->next;
            prevofMin = travNd;
        }
        travNd = travNd->next;
    }

    if (valMin != hd) hd = swapN(hd, hd, valMin, prevofMin); //Using function to swap the nodes

    hd->next = rselsortL(hd->next); //Recursively calling the function for next node

    return hd;
}


int main()
{
    cout<<"\nCS5343.002 Assignment:1 Question:1 - Selection Sort by Swapping Nodes\n\n";

    Node* head = NULL;

    //Creating a single linked list of 16 integers
    for(int i = 0; i <= 15; i++)
    head = createL(head, rand()%100 + 1);

    //Printing the list created
    cout<<"The unsorted list is: ";
    printL(head);

    head = rselsortL(head); //Function to sort the list

    //Printing the sorted list
    cout<<"\n\nThe sorted list is  : ";
    printL(head);

    cout<<"\n\nEnd of results!!\n\n";

    return 0;
}
