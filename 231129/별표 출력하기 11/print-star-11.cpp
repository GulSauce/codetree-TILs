#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < 2*n+1; i++){
        if(i % 2 == 0){
            for(int s = 0; s < 2*n+1; s++)
                cout << "* ";
            cout << '\n';
            continue;
        }
        for(int s = 0; s < 2*n+1; s++){
            if(s % 2 == 1)
                cout << "  "; 
            else
                cout << "* ";
        }
        cout << '\n';
    }
}