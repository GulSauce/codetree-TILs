#include <iostream>

using namespace std;

int main() {
    char c;
    int n; 
    cin >> c >> n;
    if(c == 'A'){
        for(int i = 1; i  <= n; i++)
            cout << i << ' ';
    }
    else{
         for(int i = n; 1 <= i; i--)
            cout << i << ' ';
    }
    return 0;
}