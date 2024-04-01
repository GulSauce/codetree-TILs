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

    vector<int> storedNumbers = numbers;

    int p = 1;
    for(int i = 0; i <= 5; i++){
        vector<int> sortedNumbers[10];
        for(int number: storedNumbers){
            int digit = (number / p) % 10;
            sortedNumbers[digit].push_back(number);
        }
        
        int index = 0;
        for(int i = 0; i <= 9; i++){
            for(int number: sortedNumbers[i]){
                storedNumbers[index++] = number;
            }
        }
        p *= 10;
    }

    for(int nubmer : storedNumbers){
        cout << nubmer << ' ';
    }
    return 0;
}