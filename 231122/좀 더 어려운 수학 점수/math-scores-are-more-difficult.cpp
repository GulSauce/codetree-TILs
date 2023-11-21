#include <iostream>

using namespace std;

int main() {
    int aMath, aEng; cin >> aMath >> aEng;
    int bMath, bEng; cin >> bMath >> bEng;

    if(bMath < aMath || aMath == bMath && bEng < aEng)
        cout << "A";
    else
        cout << "B";
    return 0;
}