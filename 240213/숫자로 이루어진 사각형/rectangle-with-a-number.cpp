#include <iostream>

using namespace std;

void printByArgs(int N){
    int curNum = 1;
    
    for(int y = 0; y <= N-1; y++){
        for(int x = 0; x <= N-1; x++){
            cout << curNum++ << ' ';
            if(curNum == 10){
                curNum = 1;
            }
        }
        cout << '\n';
    }
}

int main() {
    int N; cin >> N;
    printByArgs(N);
    return 0;
}