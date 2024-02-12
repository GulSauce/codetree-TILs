#include <iostream>

using namespace std;

int main() {
    char alphabet; 
    cin >> alphabet;

    char nextAlphbet = (char)((int)++alphabet);
    if(nextAlphbet == 'z' + 1){
        nextAlphbet = 'a';
    }
    cout << nextAlphbet;
    return 0;
}