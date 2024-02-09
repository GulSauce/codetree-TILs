#include <iostream>
#include <string>

using namespace std;

int main() {
    for(int i = 1; i <= 10; i++){
        string str; cin >> str;
        if(i % 2 == 1){
            cout << str << '\n';
        }
    }
    return 0;
}