#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    
    int classRoom = 0;
    int floor = 0;
    int toilet = 0;

    for(int i = 1; i < n; i++){
        if((i+1) % 12 == 0)
            toilet++;
        else if((i+1) % 3 == 0)
            floor++;
        else if((i+1) % 2 == 0)
            classRoom++;
    }

    cout << classRoom << ' ' << floor << ' ' << toilet;
    return 0;
}