#include <iostream>
#include <string>

using namespace std;

int main() {
    int n; cin >> n;
    string str[20];
    for(int i = 0; i <= n-1; i++){
        cin >> str[i];
    }
    char ch; cin >> ch;

    int matchCnt = 0;
    int lengthSum = 0;
    for(int i = 0; i <= n-1; i++){
        if(str[i][0] != ch){
            continue;
        }
        matchCnt++;
        lengthSum += str[i].length();
    }

    cout << fixed;
    cout.precision(2);
    cout << matchCnt << ' ' << (double)lengthSum / matchCnt;
    return 0;
}