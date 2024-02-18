#include <iostream>
#include <string>
#include <tuple>

using namespace std;

int main() {
    string secretCode;
    char meetingPoint;
    int time;

    cin >> secretCode >> meetingPoint >> time;

    tuple<string, char, int> missionInfo = make_tuple(secretCode, meetingPoint, time);

    tie(secretCode, meetingPoint, time) =  missionInfo;

    cout << "secret code : " << secretCode << '\n';
    cout << "meeting point : " << meetingPoint << '\n';
    cout << "time : " << time;
    return 0;
}