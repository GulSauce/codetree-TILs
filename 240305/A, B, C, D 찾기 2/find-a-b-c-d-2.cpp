#include <iostream>
#include <algorithm>

using namespace std;

int numbers[15];

bool checkIsValid(int A, int B, int C, int D){
    int currentArray[15] = {
        A, B, C, D, 
        A + B, B + C, C + D, D + A, A + C, B + D, 
        A + B + C, A + B + D, A + C + D, B + C + D, 
        A + B + C + D
    };

    sort(currentArray, currentArray + 15);

    for(int i = 0; i <= 14; i++){
        if(numbers[i] != currentArray[i]){
            return false;
        }
    }
    return true;
}

int main() {
    for(int i = 0; i <= 14; i++){
        cin >> numbers[i];
    }
    sort(numbers, numbers + 15);


    for(int A = 1; A <= 40; A++){
        for(int B = 1; B <= 40; B++){
            for(int C = 1; C <= 40; C++){
                for(int D = 1; D <= 40; D++){
                    if(checkIsValid(A, B, C, D)){
                        cout << A << ' ' << B << ' ' << C << ' ' << D << '\n';
                        return 0;
                    }
                }
            }
        }
    }
    return 0;
}