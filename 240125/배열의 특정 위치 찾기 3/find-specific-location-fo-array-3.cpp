#include <iostream>

using namespace std;

int main() {
    int num[100];
    for(int i = 0; i < 100; i++){
        cin >> num[i];
        if(num[i] != 0){
            continue;
        }
        cout << num[i-3] + num[i-2] + num[i-1];
        break;
    }
    return 0;
}