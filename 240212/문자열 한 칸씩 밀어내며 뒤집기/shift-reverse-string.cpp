#include <iostream>
#include <string>

using namespace std;

int main() {
    string str;
    cin >> str;
    int q;
    cin >> q;

    int length = str.length();
    while(q--){
        int flag;
        cin >> flag;
        if(flag == 1){
            str = str.substr(1, length-1) + str.substr(0, 1);
        }
        if(flag == 2){
            str =  str.substr(length - 1, 1) + str.substr(0, length-1);
        }
        if(flag == 3){
            string flipedStr = "";
            for(int i = length - 1; 0 <= i; i--){
                flipedStr += str[i];
            }
            str = flipedStr;
        }
        cout << str << '\n';
    }
    return 0;
}