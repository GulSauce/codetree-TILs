#include <iostream>

using namespace std;

int min(int a, int b, int c){
    int minValue = a;
    if(b < minValue){
        minValue = b;
    }
    if(c < minValue){
        minValue = c;
    }
    return minValue;
}

int main() {
    int a, b, c;
    cin >> a >> b >> c;
    int minValue = min(a, b, c);
    cout << minValue;
    return 0;
}