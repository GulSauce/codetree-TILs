#include <iostream>

using namespace std;

int calcStatus(int aPoint, int bPoint, int cPoint){
    if(bPoint + 1 <= aPoint){
        if(cPoint +1 <= aPoint){
            return 0;
        }
        if(cPoint == aPoint){
            return 1;
        }
        if(aPoint + 1 <= cPoint){
            return 2;
        }
    }
    if(cPoint + 1 <= aPoint){
        if(bPoint == aPoint){
            return 4;
        }
        if(aPoint + 1 <= bPoint){
            return 5;
        }
    }
    if(aPoint + 1 <= bPoint){
        if(bPoint == cPoint){
            return 6;
        }
    }
    return 7;
}

int main() {
    int n;
    cin >> n;
    
    int aPoint = 0;
    int bPoint = 0;
    int cPoint = 0;
    
    int changeCount = 0;
    while(n--){
        char c;
        int s;
        cin >> c >> s;

        int preAPoint = aPoint;
        int preCPoint = bPoint;
        int preBPoint = cPoint;
        if(c == 'A'){
            aPoint += s;
        }
         if(c == 'B'){
            bPoint += s;
        }
         if(c == 'C'){
            cPoint += s;
        }

        int preStatus = calcStatus(preAPoint, preBPoint, preCPoint);
        int status = calcStatus(aPoint, bPoint, cPoint);
        if(preStatus != status){
            changeCount++;
        }
    }

    cout << changeCount;
    return 0;
}