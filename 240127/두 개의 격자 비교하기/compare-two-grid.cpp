#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    int matrix1[10][10];
    int matrix2[10][10];

    int loopCount = 2;
    while(loopCount--){
        for(int y = 0; y <= n-1; y++){
            for(int x = 0; x <= m-1; x++){
                cin >> matrix1[y][x];
            }
        }
        for(int y = 0; y <= n-1; y++){
            for(int x = 0; x <= m-1; x++){
                cin >> matrix2[y][x];
            }
        }
    }


    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= m-1; x++){
            if(matrix1[y][x] == matrix2[y][x]){
                cout << 0;
            }
            else{
                cout << 1;
            }
            cout << ' ';
        }
        cout << '\n';
    }
       
    return 0;
}