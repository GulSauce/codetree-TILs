#include <iostream>

using namespace std;

int main() {
    char eng1, eng2;
    cin >> eng1 >> eng2;
    
    int sub;
    if(eng1 <= eng2){
        sub = eng2 - eng1;
    } else{
        sub = eng1- eng2;
    }

    cout << eng1 + eng2 << ' ' << sub;
    return 0;
}