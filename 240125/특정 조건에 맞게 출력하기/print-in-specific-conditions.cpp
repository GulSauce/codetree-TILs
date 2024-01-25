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
            cin >> num / 2;
        }
        if(num % 2 == 1){
            cin >> num + 3;
        }
    }

    return 0;
}