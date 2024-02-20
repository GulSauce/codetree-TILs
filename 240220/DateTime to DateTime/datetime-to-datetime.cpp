#include <iostream>

using namespace std;

int a, b, c;

bool checkIsBefore(){
    if(b <= 11 && c <= 10){
        return true;
    }
    return false;
}

int main() {
    cin >> a >> b >> c;

    bool isBefore = checkIsBefore();
    if(isBefore){
        cout << -1;
        return 0;
    }

    int dDiff = a - 11;
    int hDiff = b - 11;
    int mDiff = c - 11;

    int result = dDiff*24*60 + hDiff*60 + mDiff;

    cout << result;
    return 0;
}