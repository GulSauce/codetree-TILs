#include <iostream>

using namespace std;

int main() {
    int evenNum[100];
    int evenCount = 0;
    
    for(int i = 0; i < 100; i++){
        int num; cin >> num;
        if(num % 2 == 1){
            continue;
        }

        evenNum[evenCount++] = num;
    }

    for(int i = 0; i < evenCount; i++){
        cout << evenNum[i] << ' ';
    }

    return 0;
}