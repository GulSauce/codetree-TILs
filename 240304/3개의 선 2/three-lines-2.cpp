#include <iostream>

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

Point points[20];
                
bool checkIsThrowAll(int i, int j, int k){
    bool isSucces = true;
    for(int p = 0; p <= N-1; p++){
        int currentX = points[p].x;
        int currentY = points[p].y;

        if(currentX != i && currentX != j && currentX != k){
            isSucces = false;
            break;
        }
    }
    if(isSucces){
        return true;
    }

    isSucces = true;
    for(int p = 0; p <= N-1; p++){
        int currentX = points[p].x;
        int currentY = points[p].y;

        if(currentX != i && currentX != j && currentY != k){
            isSucces = false;
            break;
        }
    }
    if(isSucces){
        return true;
    }

    isSucces = true;
    for(int p = 0; p <= N-1; p++){
        int currentX = points[p].x;
        int currentY = points[p].y;

        if(currentX != i && currentY != j && currentY != k){
            isSucces = false;
            break;
        }
    }
    if(isSucces){
        return true;
    }

    isSucces = true;
    for(int p = 0; p <= N-1; p++){
        int currentX = points[p].x;
        int currentY = points[p].y;

        if(currentY != i && currentY != j && currentY != k){
            isSucces = false;
            break;
        }
    }
    if(isSucces){
        return true;
    }

    return false;
}

int main() {
    cin >> N;

    for(int i = 0; i <= N-1; i++) {
        int x, y;
        cin >> x >> y;
        points[i] = Point(x, y);
    }
    
    for(int i = 0; i <= 10; i++){
        for(int j = 0; j <= 10; j++){
            for(int k = 0; k <= 10; k++){
                if(checkIsThrowAll(i, j, k)){
                    cout << 1;
                    return 0;
                }
            }
        }
    }

    cout << 0;
    return 0;
}