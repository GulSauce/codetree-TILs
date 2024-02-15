#include <iostream>

using namespace std;

void printString(int recursiveCnt){
    if(recursiveCnt == 0){
        return;
    }
    cout << "HelloWorld" << '\n';
    printString(--recursiveCnt);
}

int main() {
    int N; cin >> N;
    printString(N);
    return 0;
}