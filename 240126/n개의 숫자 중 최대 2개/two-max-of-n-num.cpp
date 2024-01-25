#include <iostream>
#include <climits>

using namespace std;

int main() {
    int firstMax = INT_MIN;
    int secondMax = INT_MIN;
    int num[100];
    
    int N; cin >> N;
    for(int i = 0; i < N; i++){
        cin >> num[i];
    }

    firstMax = num[0];
    secondMax = num[0];

    for(int i = 1; i < N; i++){
        if(firstMax <= num[i]){
            secondMax = firstMax;
            firstMax = num[i];
        }
        if(secondMax < num[i] && num[i] < firstMax){
            secondMax = num[i];
        }
    }

    cout << firstMax << ' ' << secondMax;
    return 0;
}