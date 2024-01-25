#include <iostream>

using namespace std;

int main() {
    int scoreByTenUnitCount[11] = {};

    while(1){
        int score; cin >> score;
        if(score == 0){
            break;
        }

        int tenUnitScore = score / 10;
        scoreByTenUnitCount[tenUnitScore]++;
    }

    for(int i = 10; i >= 1; i--){
        cout << i*10 << " - " << scoreByTenUnitCount[i] << '\n';
    }

    return 0;
}