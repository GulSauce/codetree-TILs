#include <iostream>
#include <vector>

using namespace std;

int numbers[100002];

void heapify(int parentIndex, int arrayLength){
    int parentValue = numbers[parentIndex];
    int largestIndex = parentIndex;

    if(parentIndex * 2 <= arrayLength
    && numbers[largestIndex] + 1 <= numbers[parentIndex*2]){
        largestIndex = parentIndex * 2;
    }

    if(parentIndex * 2 + 1 <= arrayLength
    && numbers[largestIndex] + 1 <= numbers[parentIndex * 2 + 1]){
        largestIndex = parentIndex * 2 + 1;
    }

    if(parentIndex == largestIndex){
        return;
    }

    swap(numbers[largestIndex], numbers[parentIndex]);

    heapify(largestIndex, arrayLength);
}

int main() {
    int n;
    cin >> n;

    for(int i = 1; i <= n; i++){
        cin >> numbers[i];
    }

    for(int i = n/2; i >= 1; i--){
        heapify(i, n);
    }

    for(int i = n; i >= 2; i--){
        swap(numbers[1], numbers[i]);
        heapify(1, i-1);
    }

    for(int i = 1; i <= n; i++){
        cout << numbers[i] << ' ';
    }
    return 0;
}