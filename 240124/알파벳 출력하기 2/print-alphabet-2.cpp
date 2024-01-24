#include <iostream>

using namespace std;

int main() {
    char curAlphabet = 'A';
    int n; cin>> n;
    for(int y = 0; y < n; y++){
        for(int x= 0; x < n; x++){
            if(x < y){
                cout << "  ";
                continue;
            }
            cout << curAlphabet++ << ' ';
        }
        cout << '\n';
    }
    return 0;
}