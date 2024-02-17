#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int n;
    cin >> n;
    int numbers[100];
    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }
    sort(numbers, numbers+n);
    for(int i = 0; i <= n-1; i++){
        cout << numbers[i] << ' ';
    }
    cout << '\n';
    sort(numbers, numbers+n, greater<int>());
    for(int i = 0; i <= n-1; i++){
        cout << numbers[i] << ' ';
    }
    return 0;
}