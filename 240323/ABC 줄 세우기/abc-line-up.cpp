#include <algorithm>
#include <iostream>
#include <cstring>

using namespace std;

// 인덱스에 어떤 알파벳이 있는가
char alphabetSeq[26];
// 알파벳은 어떤 인덱스에 있는가
int alphabetPosArr[26];

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        char alphabet;
        cin >> alphabet;
        alphabetSeq[i] = alphabet;
        alphabetPosArr[alphabet-'A'] = i;
    }

    int result = 0;
    for(int i = 0; i <= n-1; i++){
        char curAlphabet = i + 'A';
        if(alphabetSeq[i] == curAlphabet){
           continue; 
        }

        int curAlphaPos = alphabetPosArr[i];
        while(i + 1 <= curAlphaPos){
            char beforeAlphabet = alphabetSeq[curAlphaPos-1];
            swap(alphabetPosArr[beforeAlphabet], alphabetPosArr[curAlphabet]);
            swap(alphabetSeq[curAlphaPos-1], alphabetSeq[curAlphaPos]);
            curAlphaPos--;
            result++;
        }
    }
    cout << result;
    return 0;
}