#include <iostream>

using namespace std;

int N;
int numbers[999];

bool checkIsValid(int startNumber){
    bool isUsed[1001] = {};
    int currentNumber = startNumber;
    isUsed[currentNumber] = true;
    for(int i = 0; i <= N-2; i++){
        int testNumber = numbers[i] - currentNumber;
        if(testNumber <= 0 || isUsed[testNumber]){
            return false;
        }
        currentNumber = testNumber;
        isUsed[currentNumber] = true;
    }

    return true;
}

void print(int startNumber){
    int currentNumber = startNumber;
    cout << currentNumber << ' ';
    for(int i = 0; i <= N-2; i++){
        currentNumber = numbers[i] - currentNumber;
        cout << currentNumber << ' ';
    }
}

int main() {
    cin >> N;
    
    for(int i = 0; i <= N-2; i++){
        cin >> numbers[i];
    }

    for(int i = 1; i <= N; i++){
        if(checkIsValid(i)){
            print(i);
            return 0;
        }
    }
    return 0;
}