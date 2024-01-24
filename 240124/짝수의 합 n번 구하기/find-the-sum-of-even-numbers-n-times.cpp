#include <iostream>

using namespace std;

int getCurEvenSum(int start, int end){
    int sum = 0;
    for(int num = start; num <= end; num++){
        if(num % 2 == 0){
            sum += num;
        }
    }
    return sum;
}

int main() {
    int n; cin >> n;
    int evenSum = 0;
    while(n--){
        int a, b; cin >> a >> b;
        cout << getCurEvenSum(a, b) << '\n';
    }
    return 0;
}