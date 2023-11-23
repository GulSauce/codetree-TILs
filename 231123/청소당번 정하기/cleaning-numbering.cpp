#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    
    int classRoom = 0;
    int floor = 0;
    int toilet = 0;

    for(int i = 2; i < n; i++){
        if((i-1) % 2 == 0)
            classRoom++;
        if((i-1) % 3 == 0)
            floor++;
        if((i-1) % 12 == 0)
            toilet++;
    }

    cout << classRoom << ' ' << floor << ' ' << toilet;
    return 0;
}