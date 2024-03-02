#include <iostream>

using namespace std;

class EatingCheeseInfo{
    public:
        int cheeseCount = 0;
        int cheeseIndex[50];
        int eatingWhen[50];

        EatingCheeseInfo(int cheeseIndex, int eatingWhen){
            this->cheeseIndex[this->cheeseCount++] = cheeseIndex;
            this->eatingWhen[this->cheeseCount++] = eatingWhen;
        }

        EatingCheeseInfo(){}

        void addCheeseInfo(int cheeseIndex, int eatingWhen){
            this->cheeseIndex[this->cheeseCount++] = cheeseIndex;
            this->eatingWhen[this->cheeseCount++] = eatingWhen;
        }
};

int N, M, D, S;
int getDiseaseWhen[51];
EatingCheeseInfo eatingCheeseInfo[51];
bool isDiseaseCheese[51];
bool isExist[51];

bool checkIsDiseaseCheese(int cheeseIndex){
    for(int i = 1; i <= N; i++){
        if(getDiseaseWhen[i] == 0){
            continue;
        }

        int cheeseCount = eatingCheeseInfo[i].cheeseCount;

        for(int j = 0; j <= cheeseCount-1; j++){
            cout << eatingCheeseInfo[i].eatingWhen[j] << ' ' << eatingCheeseInfo[i].cheeseIndex[j] << '\n';
            if(getDiseaseWhen[i] <= eatingCheeseInfo[i].eatingWhen[j]
            && eatingCheeseInfo[i].cheeseIndex[j] == cheeseIndex){
                return false;
            }
        }
    }
    
    return true;
}

int getCurrentDiseaseCount(int cheeseIndex){
    int diseaseCount = 0;
    for(int i = 1; i <= N; i++){
        int cheeseCount = eatingCheeseInfo[i].cheeseCount;
            for(int j = 0; j <= cheeseCount-1; j++){
            if(eatingCheeseInfo[i].cheeseIndex[j] == cheeseIndex){
                diseaseCount++;
            }
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
            eatingCheeseInfo[p].addCheeseInfo(m, t);
        }
        isExist[p] = true;
    }

    while(S--){
        int p, t;
        getDiseaseWhen[p] = t;
    }

    for(int i = 1; i <= 50; i++){
        isDiseaseCheese[i] = true;
    }

    for(int cheeseIndex = 1; cheeseIndex <= M; cheeseIndex++){
        if(isDiseaseCheese[cheeseIndex] == false){
            continue;
        }

        if(checkIsDiseaseCheese(cheeseIndex) == false){
            isDiseaseCheese[cheeseIndex] = false;
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