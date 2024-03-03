#include <iostream>

using namespace std;

int board[11][11];
                
bool checkIsAllThrown(){
    for(int y = 0; y <= 10; y++){
        for(int x = 0; x <= 10; x++){
            if(1 <= board[y][x]){
                return false;
            }
        }
    }
    return true;
}

bool calcCanThrowAllCount(int i, int j, int k){
    //Dir == 0 x축 | Dir == 1 y축
    for(int iDir = 0; iDir <= 1; iDir++){
        for(int jDir = 0; jDir <= 1; jDir++){
            for(int kDir = 0; kDir <= 1; kDir++){
                for(int p = 0; p <= 10; p++){
                    if(iDir == 0){
                        board[i][p]--;
                    }
                    if(iDir == 1){
                        board[p][i]--;
                    }
                    if(jDir == 0){
                        board[j][p]--;
                    }
                    if(jDir == 1){
                        board[p][j]--;
                    }
                    if(kDir == 0){
                        board[k][p]--;
                    }
                    if(kDir == 1){
                        board[p][k]--;
                    }
                }
                if(checkIsAllThrown()){
                    return true;
                }
                for(int p = 0; p <= 10; p++){
                    if(iDir == 0){
                        board[i][p]++;
                    }
                    if(iDir == 1){
                        board[p][i]++;
                    }
                    if(jDir == 0){
                        board[j][p]++;
                    }
                    if(jDir == 1){
                        board[p][j]++;
                    }
                    if(kDir == 0){
                        board[k][p]++;
                    }
                    if(kDir == 1){
                        board[p][k]++;
                    }
                }
            }
        }
    }
    return false;
}

int main() {
    int N;
    cin >> N;

    while(N--){
        int x, y;
        cin >> x >> y;
        board[y][x] = 1;
    }

    int ans = 0;
    
    for(int i = 0; i <= 10; i++){
        for(int j = 0; j <= 10; j++){
            for(int k = 0; k <= 10; k++){
                if(calcCanThrowAllCount(i, j, k)){
                    cout << 1;
                    return 0;
                }
            }
        }
    }

    cout << 0;
    return 0;
}