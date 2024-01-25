#include <iostream>

using namespace std;

int main() {
    int personCount = 3;
    int clinic[4] = {};

    while(personCount--){
        char isSymptom;
        int temper;
        cin >> isSymptom >> temper;
        if(isSymptom == 'Y' && 37 <= temper){
            clinic[0]++;
        }
        if(isSymptom == 'N' && 37 <= temper){
            clinic[1]++;
        }
        if(isSymptom == 'Y' && temper < 37){
            clinic[2]++;
        }
        if(isSymptom == 'N' && temper < 37){
            clinic[3]++;
        }
    }

    for(int i = 0; i <=3 ; i++){
        cout << clinic[i] << ' ';
    }
    
    if(2 <= clinic[0]){
        cout << 'E';
    }

    return 0;
}