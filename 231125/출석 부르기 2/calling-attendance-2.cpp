#include <iostream>

using namespace std;

int main() {
    int no;
    while(1){
        cin >> no;
        if(no == 1)
            cout << "John\n";
        else if(no == 2)
            cout << "Tom\n";
        else if(no == 3)
            cout << "Paul\n";
        else if(no == 4)
            cout << "Sam\n";
        else{
            cout << "Vacancy";
            break;
        }
    }
    return 0;
}