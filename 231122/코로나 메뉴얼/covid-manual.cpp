#include <iostream>

using namespace std;

int main() {
    char disA; int tempA; cin >> disA >> tempA;
    char disB; int tempB; cin >> disB >> tempB;
    char disC; int tempC; cin >> disC >> tempC;

    int aCnt = 0;
    if(disA == 'Y' && tempA >= 37){
        if(disB == 'Y' && tempB >= 37)
            cout << "E";
        else if(disC == 'Y' && tempC >= 37)
            cout << "E";
        else
            cout << "N";
    }
    
    if(disB == 'Y' && tempB >= 37){
        if(disC == 'Y' && tempC >= 37)
            cout << "E";
        else
            cout << "N";
    }
    return 0;
}