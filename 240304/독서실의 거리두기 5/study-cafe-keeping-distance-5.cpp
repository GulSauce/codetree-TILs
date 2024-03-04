#include <iostream>
#include <climits>

using namespace std;
    
int N;
string str;

int calcCurrentMinDist(int index){
    str[index] = '1';

    int beforePersonIndex = 0;
    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == '1'){
            beforePersonIndex = i;
            break;
        }
    }

    int minDist = INT_MAX;
    for(int i = beforePersonIndex + 1; str[i] != '\0'; i++){
        if(str[i] == '0'){
            continue;
        }
        int currentDist = i - beforePersonIndex;
        minDist = min(minDist, currentDist);
        beforePersonIndex = i;
    }

    str[index] = '0';
    return minDist;
}

int main() {
    cin >> N;
    cin >> str;

    int minMaxDist = 0;
    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == '1'){
            continue;
        }
        int currentMinDist = calcCurrentMinDist(i);
        minMaxDist = max(minMaxDist, currentMinDist);
    }

    cout << minMaxDist;
    return 0;
}