#include <climits>
#include <iostream>
#include <algorithm>

using namespace std;

int N;
pair<int, int> coordinates[101];

int getTaxiDistance(int skipIndex){
    int beforeIndex = 1;
    int taxiDistance = 0;
    for(int i = 2; i <= N; i++){
        if(skipIndex == i){
            continue;
        }
        taxiDistance += abs(coordinates[i].first-coordinates[beforeIndex].first)
        + abs(coordinates[i].second-coordinates[beforeIndex].second);
        beforeIndex = i;
    }
    return taxiDistance;
}

int main() {
    cin >> N;

    for(int i = 1; i <= N; i++){
        int x, y;
        cin >> x >> y;
        coordinates[i] = {x, y};
    }

    int taxiDistance = INT_MAX;
    for(int i = 2; i <= N-1; i++){
        int currentTaxiDistance = getTaxiDistance(i);
        taxiDistance = min(taxiDistance, currentTaxiDistance);
    }
    cout << taxiDistance;
    return 0;
}