#include <iostream>

using namespace std;

int A[100];

int getPermuSum(int start, int end){
    int permuSum = 0;
    for(int i = start-1; i <= end-1; i++){
        permuSum += A[i];
    }

    return permuSum;
}

int main() {
    int  n, m; cin >> n >> m;

    for(int i = 0; i <= n-1; i ++){
        cin >> A[i];
    }

    while(m--){
        int start, end;
        cin >> start >> end;
        int permuSum = getPermuSum(start, end);
        cout << permuSum << '\n';
    }
    return 0;
}