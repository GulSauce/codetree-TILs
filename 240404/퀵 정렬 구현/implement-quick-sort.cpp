#include <iostream>
#include <vector>

using namespace std;

vector<int> numbers;

int partion(int start, int end){
    int pivot = numbers[end];

    int i = start-1;
    int j = start;

    while(j + 1 <= end){
        if(numbers[j] + 1 <= pivot){
            swap(numbers[++i], numbers[j++]);
            continue;
        }
        j++;
    }

    swap(numbers[++i], numbers[j]);
    return i;
}

void quickSort(int start, int end){
    if(end <= start){
        return;
    }

    int pivotIndex = partion(start, end);
    quickSort(start, pivotIndex-1);
    quickSort(pivotIndex+1, end);
}

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        int number;
        cin >> number;
        numbers.push_back(number);
    }

    quickSort(0, n-1);

    for(int number : numbers){
        cout << number << ' ';
    }

    return 0;
}