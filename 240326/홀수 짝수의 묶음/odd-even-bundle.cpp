#include <iostream>

using namespace std;

int numbers[1000];

int main() {
    int N;
    cin >> N;

    int oddCount = 0;
    int evenCount = 0;

    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
        if(numbers[i] % 2 == 0){
            evenCount++;
        }
        else{
            oddCount++;
        }
    }

    if(oddCount + 2 <= evenCount){
        while(oddCount + 2 <= evenCount){
            evenCount--;
        }
        cout << oddCount + evenCount;
        return 0;
    }

    if(evenCount + 1 <= oddCount){
        while(evenCount + 1 <= oddCount){
            oddCount -= 2;
            evenCount++;
        }
    }

    while(oddCount + 2 <= evenCount){
        evenCount--;
    }

    cout << oddCount + evenCount;
    return 0;
}