#include <iostream>
#include <algorithm>

using namespace std;

int N, K;
int bombNumberAtPosition[100];
int bombNumber[100];

int calcBoomCount(int number){
    int position[100];
    int cnt = 0;
    for(int i = 0; i <= N-1; i++){
        if(number == bombNumberAtPosition[i]){
            position[cnt++] = i;
        }
    }

    int bombCount = 0;
    for(int i = 1; i <= cnt-1; i++){
        if(position[i] - position[i-1] <= K){
            bombCount++;
        }
    }
    return bombCount;
}

int main() {
    cin >> N >> K;

    for(int i = 0; i <= N-1; i++){
        cin >> bombNumberAtPosition[i];
        bombNumber[i] = bombNumberAtPosition[i];
    }

    sort(bombNumber, bombNumber + N);

    int ansbombNumber = -1;
    int maxBoomCount = 1;
    for(int i = 0; i <= N-1; i++){
        int currentBombCount = calcBoomCount(bombNumber[i]);
        if(maxBoomCount <= currentBombCount){
            ansbombNumber = bombNumber[i];
            maxBoomCount = currentBombCount;
        }
    }
    cout << ansbombNumber;
    return 0;
}