#include <climits>
#include <iostream>

using namespace std;

int main() {
    int minVal = INT_MAX;
    int maxVal = INT_MIN;

    while(1){
        int num; cin >> num;

        if(num == 999 || num == -999){
            break;
        }

        if(num < minVal){
            minVal = num;
        }
        if(maxVal < num){
            maxVal = num;
        }
    }

    cout << maxVal << ' ' << minVal;

    return 0;
}