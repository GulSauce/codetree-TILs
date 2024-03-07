#include <iostream>

using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    
    int c, d;
    cin >> c >> d;

    int left = min(a, c);
    int right = max(b, d);

    int emptyArea = 0;
    if(b + 1 <= c){
        emptyArea = c - b;
    }
    cout << right - left - emptyArea;
    return 0;
}