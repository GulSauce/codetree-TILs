#include <iostream>

using namespace std;

int N; 

void printFlower(int number){
    if(number == 0){
        return;
    }

    cout << number << ' ';

    printFlower(number-1);

    cout << number << ' ';
}

int main() {
    cin >> N;
    printFlower(N);
    return 0;
}