#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int N;  cin >> N;
    int numbers[1000];
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    int cnt = 0;
    int maxCnt = 0;
    for(int i = 0; i <= N-1; i++){
        if(1 <= i && numbers[i-1] == numbers[i]){
            cnt++;
        }else{
            cnt = 1;
        }
        maxCnt = max(maxCnt , cnt);
    }

    cout << maxCnt;
    return 0;
}