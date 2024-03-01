#include <iostream>

using namespace std;

class Line{
    public:
        int x1;
        int x2;

        Line(int x1, int x2){
            this->x1 = x1;
            this->x2 = x2;
        }

        Line(){}
};

int N;
Line lines[100];

bool checkIsOverlapped(int index){
    int x1 = lines[index].x1;
    int x2 = lines[index].x2;

    for(int i = 0; i <= N-1; i++){
        if(i == index){
            continue;
        }
        int targetX1 = lines[i].x1;
        int targetX2 = lines[i].x2;
        if(x1 == targetX1 && x2 == targetX2){
            return true;
        }
        if(x1 + 1 <= targetX1 && targetX2-1 <= x2){
            return true;
        }
        if(targetX1 + 1 <= x1 && x2 + 1 <= targetX2){
            return true;
        }
    }
    return false;
}

int main() {
    cin >> N;
    for(int i = 0; i <= N-1; i++){
        int x1, x2;
        cin >> x1 >> x2;
        lines[i] = Line(x1, x2);
    }   

    int ans = 0;

    for(int i = 0; i <= N-1; i++){
        bool isOverlapped = checkIsOverlapped(i);
        if(isOverlapped == false){
            ans++;
        }
    }

    cout << ans;
    return 0;
}