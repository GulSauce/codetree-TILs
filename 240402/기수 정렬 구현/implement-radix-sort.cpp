#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;
    
    vector<int> numbers(n);
    for(int& number : numbers){
        cin >> number;
    }

    int p = 1;
    for(int i = 0; i <= 5; i++){
        vector<int> sorted_numbers[10];
        for(int number: numbers){
            int digit = (number / p) % 10;
            sorted_numbers[digit].push_back(number);
        }
        
        int index = 0;
        for(int i = 0; i <= 9; i++){
            for(int number: sorted_numbers[i]){
                numbers[index++] = number;
            }
        }
        p *= 10;
    }

    for(int nubmer : numbers){
        cout << nubmer << ' ';
    }
    return 0;
}