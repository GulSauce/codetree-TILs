#include <iostream>

using namespace std;

int main() {
    int loopCount = 10;
    int maxVal = 0;
    while(loopCount--){
        int num; cin >> num;
        if(maxVal < num){
            maxVal = num;
        }
    }

    cout << maxVal;
    
    return 0;
}