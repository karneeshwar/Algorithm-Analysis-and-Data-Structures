//Program to search an element by diving the array into 3 parts
//Author:
    //Name: Karneeshwar, Sendilkumar Vijaya

#include <iostream>

using namespace std;

//Recursive function to find the position of a value by dividing the array into 3 parts
int terSearch(int Arr[], int a, int b, int v)
{
    if(b>=a)
    {
        int mid1 = a+(b-a)/3;
        int mid2 = mid1+(b-a)/3;

        //Checking for the value in the three divided parts
        if(v==Arr[mid1])
            return mid1;
        else if(v==Arr[mid2])
            return mid2;
        else if(v<Arr[mid1])
            return terSearch(Arr,a,mid1-1,v);
        else if(v>Arr[mid1] && v<Arr[mid2])
            return terSearch(Arr,mid1+1,mid2-1,v);
        else
            return terSearch(Arr,mid2+1,b,v);

    }
    return -1;
}

int main()
{
    cout<<"\nCS5343.002 Assignment:1 Question:2 - Tertiary Search\n\n";

    int A[16] = {1,3,4,5,7,9,15,21,25,33,38,48,60,83,99,100};
    int val, pos;
    int first = 0, length = sizeof(A)/sizeof(A[0]);
    int last = length-1;

    cout<<"\nThe Available Array of elements are : ";

    for(int i=0; i<length; i++)
        cout<<A[i]<<" ";

    cout<<"\n\nEnter the Number to be searched (user input required): ";
    cin>>val;

    pos = terSearch(A,first,last,val);

    if(pos>=0)
        cout<<"\nThe Index of "<<val<<" is : "<<pos;
    else
        cout<<"\nThe Number cannot be found!!!";

    cout<<"\n\nEnd of results!!\n\n";
}
