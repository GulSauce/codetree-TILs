#include <iostream>

using namespace std;

int main() {
    int num[10];
    int evenSum = 0;
    pair<int, int> threeAvgInfo = {0, 0};
    for(int i = 0; i < 10; i++){
        cin >> num[i];
    }
    for(int i = 1; i <= 10; i++){
        if(i % 2 == 0){
            evenSum += num[i-1];
        }
        if(i % 3 == 0){
            threeAvgInfo.first += num[i-1];
            threeAvgInfo.second++;
        }
    }
    cout << fixed;
    cout.precision(1);
    cout << evenSum << ' ';
    cout << (double)threeAvgInfo.first / threeAvgInfo.second << ' ';
    return 0;
}