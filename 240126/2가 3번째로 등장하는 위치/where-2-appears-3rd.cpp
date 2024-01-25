#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;

    int twoCounter = 0;
    for(int i = 1; i <= n; i++){
        int num; cin >> num;
        if(num == 2){
            twoCounter++;
        }
        if(twoCounter == 3){
            cout << i;
            break;
        }
    }
    return 0;
}