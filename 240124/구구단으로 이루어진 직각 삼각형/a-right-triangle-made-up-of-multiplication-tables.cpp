#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y= 1; y <= n; y++){
        for(int x = 1; x+y <= n+1; x++){
            cout << y << " * " << x << " = " << y*x;
            if(x+y == n+1){
                continue;
            }
            cout << " / ";
        }
        cout << '\n';
    }
    return 0;
}