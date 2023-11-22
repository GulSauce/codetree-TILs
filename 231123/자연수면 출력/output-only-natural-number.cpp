#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    if(1 <= a){
        while(b--)
            cout << a;
    }
    else
        cout << 0;
        
    return 0;
}