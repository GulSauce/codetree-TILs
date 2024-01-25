#include <iostream>

using namespace std;

int main() {
    int n, q; cin >> n >> q;
    int num[100];
    for(int i = 0; i < n; i++){
        cin >> num[i];
    }

    while(q--){
        int type; cin >> type;
        if(type == 1){
            int a; cin >> a;
            cout << num[a-1];
        }
        if(type == 2){
            int a; cin >> a;
            int index = 0;
            for(int i = 0; i < n; i++){
                if(num[i] != a){
                    continue;
                }
                index = i+1;
                break;
            }
            cout << index;
        }
        if(type == 3){
            int a, b; cin >> a >> b;
            for(int i = a-1; i < b; i++){
                cout << num[i] << ' ';
            }
        }
        cout << '\n';
    }
    return 0;
}