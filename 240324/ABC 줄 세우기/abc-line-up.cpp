#include <algorithm>
#include <iostream>
#include <cstring>

using namespace std;

// 인덱스에 어떤 알파벳이 있는가
char indexToAlphabet[26];
// 알파벳은 어떤 인덱스에 있는가
int alphabetToIndex[26];

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        char alphabet;
        cin >> alphabet;
        indexToAlphabet[i] = alphabet;
        alphabetToIndex[alphabet-'A'] = i;
    }

    int result = 0;
    for(int i = 0; i <= n-1; i++){
        
        int curAlphabetInt = i;

        if(indexToAlphabet[i] == curAlphabetInt + 'A'){
           continue; 
        }

        int curAlphabetIndex = alphabetToIndex[curAlphabetInt];
        while(i + 1 <= curAlphabetIndex){
            int beforeAlphabetInt = indexToAlphabet[curAlphabetIndex-1] - 'A';
            swap(alphabetToIndex[beforeAlphabetInt], alphabetToIndex[curAlphabetInt]);
            swap(indexToAlphabet[curAlphabetIndex-1], indexToAlphabet[curAlphabetIndex]);
            curAlphabetIndex--;
            result++;
        }
    }

    cout << result;
    return 0;
}