#include <iostream>

using namespace std;

int main() {
    int n, t;
    cin >> n >> t;

    int numbers[1000];
    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    int cnt = 0;
    int ans = 0;
    for(int i = 0; i <= n-1; i++){
        if(t < numbers[i]){
            cnt++;
        }else{
             cnt = 0;
        }
        ans = max(ans, cnt);
    }

    cout << ans;
    return 0;
}