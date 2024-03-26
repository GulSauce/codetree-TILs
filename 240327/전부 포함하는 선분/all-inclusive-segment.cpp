#include <iostream>
#include <climits>

using namespace std;

class LineInfo{
    public:
        int x1;
        int x2;

        LineInfo(int x1, int x2){
            this->x1 = x1;
            this->x2 = x2;
        }

        LineInfo(){}
};

int n;
LineInfo lineInfo[100];

int calcDist(int skipIndex){
    int leftX = INT_MAX;
    int rightX = 0;

    for(int i = 0; i <= n-1; i++){
        if(skipIndex == i){
            continue;
        }
        leftX = min(leftX, lineInfo[i].x1);
        rightX = max(rightX, lineInfo[i].x2);
    }
    return rightX - leftX;
}

int main() {
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        int x1, x2;
        cin >> x1 >> x2;
        lineInfo[i] = LineInfo(x1, x2);
    }

    int leftMostXIndex = 0;
    int leftMostX = INT_MAX;
    for(int i = 0; i <= n-1; i++){
        if(lineInfo[i].x1 + 1 <= leftMostX){
            leftMostXIndex = i;
            leftMostX = lineInfo[i].x1;
        }
    }

    int minDist = calcDist(leftMostXIndex);

    int rightMostXIndex = 0;
    int rightMostX = 0;
    for(int i = 0; i <= n-1; i++){
        if(rightMostX + 1 <= lineInfo[i].x2){
            rightMostXIndex = i;
            rightMostX = lineInfo[i].x2;
        }
    }

    minDist = min(minDist, calcDist(rightMostXIndex));

    cout << minDist;
    return 0;
}