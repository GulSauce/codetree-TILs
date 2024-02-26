#include <iostream>
#include <algorithm>

using namespace std;

bool isInfected[101];
int shakeHandCount[101];

class ShakeHandInfo{
    public:
        int time;
        int dev1;
        int dev2;
        ShakeHandInfo(int time, int dev1, int dev2){
            this->time = time;
            this->dev1 = dev1;
            this->dev2 = dev2;
        }

        ShakeHandInfo(){}
};

bool cmp(const ShakeHandInfo& a, const ShakeHandInfo& b){
     return a.time < b.time;
}

int main() {
    int N, K, P, T;
    cin >> N >> K >> P >> T;

    isInfected[P] = true;

    ShakeHandInfo shakeHandInfo[250];
    for(int i = 0; i <= T-1; i++){
        int t, x, y;
        cin >> t >> x >>  y;
        shakeHandInfo[i] = ShakeHandInfo(t, x, y);
    }

    sort(shakeHandInfo, shakeHandInfo+T, cmp);

    for(int i = 0; i <= T-1; i++){
        int dev1 = shakeHandInfo[i].dev1;
        int dev2 = shakeHandInfo[i].dev2;

        if(isInfected[dev1] && !isInfected[dev2]){
            if(shakeHandCount[dev1] < K){
                shakeHandCount[dev1]++;
                isInfected[dev2] = true;           
            }
            continue;
        }

        if(isInfected[dev2] && !isInfected[dev1]){
            if(shakeHandCount[dev2] < K){
                shakeHandCount[dev2]++;
                isInfected[dev1] = true;          
            }
            continue;
        }

        if(isInfected[dev2] && isInfected[dev1]){
            shakeHandCount[dev1]++;
            shakeHandCount[dev2]++;
        }
    }

    for(int i = 1; i <= N; i++){
        if(isInfected[i]){
            cout << 1;
        }else{
            cout << 0;
        }
    }

    return 0;
}