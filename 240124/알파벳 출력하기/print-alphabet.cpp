#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    char curAlphabet = 'A';
    for(int y = 0; y < n; y++){
        for(int x = 0; x <= y; x++){
            cout << curAlphabet++;
            if(curAlphabet == 'Z'+1){
                curAlphabet = 'A';
            }
        }
        cout << '\n';
    }
    return 0;
}