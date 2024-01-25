#include <iostream>

using namespace std;

int main() {
    int num;
    
    for(int i = 0; i < 100; i++){
        cin >> num;
        if(num == 0){
            break;
        }
        if(num % 2 == 0){
            cout << num / 2;
        }
        if(num % 2 == 1){
            cout << num + 3;
        }
        cout << ' ';
    }

    return 0;
}