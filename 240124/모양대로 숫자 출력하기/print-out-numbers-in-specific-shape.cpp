#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 5; y >= 1; y--){
        for(int x = 5; x >= 1; x--){
            if(x > y){
                cout << "  ";
            }
            else{
                cout << x << ' ';
            }
        }
        cout << '\n';
    }
    return 0;
}