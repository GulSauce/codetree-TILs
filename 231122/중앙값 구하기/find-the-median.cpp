#include <iostream>

using namespace std;

int main() {
    int a, b, c; cin >> a >> b >> c;
    if(a <= b){
        if(c <= a)
            cout << a;
        else if(b <= c)
            cout << b;
        else
            cout << c;
    }

    else{
        if(a <= c)
            cout << a;
        else if(c <= b)
            cout << b;
        else
            cout << c;
    }
    return 0;
}