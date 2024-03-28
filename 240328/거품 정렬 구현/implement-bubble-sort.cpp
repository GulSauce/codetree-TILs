#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;
    
    vector<int> numbers(n);
    for(int& number : numbers){
        cin>> number;
    }

    int loopCount = n-1;
    while(loopCount--){
        for(int i = 0; i <= n-2; i++){
            if(numbers[i+1] <= numbers[i]){
                swap(numbers[i+1], numbers[i]);
            }
        }
    }

    for(int number : numbers){
        cout << number << ' ';
    }
    return 0;
}