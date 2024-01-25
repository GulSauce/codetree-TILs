#include <iostream>

using namespace std;

int main() {
    int num[10];
    for(int i = 0; i < 10; i++){
        cin >> num[i];
        if(num[i] % 3 == 0){
            cout << num[i-1];
            break;          
        }
    }
    return 0;
}