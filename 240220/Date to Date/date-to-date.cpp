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

    int m2Days = daySumOfMonths[m2]+ d2;
    int m1Days = daySumOfMonths[m1]+ d1;

    int result = m2Days - m1Days + 1;

    cout << result;

    return 0;
}