#include <iostream>

using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    
    int c, d;
    cin >> c >> d;

    if(b + 1 <= c
    || d + 1 <= a){
        cout << (d- c) + (b - a);
    }
    else{
        cout << max(b, d) - min(a, c);
    }
    
    return 0;
}