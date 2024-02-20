#include <iostream>
#include <string>

using namespace std;

int daysOfMonth[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
int daySumOfMonth[13] = {0};
string dayToString[7] = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

void calcDaySumOfMonth(){
    for(int i = 2; i <=12; i++){
        daySumOfMonth[i] = daysOfMonth[i-1] + daySumOfMonth[i-1];
    }
}

int main() {
    int m1, d1, m2, d2;
    cin >> m1 >> d1 >> m2 >> d2;

    calcDaySumOfMonth();  

    int m1Days = daySumOfMonth[m1] + d1;
    int m2Days = daySumOfMonth[m2] + d2;

    int dayDiff = m2Days-m1Days;

    if(dayDiff <= 0){
        dayDiff += 7;
    }

    dayDiff %= 7;

    cout << dayToString[dayDiff];
    return 0;
}