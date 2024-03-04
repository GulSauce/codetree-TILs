#include <iostream>

using namespace std;

class RangeInfo{
    public:
        int a;
        int b;

        RangeInfo(int a, int b){
            this->a = a;
            this->b = b;
        }

        RangeInfo(){}
};

RangeInfo rangeInfoArray[10];

int n;

bool checkThisNumber(int number){
    for(int i = 0; i <= n-1; i++){
        number *= 2;
        int a = rangeInfoArray[i].a;
        int b = rangeInfoArray[i].b;
        if(number <= a-1 || b+1 <= number){
            return false;
        }
    }
    return true;
}

int main() {
    cin >>  n;

    for(int i = 0; i <= n-1; i++){
        int a, b;
        cin >> a >> b;
        rangeInfoArray[i] = RangeInfo(a, b);
    }

    for(int number = 1; number <= 10000; number++){
        if(checkThisNumber(number)){
            cout << number << '\n';
            return 0;
        }
    }
    return 0;
}