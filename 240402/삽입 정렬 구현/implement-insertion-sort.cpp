#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> numbers(n);

    for(int& number: numbers){
        cin >> number;
    }

    for(int i = 1; i <= n-1; i++){
        int j = i-1;
        int key = numbers[i];
        while(j >= 0 && key + 1 <= numbers[j]){
            numbers[j + 1] = numbers[j];
            j--;
        }
        numbers[j + 1] = key;
    }

    for(int number: numbers){
        cout << number << ' ';
    }
    return 0;
}