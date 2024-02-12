#include <iostream>

using namespace std;

int main() {
    char alphabet;
    cin >> alphabet;

    int alphabetValue = (int)alphabet;
    char beforeAlphabet = (char)--alphabet;
    if(beforeAlphabet == (int)'a' - 1){
        beforeAlphabet = 'z';
    }

    cout << beforeAlphabet;

    return 0;
}