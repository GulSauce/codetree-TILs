#include <iostream>

using namespace std;

int main() {
    int m; cin >> m;
    while(m--){
        int n; cin >> n;
        int count = 0;
        while(n != 1){
            if(n % 2 ==0){
                n /= 2;
            }
            else{
                n = 3*n + 1;
            }
            count ++;
        }
        cout << count << '\n';
    }
    return 0;
}