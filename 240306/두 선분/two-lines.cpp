#include <iostream>

using namespace std;

int main() {
    int x1, x2, x3, x4;
    cin >> x1 >> x2 >> x3 >> x4;

    if(x2 + 1 <= x3 || x4 + 1 <= x1){
        cout <<"nonintersecting";
    }
    else{
        cout << "intersecting";
    }
    return 0;
}