#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < 2*n; i++){
        if(i % 2 == 0){
            for(int s = 0; s <= i / 2; s++)
                cout << "* ";
            cout << '\n';
        }
        
        else{
            for(int s = n - i / 2; s > 0; s--)
                cout << "* ";
            cout << '\n';
        }
    }
    return 0;
}