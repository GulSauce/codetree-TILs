#include <iostream>

using namespace std;

bool is369Exist(int number){
    while(1 <= number){
        int digitOne = number % 10;
        if(digitOne == 3 || digitOne == 6 || digitOne == 9){
            return true;
        }
        number /= 10;
    }
    return false;
}

int getMagicNumberCount(int a, int b){
    int cnt = 0;
    for(int i = a; i <= b; i++){
        if(is369Exist(i) || i % 3 == 0){
            cnt++;
        }
    }
    return cnt;
}

int main() {
    int a, b;
    cin >> a >> b;
    int cnt = getMagicNumberCount(a, b);
    cout << cnt;
    return 0;
}