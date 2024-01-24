#include <iostream>

using namespace std;

int main() {
    for(int y = 1;  y <= 19; y++){
        for(int x = 1;  x <= 19; x++){
            cout << y << " * " << x << " = " << y*x;
            if(x % 2 == 1 && x == 19 || (x % 2 == 0)){
                cout << '\n';
            }
            else if(x % 2 == 1){
                cout << " / ";
            }
        }
    }
    return 0;
}