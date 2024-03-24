#include <iostream>

using namespace std;

int main() {
    string board[10];

    for(int y= 0; y <= 9; y++){
        cin >> board[y];
    }

    pair<int, int> bPosition;
    pair<int, int> lPosition;
    pair<int, int> rPosition;

    for(int y = 0; y <= 9; y++){
        for(int x= 0; x <= 9; x++){
            if(board[y][x]== 'B'){
                bPosition = {x, y};
            }
            if(board[y][x]== 'L'){
                lPosition = {x, y};
            }
            if(board[y][x]== 'R'){
                rPosition = {x, y};
            }
        }
    }

    if(lPosition.first == bPosition.first){
        int result = abs(lPosition.second-bPosition.second)-1;
        if(rPosition.first == lPosition.first){
            result += 2;
        }
        cout << result;
        return 0;
    }

    if(lPosition.second == bPosition.second){
        int result = abs(lPosition.first-bPosition.first)-1;
        if(rPosition.second == lPosition.second){
            result += 2;
        }
        cout << result;
        return 0;
    }

    int result = abs(lPosition.first-bPosition.first)
    + abs(lPosition.second-bPosition.second)
    - 1;
    
    cout << result;
    return 0;
}