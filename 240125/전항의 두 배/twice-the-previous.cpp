#include <iostream>

using namespace std;

int main() {
    int A1, A2; cin >> A1 >> A2;
    cout << A1 << ' ' << A2 << ' ';
    
    for(int i = 3; i <= 10; i++){
        int A3 = A2 + 2*A1;
        cout << A3 << ' ';
        A1 = A2;
        A2 = A3;
    }
    return 0;
}