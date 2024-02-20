#include <iostream>

using namespace std;

int daysOfMonths[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
int daySumOfMonths[13] = {0};

void calcDaySumofMonths(){
    for(int i = 2; i <= 12; i++){
        daySumOfMonths[i] = daySumOfMonths[i-1] + daysOfMonths[i-1];
    }
}

int main() {
    int m1, d1, m2, d2;
    cin >> m1 >> d1 >> m2 >> d2;

    calcDaySumofMonths();

    int mDiff = daySumOfMonths[m2]-daySumOfMonths[m1];
    int dDiff = d2-d1;

    int result = mDiff + dDiff;
    cout << result + 1;

    return 0;
}