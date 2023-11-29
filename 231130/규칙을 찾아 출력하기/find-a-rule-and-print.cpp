#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < n; i++){
        for(int s = 0; s < n; s++){
            if(i == 0 || s == n-1 || s < i)
                cout << "* ";
            else
                cout << "  "; 
        }
        cout << '\n';
    }
    return 0;
}