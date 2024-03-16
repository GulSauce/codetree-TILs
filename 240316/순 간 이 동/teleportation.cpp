#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int A, B, x, y; 
    cin >> A >> B >> x >> y;

    int minDist = abs(B-A);
    minDist = min(minDist, abs(x-A) + abs(y-B));
    minDist = min(minDist, abs(y-A) + abs(x-B));

    cout << minDist;
    return 0;
}