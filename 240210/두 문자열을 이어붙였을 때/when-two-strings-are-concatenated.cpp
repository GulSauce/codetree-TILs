#include <iostream>
#include <string>

using namespace std;

int main() {
    string A, B; cin >> A >> B;
    if(A+B == B+A){
        cout << "true";
    }
    else{
        cout << "false";
    }
    return 0;
}