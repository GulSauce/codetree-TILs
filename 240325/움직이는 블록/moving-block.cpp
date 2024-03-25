#include <iostream>

using namespace std;

int numbers[10000];

int main() {
    int N;
    cin >> N;

    int sum = 0;
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
        sum += numbers[i];
    }

    int destHeight = sum / N;

    int moveCount = 0;
    for(int i = 0; i <= N-1; i++){
        if(destHeight + 1 <= numbers[i]){
            moveCount += numbers[i] - destHeight;
        }
    }

    cout << moveCount;
    return 0;
}