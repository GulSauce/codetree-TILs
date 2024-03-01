#include <iostream>

int position[10];
int isOut[1000];
using namespace std;

int getCurrentStrike(int currentNumber, int presentNumber){
    int loopCount = 3;
    int strike = 0;
    while(loopCount--){
        if(presentNumber % 10 == currentNumber % 10){
            strike++;
        }
        presentNumber /= 10;
        currentNumber /= 10;
    }
    return strike;
}

int getCurrentBall(int currentNumber, int presentNumber){
    int ball = 0;
    if(currentNumber % 10 == presentNumber / 10 % 10 
    || currentNumber % 10 == presentNumber / 100){
        ball++;
    }
    if(currentNumber / 10 % 10 == presentNumber % 10 
    || currentNumber / 10 % 10 == presentNumber / 100){
        ball++;
    }
    if(currentNumber / 100 == presentNumber % 10 
    || currentNumber / 100 == presentNumber / 10 % 10){
        ball++;
    }
    return ball;
}

void checkProbability(int presentNumber, int strike, int ball){
    for(int i = 1; i <= 9; i++){
        for(int j = 1; j <= 9; j++){
            for(int k = 1; k <= 9; k++){
                if(i == j || i == k || j == k){
                    continue;
                }
                int currentNumber = i*100 + j*10 + k;
                if(strike != getCurrentStrike(currentNumber, presentNumber)){
                    isOut[currentNumber] = true;
                    continue;
                }
                if(ball != getCurrentBall(currentNumber, presentNumber)){
                    isOut[currentNumber] = true;
                    continue;
                }
            }
        }
    }
}

int main() {
    int N;
    cin >> N;

    while(N--){
        int presentNumber, strike, ball;
        cin >> presentNumber >> strike >> ball;
        checkProbability(presentNumber, strike, ball);
    }

    int ans = 0;
    for(int i = 1; i <= 9; i++){
        for(int j = 1; j <= 9; j++){
            for(int k = 1; k <= 9; k++){
                if(i == j || i == k || j == k){
                    continue;
                }
                int currentNumber = i*100 + j*10 + k;

                if(isOut[currentNumber] == false){
                    ans++;
                }
            }
        }
    }

    cout << ans;
    return 0;
}