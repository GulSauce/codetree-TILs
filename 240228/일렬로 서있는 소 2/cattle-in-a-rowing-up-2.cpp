#include <iostream>

using namespace std;

int cows[101];

int main() {
    int N;
    cin >> N;
    
    for(int i = 1; i <= N; i++){
        cin >> cows[i];
    }

    int ans = 0;

    for(int i = 1; i <= N; i++){
        for(int j = i+1; j <= N; j++){
            for(int k = j+1; k <= N; k++){
                if(cows[i] <= cows[j] && cows[j] <= cows[k]){
                    ans++;
                }
            }
        }
    }

    cout << ans;
    return 0;
}