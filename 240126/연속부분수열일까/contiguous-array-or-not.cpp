#include <iostream>

using namespace std;

int main() {
    int n1, n2; cin >> n1 >> n2;
    int n1Array[100];
    int n2Array[100];
    for(int i = 0; i < n1; i++){
        cin >> n1Array[i];
    }
    for(int i = 0; i < n2; i++){
        cin >> n2Array[i];
    }

    bool isMatched = false;
    for(int i = 0; i <= n1-n2; i++){
        for(int j = 0; j < n2; j++){
            if(n1Array[i+j] != n2Array[j]){
                break;
            }    
            if(j == n2-1){
                isMatched = true;
                break;
            }
        }

        if(isMatched){
            break;
        }
    }
    
    if(isMatched){
        cout << "Yes";
    }
    else{
        cout << "No";
    }
    return 0;
}