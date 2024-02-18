#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    int n, k;
    string T;
    cin >> n >> k >> T;

    int tLength = T.length();

    string matchedStrings[100];
    int curMatchedStringIndex = 0;
    while(n--){
        string str;
        cin >> str;

        bool matched = true;

        if(str.length() < tLength){
            matched = false;
        }

        for(int i = 0; i <= tLength - 1; i++){
            if(str[i] != T[i]){
                matched = false;
            }
        }

        if(matched){
            matchedStrings[curMatchedStringIndex++] = str;
        }
    }

    sort(matchedStrings, matchedStrings + curMatchedStringIndex);

    cout << matchedStrings[k-1];
    return 0;
}