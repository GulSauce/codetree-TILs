#include <iostream>

using namespace std;

int n1, n2;
int A[100], B[100]; 

bool checkIsSubSequence(){
    bool matched = false;
    for(int i = 0; i <= n1-n2; i++){
        bool curMatched = true;
        for(int j = 0; j <= n2-1; j++){
            if(A[i+j] != B[j]){
                curMatched = false;
            }
        }
        matched = curMatched;
        if(matched){
            break;
        }
    }
    return matched;
}

int main() {

    cin >> n1 >> n2;

    for(int i = 0; i <= n1-1; i++){
        cin >> A[i];
    }
    for(int i = 0; i <= n2-1; i++){
        cin >> B[i];
    }

    bool matched = checkIsSubSequence();
    if(matched){
        cout << "Yes";
    }
    else{
        cout << "No";
    }
    return 0;
}