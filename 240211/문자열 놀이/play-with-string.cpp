#include <iostream>
#include <string>

using namespace std;

int main() {
    string s; cin >> s;
    int q; cin >> q;
    while(q--){
        int flag; cin >> flag;
        if(flag == 1){
            int a, b; cin >> a >> b;
            char aCh = s[a-1];
            s[a-1] = s[b-1];
            s[b-1] = aCh;
            cout << s;
        }
        else if(flag == 2){
            char a, b; cin >> a >> b;
            for(int i = 0; s[i] != '\0'; i++){
                if(s[i] == a){
                    s[i] = b;
                }
            }
            cout << s;
        }
        cout << '\n';
    }
    return 0;
}