#include <iostream>

using namespace std;

void printStars() {
    for(int i = 1; i <= 10; i++){
        cout << '*';
    }
}

int main() {
    for(int  y = 1; y <= 5; y++){
        printStars();
        cout << '\n';
    }
    return 0;
}