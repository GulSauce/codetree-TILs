#include <iostream>

using namespace std;

int main() {
    char ch[6] = {'L', 'E', 'B', 'R', 'O', 'S'};
    int index = -1;

    char targetCh; cin >> targetCh;
    for(int i = 0; i <= 5; i++){
        if(ch[i] == targetCh){
            index = i;
        }
    }

    if(index == -1){
        cout << "None";
    }
    else{
        cout << index;
    }
    return 0;
}