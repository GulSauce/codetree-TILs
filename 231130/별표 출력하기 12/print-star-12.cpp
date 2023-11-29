#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < n; i++)
        cout << "* ";
    cout << '\n';

    for(int i = 1; i < n; i++){
        for(int s = 0; s < n; s++){
            if(s % 2 == 1 && i <= s)
                cout << "* ";
            else
                cout << "  ";    
        }
        cout << '\n';
    }
    return 0;
}