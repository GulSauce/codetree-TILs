#include <iostream>

using namespace std;

bool isMagicNumber(int n){
    int digitSum = n /10 + n % 10;
    bool flag = n % 2 == 0 && digitSum % 5 == 0;
    return flag;
}

int main() {
    int n;
    cin >> n;
    bool flag = isMagicNumber(n);
    if(flag){
        cout << "Yes";
    }else{
        cout << "No";
    }
    return 0;
}