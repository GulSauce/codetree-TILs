#include <iostream>

using namespace std;

int daysOfMonth[13] = {0, 31 , 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
int daySumOfMonth[13] = {0};

void calcDaySumOfMonth(){
    for(int i = 2; i <= 12; i++){
        daySumOfMonth[i] = daySumOfMonth[i-1] + daysOfMonth[i-1];
    }
}

int getDayStringIndex(string& dayString){
    if(dayString == "Mon"){
        return 0;
    }if(dayString == "Tue"){
        return 1;
    }if(dayString == "Wed"){
        return 2;
    }if(dayString == "Thu"){
        return 3;
    }if(dayString == "Fri"){
        return 4;
    }if(dayString == "Sat"){
        return 5;
    }if(dayString == "Sun"){
        return 6;
    }
}

int main() {
    int m1, m2;
    int d1, d2;
    cin >> m1 >> d1 >> m2 >> d2;

    string dayString;
    cin >> dayString;
    
    calcDaySumOfMonth();
    int d1Day = daySumOfMonth[m1] + d1;
    int d2Day = daySumOfMonth[m2] + d2;

    int dayDiff = d2Day - d1Day;
    int dayStringindex = getDayStringIndex(dayString);

    int ans = 0;
    for(int i = 0; i <= dayDiff; i++){
        int curDayIndex = i % 7;
        if(curDayIndex == dayStringindex){
            ans++;
        }
    }

    cout << ans;

    return 0;
}