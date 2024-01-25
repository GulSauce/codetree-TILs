#include <iostream>

using namespace std;

int main() {
    int numberCount[10];
    int n; cin >> n;
    while(n--){
        int num; cin >> num;
        numberCount[num]++;
    }

    for(int i = 1; i <= 9; i++){
        cout << numberCount[i] << '\n';
    }
    return 0;
}