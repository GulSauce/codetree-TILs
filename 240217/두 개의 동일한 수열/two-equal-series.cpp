#include <iostream>
#include <algorithm>

using namespace std;

int n; 
int A[100];
int B[100];

bool checkIsSame(){
    bool isSame = true;
    for(int i = 0; i <= n-1; i++){
        if(A[i] == B[i]){
            continue;
        }
        isSame = false;
        break;
    }
    return isSame;
}

int main() {
    cin >> n;
    for(int i = 0; i <= n-1; i++){
        cin >> A[i];
    }
    for(int i = 0; i <= n-1; i++){
        cin >> B[i];
    }

    sort(A, A+n);
    sort(B, B+n);
    bool isSame = checkIsSame();
    if(isSame){
        cout << "Yes";
    }
    else{
        cout << "No";
    }
    return 0;
}