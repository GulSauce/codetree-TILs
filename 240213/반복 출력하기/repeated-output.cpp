#include <iostream>
#include <string>
#include <cctype>

using namespace std;

void printByArgs(int cnt){
    while(cnt--){
        cout << "12345^&*()_" << '\n';
    }
}

int main() {
    int N; cin >> N;
    printByArgs(N);
    return 0;
}