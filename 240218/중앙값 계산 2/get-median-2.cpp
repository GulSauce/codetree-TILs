#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int n;
    cin >> n;

    int numbers[100];
    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
        if((i + 1) % 2 == 0){
            continue;
        }

        sort(numbers, numbers + i + 1);
        cout << numbers[i/2] << ' ';
    }
    return 0;
}