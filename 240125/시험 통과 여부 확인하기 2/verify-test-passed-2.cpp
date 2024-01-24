#include <iostream>

using namespace std;

bool isPassed() {
    int scoreSum = 0;
    int score;
    for(int i = 0; i < 4; i++){
        cin >> score;
        scoreSum += score;
    }
    double avg = scoreSum/4;
    if(60 <= avg){
        cout << "pass\n";
        return true;
    }

    cout << "fail\n";
    return false;
}

int main() {
    int n = 0; cin >> n;
    int cnt = 0;
    while(n--){
       if(isPassed()){
            cnt++;
       }
    }
    cout << cnt;
    return 0;
}