#include <iostream>

using namespace std;

int N; 

int getFiboNumber(int seqNumber){
    if(seqNumber == 1){
        return 1;
    }
    if(seqNumber == 2){
        return 1;
    }
    
    return getFiboNumber(seqNumber-1)+getFiboNumber(seqNumber-2);
}

int main() {
    cin >> N;

    int fiboNumber = getFiboNumber(N);
    cout << fiboNumber;
    return 0;
}