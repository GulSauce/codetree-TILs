#include <iostream>

using namespace std;

int N;

void printStar(int cnt){
    int loopCnt = cnt;
    while(loopCnt--){
        cout << '*';
    }

    cout << '\n';
    if(cnt == N){
        return;
    }
    
    printStar(++cnt);
}

int main() {
    cin >> N;
    printStar(1);
    return 0;
}