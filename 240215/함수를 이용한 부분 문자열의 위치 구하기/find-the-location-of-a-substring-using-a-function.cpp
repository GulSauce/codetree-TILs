#include <iostream>
#include <string>

using namespace std;

string inputStr;
string destStr;

int findMatch(){
    return inputStr.find(destStr);
}


int main() {
    cin >> inputStr;
    cin >> destStr;

    int matchedIndex = findMatch();
    cout << matchedIndex;
    return 0;
}