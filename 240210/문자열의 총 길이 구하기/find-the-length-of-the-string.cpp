#include <iostream>
#include <string>

using namespace std;

int main() {
    int lengthSum = 0;
    for(int i = 0; i <= 9; i++){
        string str; cin >> str;
        lengthSum += str.length();
    }

    cout << lengthSum;
    return 0;
}