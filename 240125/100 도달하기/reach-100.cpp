#include <iostream>

using namespace std;

int main() {
    int num[3];
    int n; cin >> n;
    num[1] = 1; num[2] = n;
    cout << num[1] << ' ' << num[2] << ' ';
    
    while(1){
        int curNum = num[1] + num[2];
        cout << curNum << ' ';
        if(100 <= curNum){
            break;
        }
        num[1] = num[2];
        num[2] = curNum;
    }
    return 0;
}