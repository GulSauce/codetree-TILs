#include <iostream>

using namespace std;

void process(int& maxValue, int& minValue){
    maxValue += 25;
    minValue *= 2;
}

int main() {
    int a, b; cin >> a >> b;
    if(a > b){
        process(a, b);
    }
    else{
        process(b, a);
    }

    cout << a << ' ' << b;
    return 0;
}