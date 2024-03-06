#include <iostream>

using namespace std;

bool checkIsIntersected(int x1, int x2, int x3, int x4){
    if(x3 <= x1 && x1 <= x4){
        return true;
    }
    if(x3 <= x2 && x2 <= x4){
        return true;
    }
    if(x1 <= x3 && x3 <= x2){
        return true;
    }
    if(x1 <= x4 && x4 <= x2){
        return true;
    }
    return false;
}

int main() {
    int x1, x2, x3, x4;
    cin >> x1 >> x2 >> x3 >> x4;

    if(checkIsIntersected(x1,x2,x3,x4)){
        cout << "intersecting";
    }else{
        cout << "nonintersecting";
    }
    return 0;
}