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

    int minDist = INT_MAX;

    for(int i = 1; i <= 2; i++){
        int currentDist = pos[i]-pos[i-1];
        if(currentDist == 1){
            continue;
        }

        minDist = min(minDist, currentDist);
    }

    if(minDist == INT_MAX){
        cout << 0;
        return 0;
    }
    
    cout << minDist-1;
    return 0;
}