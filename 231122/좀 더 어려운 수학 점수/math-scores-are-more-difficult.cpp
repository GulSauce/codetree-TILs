#include <iostream>

using namespace std;

int main() {
    int aMath, aEng; cin >> aMath >> aEng;
    int bMath, bEng; cin >> bMath >> bEng;

    if(aMath < bMath)
        cout << 'B';
    else if(bMath < aMath)
        cout << 'A';
    else if(aEng < bEng)
        cout << 'B';
    else
        cout << 'A';
    return 0;
}