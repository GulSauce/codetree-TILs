#include <iostream>
#include <algorithm>

using namespace std;

class CoordinateInfo{
    public:
        int index;
        int x;
        int y;

        CoordinateInfo(int index, int x, int y){
            this->index = index;
            this->x = x;
            this->y = y;
        }

        CoordinateInfo(){}
};

bool cmp(const CoordinateInfo& a, const CoordinateInfo& b){
    int aDist = abs(a.x) + abs(a.y);
    int bDist = abs(b.x) + abs(b.y);
    
    if(aDist == bDist){
        return a.index < b.index;
    }
    return aDist < bDist;
}

int main() {
    int N;
    cin >> N;

    CoordinateInfo coordinateInfo[1000];

    for(int i = 0; i <= N-1; i++){
        int x, y;
        cin >> x >> y;
        coordinateInfo[i] = CoordinateInfo(i+1, x, y);
    }

    sort(coordinateInfo, coordinateInfo+N, cmp);

    for(int i = 0; i <= N-1; i++){
        cout << coordinateInfo[i].index << '\n';
    }
    return 0;
}