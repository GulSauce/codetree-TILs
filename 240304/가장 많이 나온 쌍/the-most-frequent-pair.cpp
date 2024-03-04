#include <iostream>

using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    
    pair<int, int> numbers[100];

    for(int i = 0; i <= m-1; i++){
        int a, b;
        cin >> a >> b;
        if(b <= a){
            numbers[i] = {b,a};
        }
        else{
            numbers[i] = {a,b};
        }
    }

    int maxCount = 0;
    for(int first = 1; first <= 9; first++){
        for(int second = first+1; second <= 9; second++){
            int currentCount = 0;
            for(int i = 0; i <= m-1; i++){
                if(numbers[i].first != first || numbers[i].second != second){
                    continue;
                }
                currentCount++;
            }
            maxCount = max(maxCount, currentCount);
        }
    }
    cout << maxCount;
    return 0;
}