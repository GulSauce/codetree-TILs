#include <iostream>
#include <string>

using namespace std;

int main() {
    int num;
    cin >> num;

    string numString;
    numString = to_string(num);

    int sumValue = 0;
    for(int i = 0; numString[i]; i++){
        sumValue += numString[i] - '0';
    }

    cout << sumValue;
    return 0;
}