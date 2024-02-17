#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int N, k;
    cin >> N >> k;
    
    int numbers[1000];
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers + N);
    cout << numbers[k-1];
    return 0;
}