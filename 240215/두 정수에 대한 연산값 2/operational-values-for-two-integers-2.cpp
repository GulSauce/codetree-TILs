#include <iostream>

using namespace std;

void process(int& n1, int& n2){
    if(n1 > n2){
        n1 *= 2;
        n2 += 10;
    }else{
        n2 *= 2;
        n1 += 10;
    }
}

int main() {
    int a, b; cin >> a >> b;

    process(a, b);
    
    cout << a << ' ' << b;
    return 0;
}