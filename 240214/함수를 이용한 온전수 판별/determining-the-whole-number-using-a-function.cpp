#include <iostream>

using namespace std;

bool isCompleteNumber(int number){
    if(number % 2 == 0){
        return false;
    }
    else if(number % 10 == 5){
        return false;
    }
    else if(number % 3 == 0 && number % 9 != 0){
        return false;
    }
    return true;
}

int main() {
    int a, b; cin >> a >> b;

    int cnt = 0;
    for(int i = a; i <= b; i++){
        if(isCompleteNumber(i)){
            cnt++;
        }
    }
    cout << cnt;
    return 0;
}