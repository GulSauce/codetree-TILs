#include <iostream>

using namespace std;

int main() {
    int num; cin >> num;
    int mulFiveCount = 0;

    for(int i = 1; i <= 10; i++){
        int mulNum =  num * i;
        cout << mulNum << ' ';

        if(mulNum % 5 == 0){
            mulFiveCount++;
        }
        if(mulFiveCount == 2){
            break;
        }
    }

    return 0;
}