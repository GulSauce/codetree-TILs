#include <iostream>
#include <cstring>

using namespace std;

int bombNumber[101];

int main() {
    int N, K;
    cin >> N >> K;

    for(int i = 0; i <= N-1; i++){
        cin >> bombNumber[i];
    }

    int maxBombNumber = -1;
    for(int i = 0; i <= N-K-1; i++){
        for(int j = i+1; j <= i+K; j++){
            if(bombNumber[i] != bombNumber[j]){
                continue;
            }
            maxBombNumber = max(maxBombNumber, bombNumber[j]);
        }
    }

    cout << maxBombNumber;
    return 0;
}