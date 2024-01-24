#include <iostream>

using namespace std;

int divCount(int number){
    int cnt = 0;
    for(int i = 1; i <= number; i++){
        if(number % i == 0){
            cnt++;
        }
    }
    return cnt;
}

int main() {
    int start, end; cin >> start >> end;
    int cnt = 0;
    for(int curNumber = start; curNumber <= end; curNumber++){
        if(3 == divCount(curNumber)){
            cnt++;
        }
    }
    cout << cnt;
    return 0;
}