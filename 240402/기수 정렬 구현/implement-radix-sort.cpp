#include <iostream>
#include <vector>

using namespace std;

int getDigit(int number, int digitWhere){
    while(digitWhere--){
        number /= 10;
    }
    return number % 10;
}

int main() {
    int n;
    cin >> n;
    
    vector<int> numbers(n);
    for(int& number : numbers){
        cin >> number;
    }


    for(int i = 0; i <= 5; i++){
        vector<int> sorted_numbers[10];
        for(int number: numbers){
            int digit = getDigit(number, i);
            sorted_numbers[digit].push_back(number);
        }
        
        int index = 0;
        for(int i = 0; i <= 9; i++){
            for(int number: sorted_numbers[i]){
                numbers[index++] = number;
            }
        }
    }

    for(int nubmer : numbers){
        cout << nubmer << ' ';
    }
    return 0;
}