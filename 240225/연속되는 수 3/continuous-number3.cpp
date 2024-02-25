#include <iostream>

using namespace std;

int main() {
    int N;
    cin >> N;
    
    int numbers[1000];
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    int cnt = 1;
    int ans = 0;
    for(int i = 0; i <= N-1 ;i++){
        if(1 <= i && 1 <= numbers[i-1] * numbers[i]){
            cnt++;
        }else{
            cnt = 1;   
        }
        ans = max(ans, cnt);
    }

    cout << ans;
    return 0;
}