#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    string RLE = "";
    
    char curTarget = str[0];
    int curCount = 1;

    for(int i = 1; str[i] != '\0'; i++){
        if(curTarget == str[i]){
            curCount++;
            continue;
        }
        
        RLE += curTarget;
        RLE += to_string(curCount);

        curTarget = str[i];
        curCount = 1;
    }

    RLE += curTarget;
    RLE += to_string(curCount);

    cout << RLE.length() << '\n' << RLE;
    return 0;
}