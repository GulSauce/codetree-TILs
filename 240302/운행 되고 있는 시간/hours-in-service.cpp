#include <iostream>

using namespace std;

class Developer{
    public:
        int start;
        int end;
        Developer(int start, int end){
            this->start = start;
            this->end = end;
        }
        Developer(){}
};

int workingTime[1001];
Developer developer[100];

int getCurrentContinueWorkingTime(int fireIndex){
    int start = developer[fireIndex].start;
    int end = developer[fireIndex].end;

    for(int i = start; i <= end-1; i++){
        workingTime[i]--;
    }

    int maxContinueWorkingTime = 0;
    int continueWokringTime = 0;
    for(int i = 1; i <= 1000; i++){
        if(workingTime[i] == 1){
            continueWokringTime++;
        }
        maxContinueWorkingTime = max(maxContinueWorkingTime, continueWokringTime);
    }

    for(int i = start; i <= end-1; i++){
        workingTime[i]++;
    }

    return maxContinueWorkingTime;
}

int main() {
    int N;
    cin >> N;
    
    for(int i = 0; i <= N-1; i++){
        int A, B;
        cin >> A >> B;
        developer[i] = Developer(A, B);
        for(int i = A; i <= B-1; i++){
            workingTime[i]++;
        }
    }

    int maxContinueWorkingTime = 0;

    for(int fireIndex = 0; fireIndex <= N-1; fireIndex++){
        int currentContinueWorkingTime = getCurrentContinueWorkingTime(fireIndex);
        maxContinueWorkingTime = max(currentContinueWorkingTime ,maxContinueWorkingTime);
    }
    
    cout << maxContinueWorkingTime;
    return 0;
}