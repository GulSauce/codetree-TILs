#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int N;

int hillHeight[1000];

int calcCost(int stdHeight){
    int cost = 0;
    for(int i = 0; i <= N-1; i++){
        int heightDiff = abs(hillHeight[i] - stdHeight);
        if(hillHeight[i] + 1 <= stdHeight){
            cost += heightDiff*heightDiff;
            continue;
        }
        if(heightDiff <= 17){
            continue;
        }

        cost += (heightDiff - 17)*(heightDiff - 17);
    }
    return cost;
}

int main() {
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        cin >> hillHeight[i];
    }

    sort(hillHeight, hillHeight + N);

    int minCost = INT_MAX;

    for(int i = hillHeight[0]; i <= hillHeight[N-1]; i++){
        minCost = min(minCost, calcCost(i));
    }

    cout << minCost;
    return 0;
}