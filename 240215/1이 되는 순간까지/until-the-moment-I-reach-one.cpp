#include <iostream>

using namespace std;

int getToOneCount(int number){
    if(number == 1){
        return 0;
    }
    int oneCount = 0;
    if(number % 2 == 0){
        oneCount = getToOneCount(number / 2) + 1;
    }
    else{
        oneCount = getToOneCount(number / 3) + 1;
    }
    return oneCount;
}

int main() {
    int N;
    cin >> N;
    int oneCount = getToOneCount(N);

    cout << oneCount;
    return 0;
}