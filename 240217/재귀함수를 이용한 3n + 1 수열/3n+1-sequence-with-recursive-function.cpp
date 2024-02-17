#include <iostream>

using namespace std;

int getCollatzCount(int number){
    if(number == 1){
        return 0;
    }
    int nextCollatzNumber = 0;
    if(number % 2 == 0){
        nextCollatzNumber = number / 2; 
    }
    if(number % 2 == 1){
        nextCollatzNumber = number * 3 + 1;
    }
    return getCollatzCount(nextCollatzNumber) + 1;
}

int main() {
    int n;
    cin >> n;

    int collatzCount = getCollatzCount(n);
    cout << collatzCount;
    return 0;
}