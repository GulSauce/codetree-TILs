#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    string str; cin >> str;
    int sumValue = 0;
    for(int i = 0; str[i]; i++){
        char curCh = str[i];
        if(isdigit(curCh) == 0){
            continue;
        }
        int numValue = curCh - '0';
        sumValue += numValue;
    }
    cout << sumValue;
    return 0;
}