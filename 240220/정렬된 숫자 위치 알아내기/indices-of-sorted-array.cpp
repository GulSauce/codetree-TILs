#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int N; cin >> N;
    pair<int, int> numbers[1000];
    for(int i = 0; i <= N-1; i++){
        int value;
        cin >> value;
        numbers[i] = make_pair(value, i);
    }

    sort(numbers, numbers + N);

    int changedIndex[1000];
    for(int i = 0; i <= N-1; i++){
        int oldIndex = numbers[i].second;
        changedIndex[oldIndex] = i;
    }

    for(int i = 0; i <= N-1; i++){
        cout << changedIndex[i] + 1 << ' ';
    }
    return 0;
}