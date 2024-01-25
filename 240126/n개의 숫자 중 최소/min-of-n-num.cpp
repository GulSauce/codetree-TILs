#include <iostream>
#include <climits>

using namespace std;

int main() {
    int minVal = INT_MAX;
    int minCount = 0;

    int n; cin >> n;
    while(n--){
        int num; cin >> num;

        if(minVal < num){
            continue;
        }
        
        if(minVal == num){
            minCount++;
            continue;
        }

        minVal = num;
        minCount = 1;
    }

    cout << minVal << ' ' << minCount;
    return 0;
}