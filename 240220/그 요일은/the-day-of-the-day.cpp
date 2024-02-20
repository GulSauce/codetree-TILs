#include <iostream>
#include <string>
#include <map>

using namespace std;

int daysOfMonth[13] = {0, 31 , 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
int daySumOfMonth[13] = {0};
map<string, int> dayStringIndexDict;

void addDayStringToDictionary(){
    dayStringIndexDict["Mon"] = 0;
    dayStringIndexDict["Tue"] = 1;
    dayStringIndexDict["Wed"] = 2;
    dayStringIndexDict["Thu"] = 3;
    dayStringIndexDict["Fri"] = 4;
    dayStringIndexDict["Sat"] = 5;
    dayStringIndexDict["Sun"] = 6;
}

void calcDaySumOfMonth(){
    for(int i = 2; i <= 12; i++){
        daySumOfMonth[i] = daySumOfMonth[i-1] + daysOfMonth[i-1];
    }
}

int getDayDiffByString(string& dayString){
    int monIndex = dayStringIndexDict["Mon"];
    int dayStringIndex = dayStringIndexDict[dayString];
    return dayStringIndex - monIndex;
}

int main() {
    int m1, m2;
    int d1, d2;
    cin >> m1 >> d1 >> m2 >> d2;

    string dayString;
    cin >> dayString;

    addDayStringToDictionary();
    int dayDiffByString = getDayDiffByString(dayString);
    
    calcDaySumOfMonth();
    int d1Day = daySumOfMonth[m1] + d1;
    int d2Day = daySumOfMonth[m2] + d2;

    int dayDiff = d2Day - d1Day - dayDiffByString;

    int result = 0;

    if(0 <= dayDiff){
        result = dayDiff / 7 + 1;
    }

    cout << result;

    return 0;
}