#include <iostream>

using namespace std;

int day31[7] = {1, 3, 5, 7, 8, 10, 12};
int day30[4] = {4, 6, 9, 11};


bool chekcIsDay31(int month){
    bool flag = false;
    for(int i = 0; i <= 6; i++){
        if(day31[i] == month){
            return true;
        }
    }
    return false;
}

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
    if(chekcIsDay31(M)){
        flag = checkIsDayInRange(D, 1, 31);
    }
    if(chekcIsDay30(M)){
        flag = checkIsDayInRange(D, 1, 30);
    }
    if(M == 2){
        flag = checkIsDayInRange(D, 1, 28);
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