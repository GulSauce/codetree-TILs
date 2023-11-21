#include <iostream>

using namespace std;

int main() {
    int mid, fin; cin >> mid >> fin;
    if(mid < 90)
        cout << 0;
    else if(95 <= fin)
        cout << 100000;
    else if(90 <= fin)
        cout << 50000;
    else
        cout << 0;
    return 0;
}