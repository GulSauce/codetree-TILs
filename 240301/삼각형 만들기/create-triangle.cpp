#include <iostream>

using namespace std;

pair<int, int> points[100];

int getTriangleArea(int i, int j, int k){
    return abs(points[i].first * points[j].second
    + points[j].first * points[k].second
    + points[k].first * points[i].second
    - (points[i].second * points[j].first
    + points[j].second * points[k].first
    + points[k].second * points[i].first
    ));
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
                if(points[i].first != points[j].first
                && points[i].first != points[k].first
                && points[j].first != points[k].first){
                    continue;
                }
                if(points[i].second != points[j].second
                && points[i].second != points[k].second
                && points[j].second != points[k].second){
                    continue;
                }
                int currentTriangleArea = getTriangleArea(i, j, k);
                triangleArea = max(triangleArea, currentTriangleArea);
            }
        }
    }

    cout << triangleArea;
    return 0;
}