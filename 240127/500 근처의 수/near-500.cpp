#include <climits>
#include <iostream>

using namespace std;

int main() {
    int loopCount = 10;
    int maxUnder500 = 0;
    int minOver500 = INT_MAX;
    while(loopCount--){
        int num; cin >> num;
        if(num < 500){
            if(maxUnder500 < num){
                maxUnder500 = num;
            }
        }
        if(500 < num){
            if(num < minOver500){
                minOver500 = num;
            }
        }
    }
    cout << maxUnder500 << ' ' << minOver500;
    return 0;
}