#include <iostream>

using namespace std;

int main() {
    int p1A;
    char p1S; 
    cin >> p1A >> p1S;

    int p2A;
    char p2S; 
    cin >> p2A >> p2S;

    cout << (19 <= p1A && p1S == 'M' ||  19 <= p2A && p2S == 'M');
    return 0;
}