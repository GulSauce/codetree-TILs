#include <iostream>

using namespace std;

int main() {
    int a; cin >> a;
    if(a < 10 || 20 < a)
        cout << "yes";
    else
        cout << "no";
    return 0;
}