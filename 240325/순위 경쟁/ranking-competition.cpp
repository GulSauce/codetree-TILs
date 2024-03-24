#include <iostream>
#include <algorithm>

using namespace std;

int calcStatus(int aPoint, int bPoint, int cPoint){
    int highestPoint = max({aPoint, bPoint, cPoint});
    if(highestPoint == aPoint){
        if(highestPoint == bPoint){
            if(highestPoint == cPoint){
                return 0;
            }
            return 1;
        }
        if(highestPoint == cPoint){
            return 2;
        }
        return 3;
    }
    if(highestPoint == bPoint){
        if(highestPoint == cPoint){
            return 4;
        }
        return 5;
    }
    if(highestPoint == cPoint){
        return 6;
    }
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
        int preBPoint = bPoint;
        int preCPoint = cPoint;

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