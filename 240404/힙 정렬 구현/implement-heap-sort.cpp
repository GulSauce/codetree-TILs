#include <iostream>
#include <vector>

using namespace std;

int n;
int numbers[100002];

void heapify(int startIndex, int arrayLength){
    int parentValue = numbers[startIndex];
    if(arrayLength + 1 <= startIndex*2){
        return;
    }

    int child1Value = numbers[startIndex*2];
    if(parentValue + 1 <= child1Value){
        swap(numbers[startIndex], numbers[startIndex*2]);
        heapify(startIndex*2, arrayLength);
    }

    if(arrayLength + 1 <= startIndex*2 + 1){
        return;
    }

    int child2Value = numbers[startIndex*2+1];
    if(parentValue + 1 <= child2Value){
        swap(numbers[startIndex], numbers[startIndex*2+1]);
        heapify(startIndex*2+1, arrayLength);
    }
}

int main() {
    cin >> n;

    for(int i = 1; i <= n; i++){
        cin >> numbers[i];
    }

    for(int i = n/2; i >= 1; i--){
        heapify(i, n);
    }

    for(int i = n; i >= 1; i--){
        swap(numbers[1], numbers[i]);
        heapify(1, i-1);
    }

    for(int i = 1; i <= n; i++){
        cout << numbers[i] << ' ';
    }
    return 0;
}