#include <iostream>

using namespace std;

int main() {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >>y2;
    
    int a1, b1, a2, b2;
    cin >> a1 >> b1 >> a2 >>b2;

    int leftLowerX = min(x1, a1);
    int leftLowerY = min(y1, b1);

    int rightUpperX = max(x2, a2);
    int rightUpperY = max(y2, b2);

    cout << (rightUpperX - leftLowerX) * (rightUpperY-leftLowerY);
     
    return 0;
}