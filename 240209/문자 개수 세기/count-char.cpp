#include <iostream>
#include <string>

using namespace std;

int main() {
    string str;
    getline(cin, str);

    char alphabet; cin >> alphabet;

    int matchCount = 0;
    for(char ch : str){
        if(ch == alphabet){
            matchCount++;
        }
    }

    cout << matchCount;
    return 0;
}