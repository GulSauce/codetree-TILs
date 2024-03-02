#include <iostream>

using namespace std;

class WorkByTemperInfo{
    public:
        int lowerTemper;
        int higherTemper;

        WorkByTemperInfo(int lowerTemper, int higherTemper){
            this->lowerTemper = lowerTemper;
            this->higherTemper = higherTemper;
        }

        WorkByTemperInfo(){}
};


int N, C, G, H;

WorkByTemperInfo WorkByTemperInfoArray[1000];

int getCurrentWorkSum(int currentTemper){
    int currentWorkSum = 0;
    for(int i = 0; i <= N-1; i++){
        int Ta = WorkByTemperInfoArray[i].lowerTemper;
        int Tb = WorkByTemperInfoArray[i].higherTemper;

        if(currentTemper <= Ta - 1){
            currentWorkSum += C;
        }
        if(Ta <= currentTemper && currentTemper <= Tb){
            currentWorkSum += G;
        }
        if(Tb + 1 <= currentTemper){
            currentWorkSum += H;
        }
    }
    return currentWorkSum;
}

int main() {
    cin >> N >> C >> G >> H;
    for(int i = 0; i <= N-1; i++){
        int Ta, Tb;
        cin >> Ta >> Tb;
        WorkByTemperInfoArray[i] = WorkByTemperInfo(Ta, Tb);
    }

    int maxWorkSum = 0;
    for(int currentTemper = -1; currentTemper <= 1100; currentTemper++){
        int currentWorkSum = getCurrentWorkSum(currentTemper);
        maxWorkSum = max(maxWorkSum, currentWorkSum);
    }

    cout << maxWorkSum;
    return 0;
}