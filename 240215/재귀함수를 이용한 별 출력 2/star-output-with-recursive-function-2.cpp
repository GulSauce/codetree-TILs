#include <iostream>

using namespace std;

void printStar(int starCnt){
    if(starCnt == 0){
        return;
    }

    int loopCnt = starCnt;
    while(loopCnt--){
        cout << '*' << ' ';
    }
    cout << '\n';
    
    printStar(starCnt-1);

    loopCnt = starCnt;
    while(loopCnt--){
        cout << '*' << ' ';
    }
    cout << '\n';
}

int main() {
    int N; cin >> N;
    printStar(N);
    return 0;
}