#include <iostream>

using namespace std;

bool isLeapYear(int Y){
    if(Y % 4 == 0 && Y % 100 == 0 && Y % 400 == 0){
        return true;
    }
    if(Y % 4 == 0 && Y % 100 == 0){
        return false;
    }
    if(Y % 4 == 0){
        return true;
    }
    return false;
}

bool isDayInRange(int D, int s, int e){
    return s <= D && D <= e;
}

bool checkIsDayExist(int Y, int M, int D){
    int endOfFeb = 28;
    if(isLeapYear(Y)){
        endOfFeb = 29;
    }
    if(M == 4 || M == 6 || M == 9 || M == 11){
        return isDayInRange(D, 1, 30);
    }
    else if(M == 2){
        return isDayInRange(D, 1, endOfFeb);
    }
    else{
        return isDayInRange(D, 1, 31);
    }
}

void printMySeason(int M){
    if(3 <= M && M <= 5){
        cout << "Spring";
    }
    else if(6 <= M && M <= 8){
        cout << "Summer";
    }
    else if(9 <= M && M <= 11){
        cout << "Fall";
    }
    else{
        cout << "Winter";
    }
}

int main() {
    int Y, M, D;
    cin >> Y >> M >> D;
    if(checkIsDayExist(Y, M, D) == false){
        cout << -1;
        return 0;
    }

    printMySeason(M);
    return 0;
}