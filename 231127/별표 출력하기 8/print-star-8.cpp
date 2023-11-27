#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < n; i++){
        if(i % 2 == 0){
            cout << "*\n";
            continue;
        }
        for(int s = 0; s <= i; s++)
            cout << "* ";
        cout << '\n';
    }
    return 0;
}