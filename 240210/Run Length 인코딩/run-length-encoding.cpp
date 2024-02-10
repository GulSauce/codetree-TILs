#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    string RLE = "";
    
    for(int i = 0; str[i] != '\0';){
        int j = i;
        int cnt = 0;
        while(str[i] == str[j]){
            j++;
            cnt++;
        }
        RLE += str[i];
        RLE += cnt + '0';
        i = j;
    }

    cout << RLE.length() << '\n' << RLE;
    return 0;
}