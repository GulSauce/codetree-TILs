#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    for(int i = a; i <= b;){
        cout << i << ' ';

        if(i % 2 == 1)
            i *= 2;

        else if(i % 2 == 0)
            i += 3;
    }
    return 0;
}