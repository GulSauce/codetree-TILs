#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int start = 0;
    for(int i = 0; i < n; i++){
        for(int j = 1; j <= n; j++){
            if(i % 2 == 0){
                start += 1;
                cout << start << ' ';
            }
            if(i % 2 == 1){
                start += 2;
                cout << start << ' ';
            }
        }
        cout << '\n';
    }
    return 0;
}