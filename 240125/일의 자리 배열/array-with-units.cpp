#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    cout << a << ' ' << b << ' ';

    for(int i = 3; i <= 10; i++){
        int c = (a + b)%10;
        cout << c << ' ';

        a = b;
        b = c;
    }
    return 0;
}