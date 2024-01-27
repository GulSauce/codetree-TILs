#include <iostream>

using namespace std;

int nums[2][4];

void getRowAvg() {
    for(int y = 0; y < 2; y++){
        int sum = 0;
        for(int x = 0; x < 4; x++){
            sum += nums[y][x];
        }
        cout << (double)sum / 4 << ' ';
    }
    cout << '\n';
}

void getColumnAvg() {
    for(int x = 0; x < 4; x++){
        int sum = 0;
        for(int y = 0; y < 2; y++){
             sum += nums[y][x];
        }
        cout << (double)sum / 2 << ' ';
    }
    cout << '\n';
}

void getAllAvg() {
    int sum = 0;
    for(int x = 0; x < 4; x++){
        for(int y = 0; y < 2; y++){
             sum += nums[y][x];
        }
    }
    cout << (double)sum / 8;
}

int main() {

    for(int y= 0; y < 2; y++){
        for(int x=  0; x < 4; x++){
            cin >> nums[y][x];
        }
    }

    cout << fixed;
    cout.precision(1);

    getRowAvg();
    getColumnAvg();
    getAllAvg();
}