#include <iostream>
#include <climits>

using namespace std;

int N;
string str;

int calcSitHere(){
    int sitHere = -1;

    int maxDist = 1;
    for(int i = 0; str[i] != '\0';){
        if(str[i] == '0'){
            i++;
            continue;
        }
        int left = i;
        i++;
        while(str[i] == '0' && str[i] != '\0'){
            i++;
        }
        if(str[i] == '\0'){
            break;
        }
        int right = i;

        int currentDist = right-left;
        if(maxDist + 1 <= currentDist){
            maxDist = currentDist / 2;
            sitHere = (right + left) / 2;
        }
    }

    if(str[0] == '0'){
        int right = 0;
        while(str[right] == '0' && str[right] != '\0'){
            right++;
        }
        if(str[right] != '\0'){
            int currentDist = right;
            if(maxDist + 1 <= currentDist){
                maxDist = currentDist;
                sitHere = 0;
            }
        }
    }

    if(str[N-1] == '0'){
        int left = N-1;
        while(0 <= left && str[left] == '0'){
            left--;
        }
        if(left != -1){
            int currentDist = N-1 - left;
            if(maxDist + 1 <= currentDist){
                maxDist = currentDist;
                sitHere = N-1;
            }
        }
    }
    
    return sitHere;
}

int main() {
    cin >> N;
    cin >> str;
    
    int sitHere = calcSitHere();

    str[sitHere] = '1';

    int minMaxDist = INT_MAX;
    for(int i = 0; str[i] != '\0';){
        if(str[i] == '0'){
            i++
            continue;
        }
        int left = i;
        i++;
        while(str[i] == '0' && str[i] != '\0'){
            i++;
        }
        if(str[i] == '\0'){
            break;
        }
        int right = i;

        int currentDist = (right - left);
        minMaxDist = min(minMaxDist, currentDist);
    }

    cout << minMaxDist;
    return 0;
}