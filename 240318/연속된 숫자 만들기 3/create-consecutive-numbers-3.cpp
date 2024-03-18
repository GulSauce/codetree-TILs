#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

int main() {
    
    int pos[3] = {};

    for(int i = 0; i<= 2; i++){
        cin >> pos[i];
    }

    sort(pos, pos+3);

    int maxDist = 0;

    for(int i = 1; i <= 2; i++){
        int currentDist = pos[i]-pos[i-1];
        maxDist = max(maxDist, currentDist);
    }
    
    cout << maxDist-1;
    return 0;
}