#include <iostream>

using namespace std;

int main() {
    int start = 10;
    
    int num[10];
    for(int i = 0; i < 10; i++){
        cin >> num[i];
        if(num[i] == 0){
            start = i;
            break;
        }
    }

    for(int i = start-1; i >= 0; i--){
        cout << num[i] << ' ';
    }

    return 0;
}