#include <iostream>

using namespace std;

int N;

void div2WhenEven(int* numbers){
    for(int i = 0; i <= N-1; i++){
        if(numbers[i] % 2 == 0){
            numbers[i] /= 2;
        }
    }
}

int main() {
    cin >> N;
    int numbers[100];
    for(int i = 0; i <= N-1; i++){
        cin >> numbers[i];
    }

    div2WhenEven(numbers);

    for(int i = 0; i <= N-1; i++){
        cout << numbers[i] << ' ';
    }
    return 0;
}