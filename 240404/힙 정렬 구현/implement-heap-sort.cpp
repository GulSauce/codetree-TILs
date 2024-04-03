#include <iostream>
#include <vector>

using namespace std;

int n;
int numbers[100002];

void heapify(int parentIndex, int arrayLength){
    int parentValue = numbers[parentIndex];
    int child1Value = -1;
    int child2Value = -1;
    int largestIndex = parentIndex;

    if(parentIndex * 2 <= arrayLength){
        child1Value = numbers[parentIndex*2];
    }
    if(parentIndex * 2 + 1<= arrayLength){
        child2Value = numbers[parentIndex*2+1];
    }

    if(parentValue + 1 <= child1Value
    && child2Value + 1<= child1Value){
        swap(numbers[parentIndex], numbers[parentIndex*2]);
        largestIndex = parentIndex*2;
    }
    if(parentValue + 1 <= child2Value
    && child1Value + 1<= child2Value){
        swap(numbers[parentIndex], numbers[parentIndex*2+1]);
        largestIndex = parentIndex*2 + 1;
    }
    if(parentIndex == largestIndex){
        return;
    }
    heapify(largestIndex, arrayLength);
}

int main() {
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