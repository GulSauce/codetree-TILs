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
    bool wrongFlag = false;
    if(o == '+'){
        result = add(a, c);
    }

    else if(o == '-'){
        result = substract(a, c);
    }
    else if(o == '/'){
        result = divide(a, c);
    }
    else if(o == '*'){
        result = multiply(a, c);
    }
    else{
        wrongFlag = true;
    }
    
    if(wrongFlag){
        cout << "False";
    }
    else{
        cout << a << ' ' << o << ' ' << c << " = " << result;
    }
    return 0;
}