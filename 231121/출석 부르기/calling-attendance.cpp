#include <iostream>

using namespace std;

int main() {
    int num; cin >> num;
    if(num == 1)
        cout << "John";
    if(num == 2)
        cout << "Tom";
    if(num == 3)
        cout << "Paul";
    if(num > 3)
        cout << "Vacancy"; 
    return 0;
}