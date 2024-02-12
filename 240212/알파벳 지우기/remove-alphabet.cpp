#include <iostream>
#include <string>
#include <cctype>

using namespace std;

int main() {
    string A, B; cin >> A >> B;

    string ANumberString = "";
    for(int i = 0; A[i]; i++){
        char curCh = A[i];
        if(isdigit(curCh)){
            ANumberString += A[i];
        }
    }

    string BNumberString = "";
    for(int i = 0; B[i]; i++){
        char curCh = B[i];
        if(isdigit(curCh)){
            BNumberString += B[i];
        }
    }

    cout << stoi(ANumberString) + stoi(BNumberString);

    return 0;
}