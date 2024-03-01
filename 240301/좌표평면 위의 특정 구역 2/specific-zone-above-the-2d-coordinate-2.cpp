#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

class Point{
    public:
        int x;
        int y;
        int index;

        Point(int x, int y, int index){
            this->x = x;
            this->y = y;
            this->index = index;
        }
        Point(){}
};

bool cmpByX(Point& a, Point& b){
    return a.x < b.x;
}

bool cmpByY(Point& a, Point& b){
    return a.y < b.y;
}

int main() {
    Point pointsByX[100];
    Point pointsByY[100];

    int N;
    cin >> N;
    for(int i = 0; i <= N-1; i++){
        int x, y;
        cin >> x >> y;
        pointsByX[i] = Point(x, y, i);
        pointsByY[i] = Point(x, y, i);
    }    

    sort(pointsByX, pointsByX+N, cmpByX);
    sort(pointsByY, pointsByY+N, cmpByY);

    int area = INT_MAX;

    int minX = 0;
    int maxX = 0;
    int minY = 0;
    int maxY = 0;
    for(int pointToRemoveIndex = 0; pointToRemoveIndex <= N-1; pointToRemoveIndex++){
        minX = pointsByX[0].x;
        if(pointsByX[0].index == pointToRemoveIndex){
            minX = pointsByX[1].x;
        }
        maxX = pointsByX[N-1].x;
        if(pointsByX[N-1].index == pointToRemoveIndex){
            maxX = pointsByX[N-2].x;
        }
        minY = pointsByX[0].y;
        if(pointsByY[0].index == pointToRemoveIndex){
            minX = pointsByX[1].y;
        }
        maxY = pointsByY[N-1].y;
        if(pointsByY[N-1].index == pointToRemoveIndex){
            maxY = pointsByY[N-2].y;
        }
        int currentArea = (maxX-minX)*(maxY-minY);
        area = min(area, currentArea);
    }

    cout << area;


    return 0;
}