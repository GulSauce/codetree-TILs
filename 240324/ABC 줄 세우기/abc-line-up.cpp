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
        if(indexToAlphabet[i] == i + 'A'){
           continue; 
        }

        int curAlphabet = indexToAlphabet[i]; 
        int curAlphabetIndex = alphabetToIndex[i];
        while(i + 1 <= curAlphabetIndex){
            char beforeAlphabet = indexToAlphabet[curAlphabetIndex-1];
            swap(alphabetToIndex[beforeAlphabet-'A'], alphabetToIndex[curAlphabet-'A']);
            swap(indexToAlphabet[curAlphabetIndex-1], indexToAlphabet[curAlphabetIndex]);
            curAlphabetIndex--;
            result++;
        }
    }

    cout << result;
    return 0;
}