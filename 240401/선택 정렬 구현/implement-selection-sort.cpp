#include <iostream>
#include <climits>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> numbers(n);

    for(int & number: numbers){
        cin >> number;
    }


    for(int curIndex = 0; curIndex <= n-1; curIndex++){
        int minIndex = curIndex;
        int minValue = INT_MAX;
        for(int searchIndex = curIndex; searchIndex <= n-1; searchIndex++){
            if(numbers[searchIndex] <= minValue){
                minIndex = searchIndex;
                minValue = numbers[searchIndex];
            }
        }

        swap(numbers[curIndex], numbers[minIndex]);
    }

    for(int number : numbers){
        cout << number << ' '; 
    }
    return 0;
}