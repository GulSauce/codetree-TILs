#include <iostream>
#include <string>

using namespace std;

string inputStr;
string destStr;

int findMatch(){
    int  matchedIndex = -1;
    for(int i = 0; inputStr[i]; i++){
        int curMatched = true;
        for(int j = 0; destStr[j]; j++){
            if(inputStr[i+j] != destStr[j]){
                curMatched = false;
                break;
            }
        }
        if(curMatched){
            matchedIndex = i;
            break;
        }
    }
    return matchedIndex;
}
int main() {
    cin >> inputStr;
    cin >> destStr;

    int matchedIndex = findMatch();
    cout << matchedIndex;
    return 0;
}