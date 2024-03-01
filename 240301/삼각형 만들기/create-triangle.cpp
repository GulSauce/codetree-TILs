#include <iostream>

using namespace std;

pair<int, int> points[100];

int getYDist(int i, int j, int k){
    int yDist = -1;
    if(points[i].first == points[j].first){
        yDist = abs(points[i].second - points[j].second);
    }
    if(points[i].first == points[k].first){
        yDist = abs(points[i].second - points[k].second);
    }
    if(points[j].first == points[k].first){
        yDist = abs(points[j].second - points[k].second);
    }
 
    return yDist;
}

int getXDist(int i, int j, int k){
    int xDist = -1;
    if(points[i].second == points[j].second){
        xDist = abs(points[i].first - points[j].first);
    }
    if(points[i].second == points[k].second){
        xDist = abs(points[i].first - points[k].first);
    }
    if(points[j].second == points[k].second){
        xDist = abs(points[j].first - points[k].first);
    }

    return xDist;
}

int main() {
    int N;
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        int x, y;
        cin >> x >> y;
        points[i] = {x, y};
    }

    int triangleArea = 0;
    for(int i = 0; i <= N-1; i++){
        for(int j = i+1; j <= N-1; j++){
            for(int k = j+1; k <= N-1; k++){
                int xDist = getXDist(i, j, k);
                if(xDist == -1){
                    continue;
                }
                int yDist = getYDist(i, j, k);
                if(yDist == -1){
                    continue;
                }
                triangleArea = max(triangleArea, xDist * yDist);
            }
        }
    }

    cout << triangleArea;
    return 0;
}