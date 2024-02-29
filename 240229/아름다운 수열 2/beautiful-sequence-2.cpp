#include <iostream>
#include <algorithm>

using namespace std;

int A[100];
int B[100];

int N, M;

bool checkIsAllMatched(int start){
    int copiedA[100];
    for(int i = 0; i <= M-1; i++){
        copiedA[i] = A[i+start];
    }

    sort(copiedA, copiedA+M);

    for(int i = 0; i <= M-1; i++){
        if(copiedA[i] != B[i]){
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

    sort(B, B + M);

    int ans = 0;
    for(int i = 0; i <= N-M; i++){
        bool isAllMatched = checkIsAllMatched(i);
        if(isAllMatched){
            ans++;
        }
    }

    cout << ans;
    return 0;
}