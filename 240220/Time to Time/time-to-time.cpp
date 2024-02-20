#include <iostream>

using namespace std;

int main() {
    int a, b, c, d;
    cin >> a >> b >> c >> d;
    int hDiff = c - a;
    int mDiff = d - b;
    cout <<  hDiff*60 + mDiff;
    return 0;
}