#include <iostream>

using namespace std;

int main() {
    int N;
    string str;
    cin >> N >> str;

    int ans = 0;
    for(int cIndex = 0; cIndex <= N-1; cIndex++){
        for(int oIndex = cIndex+1; oIndex <= N-1; oIndex++){
            for(int wIndex = oIndex+1; wIndex <= N-1; wIndex++){
                if(str[cIndex] == 'C'
                && str[oIndex] == 'O'
                && str[wIndex] == 'W'){
                    ans++;
                }
            }
        }
    }

    cout << ans;
    return 0;
}