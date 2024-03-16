#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int pos[3] = {};
    for(int i = 0; i <= 2; i++){
        cin >> pos[i];
    }

    sort(pos, pos+3);

    int contCount = 0;
    if(pos[0] + 1 == pos[1]){
        contCount++;
    }
    if(pos[1] + 1 == pos[2]){
        contCount++;
    }

    if(contCount == 2){
        cout << 0;
    }
    else if(contCount == 1){
        cout << 1;
    }
    else{
        cout << 2;
    }

    return 0;
}