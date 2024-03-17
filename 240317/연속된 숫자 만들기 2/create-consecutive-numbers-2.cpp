#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int pos[3] = {};
    for(int i = 0; i <= 2; i++){
        cin >> pos[i];
    }

    sort(pos, pos+3);

    if(pos[0] + 1 == pos[1] && pos[1] + 1 == pos[2]){
        cout << 0;
        return 0;
    }

    if(pos[0] + 2 == pos[1] || pos[1] + 2 == pos[2]){
        cout << 1;
        return 0;
    }

    cout << 2;
    return 0;
}