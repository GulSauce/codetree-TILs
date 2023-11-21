#include <iostream>

using namespace std;

int main() {
    int mid, fin; cin >> mid >> fin;
    if(mid >= 90 && fin >= 95)
        cout << 100000;
    else if(mid >= 90 && fin >= 90)
        cout << 50000;
    else
        cout << 0;
    return 0;
}