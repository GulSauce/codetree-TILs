#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    int n;
    cin >> n;

    int numbers[101] = {};
    int sortedNumbers[101] = {};
    for(int i = 1; i <= n; i++){
        cin >> numbers[i];
        sortedNumbers[i] = numbers[i];
    }

    sort(sortedNumbers + 1, sortedNumbers+n);

    int firstNumber = sortedNumbers[1];
    int secondNumber = -1;

    for(int i = 1; i <= n; i++){
        if(firstNumber + 1 <= sortedNumbers[i]){
            if(i <= n-1 && sortedNumbers[i+1] == sortedNumbers[i]){
                break;
            }
            secondNumber = sortedNumbers[i];
            break;
        }
    }

    if(secondNumber == -1){
        cout << -1;
        return 0;
    }

    for(int i = 1; i <= n; i++){
        if(numbers[i] == secondNumber){
            cout << i;
            break;
        }
    }
    return 0;
}