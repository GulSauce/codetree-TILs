#include <iostream>

using namespace std;

int N;
pair<int, int> matchHistory[100];

int getWinCount(int i, int j, int k){
    // 가위바위보 숫자 1, 2, 3은 각각 i, j, k에 대응함 
    // index = 0 가위 index = 1 바위 index = 2 보

    int winCount = 0;

    for(int i = 0; i <= N-1; i++){
        int first = matchHistory[i].first;
        int second = matchHistory[i].second;

        if(first == 1){
            if(second == 2){
                if((i - 1 + 3) % 3 == j){
                    winCount++;
                }
            }
            if(second == 3){
                if((i - 1 + 3) % 3 == k){
                    winCount++;
                }
            }
        }
        if(first == 2){
            if(second == 1){
                if((j - 1 + 3) % 3 == i){
                    winCount++;
                }
            }
            if(second == 3){
                if((j - 1 + 3) % 3 == k){
                    winCount++;
                }
            }
        }
        if(first == 3){
            if(second == 1){
                if((k - 1 + 3) % 3 == i){
                    winCount++;
                }
            }
            if(second == 2){
                if((k - 1 + 3) % 3 == j){
                    winCount++;
                }
            }
        }
    }

    return winCount;
}

int main() {
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        int first, second;
        cin >> first >> second;

        matchHistory[i] = {first, second};
    }

    int maxWinCount = 0;
    for(int i = 0; i <= 2; i++){
        for(int j = 0; j <= 2; j++){
            for(int k = 0; k <= 2; k++){
                if(i == j || i == k || j == k){
                    continue;
                }

                int winCount = getWinCount(i, j, k);
                maxWinCount = max(maxWinCount, winCount);
            }
        }
    }

    cout << maxWinCount;
    return 0;
}