#include <iostream>

using namespace std;

int main() {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;

    int a1, b1, a2, b2;
    cin >> a1 >> b1 >> a2 >> b2;

    if(x2 + 1 <= a1
    || a2 + 1 <= x1
    || y2 + 1 <= b1
    || b2 + 1 <= y1){
        cout << "nonoverlapping";
    }
    else{
        cout << "overlapping";
    }
    return 0;
}