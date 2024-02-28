#include <iostream>
#include <string>

using namespace std;

int N, M;
int dx[8] = {1, 1, 0, -1, -1, -1, 0, 1};
int dy[8] = {0, 1, 1, 1, 0, -1, -1, -1};

string strings[50];
char targetCharArray[3] = {'L', 'E', 'E'};

bool isInRange(int x, int y){
    return 0 <= x && x <= M-1 && 0 <= y && y <= N-1;
}

int main() {
    cin >> N >> M;

    for(int i = 0; i <= N-1; i++){
        cin >> strings[i];
    }

    int ans = 0;
    for(int y = 0; y <= N-1; y++){
        for(int x = 0; x <= M-1; x++){
            if(strings[y][x] != targetCharArray[0]){
                continue;
            }
            for(int direction = 0; direction <= 7; direction++){
                int currentX = x;
                int currentY = y;
                int targetCharIndex = 1;
                int matchedCount = 1;
                while(true){
                    if(targetCharIndex == 3){
                        break;
                    }
                    char targetChar = targetCharArray[targetCharIndex++];
                    int testX = currentX + dx[direction];
                    int testY = currentY + dy[direction];
                    if(!isInRange(testX, testY) || strings[testY][testX] != targetChar){
                        break;
                    }

                    matchedCount++;
                    currentX += dx[direction];
                    currentY += dy[direction];
                }

                if(matchedCount == 3){
                    ans++;
                }
            }
        }
    }

    cout << ans;
    return 0;
}