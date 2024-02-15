#include <iostream>

using namespace std;

int day30[4] = {4, 6, 9, 11};

bool chekcIsDay30(int month){
    bool flag = false;
    for(int i = 0; i <= 3; i++){
        if(day30[i] == month){
            return true;
        }
    }
    return false;
}

bool checkIsDayInRange(int day, int s, int e){
    return s <= day && day <= e;
}

bool checkIsDayExist(int M, int D){
    bool flag = false;

    if(chekcIsDay30(M)){
        flag = checkIsDayInRange(D, 1, 30);
    }
    else if(M == 2){
        flag = checkIsDayInRange(D, 1, 28);
    }
    else if(M <= 12){
        flag = checkIsDayInRange(D, 1, 31);
    }
    return flag;
}

int main() {
    int M, D; cin >> M >> D;
    bool isDayExist = checkIsDayExist(M, D);
    if(isDayExist){
        cout << "Yes";
    }else{
        cout << "No";
    }
    return 0;
}