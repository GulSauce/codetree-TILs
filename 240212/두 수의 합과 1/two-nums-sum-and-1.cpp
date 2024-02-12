#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    int n1, n2;
    cin >> n1 >> n2;
    
    string summedString = to_string(n1+n2);

    int oneCnt = 0;
    for(int i = 0; summedString[i]; i++){
        if(summedString[i] == '1'){
            oneCnt++;
        }
    } 

    cout << oneCnt;
    return 0;
}