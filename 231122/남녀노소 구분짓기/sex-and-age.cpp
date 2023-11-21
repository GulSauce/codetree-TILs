#include <iostream>

using namespace std;

int main() {
    int sex, age; cin >> sex >> age;
    if(sex == 0){
        if(19 <= age)
            cout << "MAN";
        else
            cout << "BOY";
    }
    else{
        if(19 <= age)
            cout << "WOMAN";
        else
            cout << "GIRL";
    }
    return 0;
}