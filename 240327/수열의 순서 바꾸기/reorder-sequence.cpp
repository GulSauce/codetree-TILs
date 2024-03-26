#include <iostream>

using namespace std;

int numbers[100];

int main() {
    int N;
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    int maxAscDist = 1;
    for(int i = N-2; 0 <= i; i--){
        if(numbers[i] + 1 <= numbers[i+1]){
            maxAscDist++;
        }
        else{
            break;
        }
    }

    cout << N - maxAscDist;
    return 0;
}