#include <iostream>

using namespace std;

int main() {
    char eng1, eng2;
    cin >> eng1 >> eng2;

    int eng1Value = (int) eng1;
    int eng2Value = (int) eng2;
     
    int sub;
    if(eng1Value <= eng2Value){
        sub = eng2Value - eng1Value;
    } else{
        sub = eng1Value- eng2Value;
    }

    cout << eng1Value + eng2Value << ' ' << sub;
    return 0;
}