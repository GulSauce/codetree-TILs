#include <iostream>

using namespace std;

int board[10001];

int main() {
    int N, K;
    cin >> N >> K;
    while(N--){
        int position;
        char alphabet;
        cin >> position >> alphabet;
        int score = 0;
        if(alphabet == 'G'){
            score = 1;
        }
        if(alphabet == 'H'){
            score = 2;
        }
        board[position] = score;
    }

    int maxScore = 0;
    for(int i = 1; i <= 10001-K; i++){
        int currentScore = 0;
        for(int j = i; j <= i + K; j++){
            currentScore += board[j];
        }
        maxScore = max(maxScore, currentScore);
    }

    cout << maxScore;
    return 0;
}