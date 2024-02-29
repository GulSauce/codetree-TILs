#include <iostream>

using namespace std;

int a, b, c;

bool checkIsMatched(int first, int second, int third){
 
    if(a-2 <= first && first <= a+2){
        return true;
    }
    if(b-2 <= second && second <= b+2){
        return true;
    }
    if(c-2 <= third && third <= c+2){
        return true;
    }
    return false;
}

int main() {
    int N;
    cin >> N;
    cin >> a >> b >> c;

    int ans = 0;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            for(int k = 1; k <= N; k++){
                bool isMatched = checkIsMatched(i, j, k);
                if(isMatched){
                    ans++;
                }
            }
        }
    }
    cout << ans;
    return 0;
}