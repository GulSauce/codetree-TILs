#include <iostream>

using namespace std;

int calcStatus(int aPoint, int bPoint, int cPoint){
    if(aPoint + 1 <= bPoint){
        if(bPoint + 1 <= cPoint){
            return 0;
        }
        if(bPoint == cPoint){
            return 1;
        }
    }
    if(aPoint + 1 <= cPoint){
        if(cPoint + 1 <= aPoint){
            return 2;
        }
    }
    if(bPoint + 1 <= aPoint){
        if(aPoint + 1 <= cPoint){
            return 3;
        }
        if(aPoint == cPoint){
            return 4;
        }
    }
    if(bPoint + 1 <= cPoint){
        if(cPoint + 1 <= aPoint){
            return 5;
        }
    }
    if(cPoint + 1 <= aPoint){
         if(aPoint + 1 <= bPoint){
            return 6;
        }
        if(aPoint == bPoint){
            return 7;
        }
    }
    if(cPoint + 1 <= bPoint){
         if(bPoint + 1 <= aPoint){
            return 8;
        }
    }
    return 9;
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
        // cout << preAPoint << ' ' << preBPoint << ' ' << preCPoint << ' ' << preStatus << '\n';
        int status = calcStatus(aPoint, bPoint, cPoint);
        // cout << aPoint << ' ' << bPoint << ' ' << cPoint << ' ' << status << '\n';
        // cout << "================\n";
        if(preStatus != status){
            changeCount++;
        }
    }

    cout << changeCount;
    return 0;
}