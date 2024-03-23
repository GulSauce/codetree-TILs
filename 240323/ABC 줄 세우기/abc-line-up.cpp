#include <iostream>
#include <cstring>

using namespace std;

char alphabetSeq[26];

bool isGoodPosition[26];

int main() {
    int n;
    cin >> n;

    memset(isGoodPosition, true, sizeof isGoodPosition);

    for(int i = 0; i <= n-1; i++){
        cin >> alphabetSeq[i];
    }

    for(int i = 0; i <= n-1; i++){
        if(alphabetSeq[i] == i+'A'){
            continue;
        }

        isGoodPosition[i] = false;
    }

    int result = 0;

    int needComplexSwapCount = 0;

    for(int i = 0; i <= n-1; i++){
        if(isGoodPosition[i]){
            continue;
        }

        int currentAlphabetToInt = alphabetSeq[i] - 'A';
        if(alphabetSeq[currentAlphabetToInt] == i + 'A'){
            isGoodPosition[i] = true;
            isGoodPosition[currentAlphabetToInt] = true;
            result++;
            continue;
        }
        needComplexSwapCount++;
    }

    if(1 <= needComplexSwapCount){
        result += needComplexSwapCount - 1;
    }
    cout << result;
    return 0;
}