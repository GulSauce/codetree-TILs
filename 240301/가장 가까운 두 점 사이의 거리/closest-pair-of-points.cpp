#include <iostream>
#include <climits>
using namespace std;

int main() {
    pair<int, int> points[100];
    
    int n;
    cin >> n;
    for(int i = 0; i <= n-1; i++){
        int x, y;
        cin >> x >> y;
        points[i] = {x, y};
    }

    int minDist = INT_MAX;
    for(int i = 0; i <= n-1; i++){
        for(int j = i+1; j <= n-1; j ++){
            int xDiff = points[i].first - points[j].first;
            int yDiff = points[i].second - points[j].second;
            int currentDist = xDiff * xDiff + yDiff * yDiff;
            minDist = min(minDist, currentDist); 
        }
    }

    cout << minDist;
    return 0;
}