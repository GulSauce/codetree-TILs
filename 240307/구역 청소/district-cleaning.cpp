#include <iostream>

using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    
    int c, d;
    cin >> c >> d;

    int left = min(a, c);
    int right = max(b, d);

    cout << right - left;
    return 0;
}