#include <iostream>
#include <string>

using namespace std;

bool checkIsOtherAlphabetOver2(string A){
    char firstAlphabet = A[0];
    int lastIndex = A.length() - 1;

    bool isOtherAlphabetOver2 = false;
    for(int i = 0; i <= lastIndex; i++){
        if(firstAlphabet != A[i]){
            isOtherAlphabetOver2 = true;
        }
    }
    return isOtherAlphabetOver2;
}

int main() {
    string A;
    cin >> A;

    bool isOtherAlphabetOver2 = checkIsOtherAlphabetOver2(A);

    if(isOtherAlphabetOver2){
        cout << "Yes";
    }else{
        cout << "No";
    }
    return 0;
}