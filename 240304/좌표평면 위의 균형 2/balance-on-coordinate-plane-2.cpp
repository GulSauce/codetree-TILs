#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int N;

class Point{
    public:
        int x;
        int y;

        Point(int x, int y){
            this->x = x;
            this->y = y;
        }

        Point(){}
};

Point point[100];

int calcMinMaxCount(int y, int x){
    int div1 = 0;
    int div2 = 0;
    int div3 = 0;
    int div4 = 0;

    for(int i = 0; i <= N-1; i++){
        int currentX = point[i].x;
        int currentY = point[i].y;

        if(currentX == x || currentY == y){
            return -1;
        }

        if(x + 1 <= currentX && y + 1<= currentY){
            div1++;
        }
        if(currentX <= x - 1 && y + 1 <= currentY){
            div2++;
        }
        if(currentX <= x - 1 && currentY <= y - 1){
            div3++;
        }
        if(x+1 <= currentX && currentY <= y-1){
            div4++;
        }
    }

    int minMax = max({div1, div2, div3, div4});
    return minMax;
} 

int main() {
    cin >> N;
    
    for(int i = 0; i <= N-1; i++){
        int x, y;
        cin >> x >> y;
        point[i] = Point(x, y);
    }

    int minMaxCount = INT_MAX;

    for(int y = 0; y <= 101; y++){
        for(int x = 0; x <= 101; x++){
            int currentMinMaxCount = calcMinMaxCount(y, x);

            if(currentMinMaxCount == -1){
                continue;
            }
            minMaxCount = min(minMaxCount, currentMinMaxCount);
        }
    }

    cout << minMaxCount;
    return 0;
}