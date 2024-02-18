#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

bool checkIsStartWithT(string& T, string& target){
    int tLength = T.length();
    bool isStartWith = true;

    if(target.length() < tLength){
        isStartWith= false;
    }

    for(int i = 0; i <= tLength - 1; i++){
        if(target[i] != T[i]){
            isStartWith = false;
        }
    }

    return isStartWith;
}

int main() {
    int n, k;
    string T;
    cin >> n >> k >> T;

    string matchedStrings[100];
    int curMatchedStringIndex = 0;
    while(n--){
        string str;
        cin >> str;

        bool isStartWith = checkIsStartWithT(T, str);

        if(isStartWith){
            matchedStrings[curMatchedStringIndex++] = str;
        }
    }

    sort(matchedStrings, matchedStrings + curMatchedStringIndex);

    cout << matchedStrings[k-1];
    return 0;
}