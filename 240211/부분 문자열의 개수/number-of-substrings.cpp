#include <iostream>
#include <string>

using namespace std;

int main() {
    string A, B;
    cin >> A >> B;
    int bLength = B.length();
    int lastIdx = A.length() - bLength;
    int matchCnt = 0;
    for(int i = 0; i <= lastIdx; i++){
        bool matched = true;
        for(int j = 0; j <= bLength - 1; j++){
            if(A[i+j] != B[j]){
                matched = false;
            }
        }
        if(matched){
            matchCnt++;
        }
    }

    cout << matchCnt;
    return 0;
}