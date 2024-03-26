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

    int length = 0;
    while(1 <= evenCount || 1 <= oddCount){
        if(length % 2 == 0){
            if(evenCount == 0 && 2 <= oddCount){
                oddCount -= 2;
                length++;
            }
            else if(evenCount == 0 && 1 == oddCount){
                oddCount--;
                length--;
            }
            else{
                evenCount--;
                length++;
            }
        }
        else if(length % 2 == 1){
            if(oddCount == 0){
                evenCount--;
            }
            else{
                oddCount--;
                length++;
            }
        }
    }
    cout << length;
    return 0;
}