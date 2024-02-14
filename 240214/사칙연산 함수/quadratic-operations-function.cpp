#include <iostream>

using namespace std;

int add(int a, int c){
    return a+c;
}

int substract(int a, int c){
    return a-c;
}

int divide(int a, int c){
    return a/c;
}

int multiply(int a, int c){
    return a*c;
}

int main() {
    int a, c;
    char o;

    cin >> a >> o >> c;

    int result = 0;
    if(o == '+'){
        result = add(a, c);
    }

    if(o == '-'){
        result = substract(a, c);
    }
    if(o == '/'){
        result = divide(a, c);
    }
    if(o == '*'){
        result = multiply(a, c);
    }

    cout << a << ' ' << o << ' ' << c << " = " << result;
    return 0;
}