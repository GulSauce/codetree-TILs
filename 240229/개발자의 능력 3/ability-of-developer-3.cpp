#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int sum = 0;
int statusArray[6];

int getCurrentMinDiff(int i, int j, int k){
    int firstSection = statusArray[i] + statusArray[j] + statusArray[k];
    int secondSection = sum - firstSection;
    return abs(firstSection - secondSection);
}

int main() {
    for(int i = 0; i <= 5; i++){
        cin >> statusArray[i];
        sum += statusArray[i];
    }
    
    int minDiff = INT_MAX;
    for(int i = 0; i <= 5; i++){
        for(int j = i+1; j <= 5; j++){
            for(int k = j+1; k <= 5; k++){
                minDiff = min(minDiff, getCurrentMinDiff(i, j, k));
            }
        }
    }

    cout << minDiff;
    return 0;
}