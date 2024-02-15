#include <iostream>

using namespace std;

int N; 

void printFlower(int number){
    if(number == 0){
        return;
    }

    cout << number << ' ';

    int nxtNubmer = number-1;

    printFlower(nxtNubmer);

    cout << number << ' ';
}

int main() {
    cin >> N;
    printFlower(N);
    return 0;
}