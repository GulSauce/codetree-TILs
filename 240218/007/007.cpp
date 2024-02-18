#include <iostream>
#include <string>
#include <tuple>

using namespace std;

class MissionInfo {
    public:
        string secretCode;
        char meetingPoint;
        int time;
    
        MissionInfo(string secretCode, char meetingPoint, int time){
            this->secretCode = secretCode;
            this->meetingPoint = meetingPoint;
            this->time = time;
        }
};

int main() {
    string secretCode;
    char meetingPoint;
    int time;

    cin >> secretCode >> meetingPoint >> time;

    MissionInfo missionInfo = MissionInfo(secretCode, meetingPoint, time);

    cout << "secret code : " << missionInfo.secretCode << '\n';
    cout << "meeting point : " << missionInfo.meetingPoint << '\n';
    cout << "time : " << missionInfo.time;
    return 0;
}