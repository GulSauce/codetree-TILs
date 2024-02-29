#include <iostream>

using namespace std;

int A[100];
int B[100];

int N, M;

bool checkIsAllMatched(int start, int end){
    bool matchedIndex[100] = {};

    for(int aIndex = start; aIndex <= end; aIndex++){
        for(int bIndex = 0; bIndex <= M-1; bIndex++){
            bool isMatched = false;

            if(A[aIndex] == B[bIndex] && matchedIndex[bIndex] == false){
                matchedIndex[bIndex] = true;
                isMatched = true;
            }

            if(isMatched){
                break;
            }
        }
    }

    for(int bIndex = 0; bIndex <= M-1; bIndex++){
        if(matchedIndex[bIndex] == false){
            return false;
        }
    }

    return true;
}

int main() {
    cin >> N >> M;

    for(int i = 0; i <= N-1; i++){
        cin >> A[i];
    }

    for(int i = 0; i <= N-1; i++){
        cin >> B[i];
    }

    int ans = 0;
    for(int i = 0; i <= N-M; i++){
        bool isAllMatched = checkIsAllMatched(i, i+M-1);
        if(isAllMatched){
            ans++;
        }
    }

    cout << ans;
    return 0;
}