#include <iostream>

using namespace std;

class EatingCheeseInfo{
    public:
        bool isEatingCheese[51] = {};
        int eatingWhen[51] = {};

        EatingCheeseInfo(int cheeseIndex, int eatingWhen){
            this->isEatingCheese[cheeseIndex] = true;
            this->eatingWhen[cheeseIndex] = eatingWhen;
        }

        EatingCheeseInfo(){}

        void upsertCheeseInfo(int cheeseIndex, int eatingWhen){
            if(this->isEatingCheese[cheeseIndex]){
                int oldEatingWhen = this->eatingWhen[cheeseIndex];
                this->eatingWhen[cheeseIndex] = min(oldEatingWhen, eatingWhen);
                return;
            }

            this->isEatingCheese[cheeseIndex] = true;
            this->eatingWhen[cheeseIndex] = eatingWhen;
        }
};

bool isExist[51];
bool isDiseaseCheese[51];

int N, M, D, S;
int getDiseaseWhen[51];

EatingCheeseInfo eatingCheeseInfo[51];

bool checkIsEatenByPatient(int cheeseIndex){
    for(int i = 1; i <= N; i++){
        if(getDiseaseWhen[i] == 0){
            continue;
        }

        bool isCheeseEaten = false;
        if(eatingCheeseInfo[i].isEatingCheese[cheeseIndex]){
            isCheeseEaten = true;
        }
        if(isCheeseEaten == false){
            return false;
        }
    }

    return true;
}

bool checkIsFasterThenGetDisease(int cheeseIndex){
    for(int i = 1; i <= N; i++){
        if(getDiseaseWhen[i] == 0){
            continue;
        }

        if(getDiseaseWhen[i] <= eatingCheeseInfo[i].eatingWhen[cheeseIndex]){
            return true;
        }
    }

    return false;
}

bool checkIsDiseaseCheese(int cheeseIndex){
    if(checkIsEatenByPatient(cheeseIndex) == false){
        return false;
    }

    if(checkIsFasterThenGetDisease(cheeseIndex)){
        return false;
    }

    return true;
}
    
int getCurrentDiseaseCount(int cheeseIndex){
    int diseaseCount = 0;
    for(int i = 1; i <= N; i++){
        if(eatingCheeseInfo[i].isEatingCheese[cheeseIndex]){
            diseaseCount++;
        }
    }
    return diseaseCount;
}

int main() {
    cin >> N >> M >> D >> S;
    
    while(D--){
        int p, m, t;
        cin >> p >> m >> t;
        if(isExist[p] == false){
            eatingCheeseInfo[p] = EatingCheeseInfo(m, t);
        }else{
            eatingCheeseInfo[p].upsertCheeseInfo(m, t);
        }
        isExist[p] = true;
    }

    while(S--){
        int p, t;
        cin >> p >> t;
        getDiseaseWhen[p] = t;
    }

    for(int cheeseIndex = 1; cheeseIndex <= M; cheeseIndex++){        
        if(checkIsDiseaseCheese(cheeseIndex)){
            isDiseaseCheese[cheeseIndex] = true;
        }
    }

    int diseaseCount = 0;
    for(int cheeseIndex = 1; cheeseIndex <= M; cheeseIndex++){
        if(isDiseaseCheese[cheeseIndex] == false){
            continue;
        }

        int currentDiseaseCount = getCurrentDiseaseCount(cheeseIndex);
        diseaseCount = max(diseaseCount, currentDiseaseCount);
    }

    cout << diseaseCount;
    return 0;
}