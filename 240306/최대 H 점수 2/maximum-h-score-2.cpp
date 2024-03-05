#include <iostream>
#include <algorithm>

using namespace std;

int N, L;
int numbers[100];

bool checkIsValid(int maxScore){
    for(int i = N-1; i >= N-L; i--){
        numbers[i]++; 
    }

    bool isValid = true;

    if(numbers[N-maxScore] + 1 <= maxScore){
        isValid = false;
    }

    for(int i = N; i >= N-L; i--){
        numbers[i]--; 
    }

    if(isValid){
        return true;
    }
    return false;
}

int main() {
    cin >> N >> L;
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers + N);
    for(int maxScore = N; maxScore >= 1; maxScore--){
        if(checkIsValid(maxScore)){
            cout << maxScore;
            return 0;
        }
    }
    return 0;
}