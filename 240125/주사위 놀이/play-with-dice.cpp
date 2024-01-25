#include <iostream>

using namespace std;

int main() {
    int diceCount[7] = {};
    int loopCount = 10;

    while(loopCount--){
        int result; cin >> result;
        diceCount[result]++;
    }

    for(int i = 1; i <= 6; i++){
        cout << i << " - " << diceCount[i] << '\n';
    }

    return 0;
}