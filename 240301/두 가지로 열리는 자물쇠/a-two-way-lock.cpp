#include <iostream>

using namespace std;

int N;
int a1, b1, c1;
int a2, b2, c2;

bool checkIsMatchedByFirstComb(int i, int j, int k){
    int distBorder1 = 3;
    int distBoder2 = abs(N-3);
    int currentIDiff = abs(a1 - i);
    if(distBorder1 <= currentIDiff && currentIDiff <= distBoder2){
        return false;
    }
    int currentJDiff = abs(b1 - j);
    if(distBorder1 <= currentJDiff && currentJDiff <= distBoder2){
        return false;
    }
    int currentKDiff = abs(c1 - k);
    if(distBorder1 <= currentKDiff && currentKDiff <= distBoder2){
        return false;
    }
    return true;
}

bool checkIsMatchedBySecondComb(int i, int j, int k){
    int distBorder1 = 3;
    int distBoder2 = abs(N-3);
    int currentIDiff = abs(a2 - i);
    if(distBorder1 <= currentIDiff && currentIDiff <= distBoder2){
        return false;
    }
    int currentJDiff = abs(b2 - j);
    if(distBorder1 <= currentJDiff && currentJDiff <= distBoder2){
        return false;
    }
    int currentKDiff = abs(c2 - k);
    if(distBorder1 <= currentKDiff && currentKDiff <= distBoder2){
        return false;
    }
    return true;
}

int main() {
    cin >> N;
    cin >> a1 >> b1 >> c1;
    cin >> a2 >> b2 >> c2;

    int ans = 0;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            for(int k = 1; k <= N; k++){
                bool isMatchedByFirstComb = checkIsMatchedByFirstComb(i, j, k);
                bool isMatchedBySecondComb = checkIsMatchedBySecondComb(i, j, k);
                if(isMatchedByFirstComb || isMatchedBySecondComb){
                    ans++;
                }
            }
        }
    }
    cout << ans;
    return 0;
}