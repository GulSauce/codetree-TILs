#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    string A, B;
    cin >> A >> B;

    string ANumberString = "";
    for(int i = 0; isdigit(A[i]); i++){
        ANumberString += A[i];
    }

    string BNumberString = "";
    for(int i = 0; isdigit(B[i]); i++){
        BNumberString += B[i];
    }

    cout << stoi(ANumberString) + stoi(BNumberString);
    return 0;
}