#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    int n; cin >> n;

    int sumValue = 0;
    while(n--){
        int num; cin >> num;
        sumValue += num;
    }

    string sumValueString = to_string(sumValue);
    int length = sumValueString.length();
    sumValueString = sumValueString.substr(1, length-1) + sumValueString.substr(0, 1);
    cout << sumValueString;
    return 0;
}