#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;

    int aPoint = 0;
    int bPoint = 0;
    int changeCount = 0;
    while(n--){
        char c;
        int s;
        cin >> c >> s;
        int beforeApoint = aPoint;
        int beforeBpoint = bPoint;
        
        if(c=='A'){
            aPoint += s;
        }
        if(c=='B'){
            bPoint += s;
        }
        if(beforeBpoint + 1 <= beforeApoint){
            if(aPoint <= bPoint){
                changeCount++;
            }
        }
        if(beforeApoint + 1 <= beforeBpoint){
            if(bPoint <= aPoint){
                changeCount++;
            }
        }
        if(beforeApoint == beforeBpoint){
            if(bPoint + 1 <= aPoint
            || aPoint + 1 <= bPoint){
                changeCount++;
            }
        }
    }
    cout << changeCount;
    return 0;
}