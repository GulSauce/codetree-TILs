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

    if(num[0] < num[1]){
        firstMax = num[1];
        secondMax = num[0];
    }
    else{
        firstMax = num[0];
        secondMax = num[1];
    }
   
    for(int i = 2; i < N; i++){
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