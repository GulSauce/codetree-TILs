#include <iostream>

using namespace std;

int getResult(int curSeqNumber){
    if(curSeqNumber == 1){
        return 2;
    }
    if(curSeqNumber == 2){
        return 4;
    }
    return getResult(curSeqNumber - 1) * getResult(curSeqNumber - 2) % 100;
}

int main() {
    int N; cin >> N;
    int result = getResult(N);
    cout << result;
    return 0;
}