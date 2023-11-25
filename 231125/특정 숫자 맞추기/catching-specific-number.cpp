#include <iostream>

using namespace std;

int main() {
    int num;
    while(1){
        cin >> num;
        if(num < 25)
            cout << "Higher";
        if(num > 25)
            cout << "Lower";
        if(num == 25){
            cout << "Good";
            break;
        }
        cout << '\n';
    }
    return 0;
}