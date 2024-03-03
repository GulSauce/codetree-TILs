#include <iostream>

using namespace std;

int N;

bool board[11][11];
                
bool checkIsThrowAll(int i, int j, int k){
    if(i == 0 && j == 1 && k == 2){
        cout << "HELLO\n";
    }
    int count = 0;
    for(int p = 0; p <= 10; p++){
        if(board[p][i] || board[p][j] || board[p][k]){
            count++;
        }
    }
    if(count == N){
        return true;
    }

    count = 0;
    for(int p = 0; p <= 10; p++){
        if(board[p][i] || board[p][j] || board[k][p]){
            count++;
        }
    }
    if(count == N){
        return true;
    }
    
    count = 0;
    for(int p = 0; p <= 10; p++){
        if(board[p][i] || board[j][p] || board[k][p]){
            count++;
        }
    }
    if(count == N){
        return true;
    }

    count = 0;
    for(int p = 0; p <= 10; p++){
        if(board[i][p] || board[j][p] || board[k][p]){
            count++;
        }
    }
    if(count == N){
        return true;
    }

    return false;
}

int main() {
    cin >> N;

    for(int i = 0; i <= N-1; i++) {
        int x, y;
        cin >> x >> y;
        board[y][x] = true;
    }
    
    for(int i = 0; i <= 10; i++){
        for(int j = 0; j <= 10; j++){
            for(int k = 0; k <= 10; k++){
                if(checkIsThrowAll(i, j, k)){
                    cout << 1;
                    return 0;
                }
            }
        }
    }

    cout << 0;
    return 0;
}