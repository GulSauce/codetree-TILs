#include <iostream>

using namespace std;

int main() {
    int num; cin >> num;
    int mulNum[11];

    for(int i = 1; i <= 10; i++){
        mulNum[i] =  num * i;
    }

    int mulFiveCount = 0;

    for(int i = 1; i <= 10; i++){
        int curNum = mulNum[i];

        cout << curNum  << ' ';

        if(curNum  % 5 == 0){
            mulFiveCount++;
        }

        if(mulFiveCount == 2){
            break;
        }
    }

    return 0;
}