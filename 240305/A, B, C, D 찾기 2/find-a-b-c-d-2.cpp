#include <iostream>

using namespace std;

int numbers[15];

bool checkIsExist(bool* isUsed, int target){
    for(int i = 0; i <= 14; i++){
        if(isUsed[i]){
            continue;
        }
        if(target == numbers[i]){
            isUsed[i] = true;
            return true;
        }
    }

    return false;
}

bool checkIsValid(int A, int B, int C, int D){
    bool isUsed[15] = {};
 
    if(checkIsExist(isUsed, A) == false){
        return false;
    }
    if(checkIsExist(isUsed, B) == false){
        return false;
    }

    if(checkIsExist(isUsed, C) == false){
        return false;
    }

    if(checkIsExist(isUsed, D) == false){
        return false;
    }
    
    if(checkIsExist(isUsed, A + B) == false){
        return false;
    }

    if(checkIsExist(isUsed, B + C) == false){
        return false;
    }

    if(checkIsExist(isUsed, C + D) == false){
        return false;
    }

    if(checkIsExist(isUsed, D + A) == false){
        return false;
    }

    if(checkIsExist(isUsed, A + B + C) == false){
        return false;
    }

    if(checkIsExist(isUsed, A + B + D) == false){
        return false;
    }
    
    if(checkIsExist(isUsed, A + C + D) == false){
        return false;
    }

    if(checkIsExist(isUsed, B + C + D) == false){
        return false;
    }

    if(checkIsExist(isUsed, A + B + C + D) == false){
        return false;
    }

    return true;
}

int main() {
    for(int i = 0; i <= 14; i++){
        cin >> numbers[i];
    }

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