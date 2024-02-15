#include <iostream>

using namespace std;

int N; 

void printIncreaseNumber(int number){
    cout << number << ' ';
    if(number == N){
        return;
    }
    printIncreaseNumber(++number);
}

void printDecreaseNumber(int number){
    cout << number << ' ';
    if(number == 1){
        return;
    }
    printDecreaseNumber(--number);
}

int main() {
    cin >> N;
    printIncreaseNumber(1);
    cout << '\n';
    printDecreaseNumber(N);
    return 0;
}