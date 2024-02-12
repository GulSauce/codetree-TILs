#include <iostream>
#include <string>

using namespace std;

int main() {
    string A, B; cin >> A >> B;

    if(A == B){
        cout << 0;
        return 0;
    }

    int Alength = A.length();
    string shiftedA = A.substr(Alength-1, 1) + A.substr(0, Alength-1);
    
    int n = 0;
    while(shiftedA != A){
        n++;
        if(shiftedA == B){
            break;
        }
        shiftedA = shiftedA.substr(Alength-1, 1) + shiftedA.substr(0, Alength-1);
    }

    if(shiftedA == A){
        cout << -1;
    }
    else{
        cout << n;
    }
    return 0;
}