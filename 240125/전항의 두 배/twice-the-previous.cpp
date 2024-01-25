#include <iostream>

using namespace std;

int main() {
    int A1, A2; cin >> A1 >> A2;
    
    int num[10] = {A1, A2};

    
    for(int i = 2; i <= 9; i++){
        num[i] = num[i-1] + 2*num[i-2];
    }

    for(int i = 0; i <= 9; i++){
        cout << num[i] << ' ';
    }
    
    return 0;
}