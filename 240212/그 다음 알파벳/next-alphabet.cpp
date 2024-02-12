#include <iostream>

using namespace std;

int main() {
    char alphabet; 
    cin >> alphabet;

    int alphabetValue = (int)alphabet;
    char nextAlphbet = (char)(++alphabetValue);
    if(nextAlphbet == 'z' + 1){
        nextAlphbet = 'a';
    }
    cout << nextAlphbet;
    return 0;
}