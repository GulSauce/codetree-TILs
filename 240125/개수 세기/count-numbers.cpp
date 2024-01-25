#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    int cnt = 0;
    while(n--){
        int num; cin >> num;
        if(num == m){
            cnt++;
        }
    }
    cout << cnt;
    return 0;
}