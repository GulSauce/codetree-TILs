#include <iostream>
#include <string>

using namespace std;

int main() {
    string A, B; cin >> A >> B;
    int bLength = B.length();
    while(A.find(B)!= string::npos){
        int searchedIdx = A.find(B);
        A.erase(searchedIdx, bLength);
    }
    cout << A;
    return 0;
}