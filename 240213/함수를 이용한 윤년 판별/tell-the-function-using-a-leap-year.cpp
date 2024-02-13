#include <iostream>

using namespace std;

bool isLeapYear(int year){
    if(year % 4 == 0 && year % 100 == 0 && year % 400 == 0){
        return true;
    }
    if(year % 4 == 0 && year % 100 == 0){
        return false;
    }
    if(year % 4 == 0){
        return true;
    }
    return false;
}

int main() {
    int y; cin >> y;
    bool flag = isLeapYear(y);
    if(flag){
        cout << "true";
    }else{
        cout << "false";
    }
    return 0;
}