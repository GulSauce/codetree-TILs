#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int num = 1;
    for(int y = 0; y < n; y++){
        for(int x = 0; x < n; x++){
            if(x < y){
                cout << "  ";
            }
            else{
                cout << num++ << ' ';
            }
            if(num == 10){
                num = 1;
            }
        }
        cout << '\n';
    }
    return 0;
}