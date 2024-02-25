#include <iostream>

using namespace std;

int main() {
    int N;  cin >> N;
    int numbers[1000];
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    int cnt = 0;
    for(int i = 0; i <= N-1; i++){
        if(i == 0 || numbers[i-1] != numbers[i]){
            cnt++;
        }
    }

    cout << cnt;
    return 0;
}