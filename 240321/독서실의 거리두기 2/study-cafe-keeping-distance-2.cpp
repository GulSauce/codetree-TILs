#include <iostream>
#include <climits>

using namespace std;

int N;
string librarySeatInfo;

int calcWhereSit(){
    int sitHere = 0;
    int maxDistAfterSit = 0;
    for(int i = 0; librarySeatInfo[i] != '\0';){
        if(librarySeatInfo[i] == '0'){
            i++;
            continue;
        }

        int dist = 1;
        while(librarySeatInfo[i+dist] == '0' && librarySeatInfo[i+dist] != '\0'){
            dist++;
        }

        if(librarySeatInfo[i+dist] == '\0'){
            break;
        }
        int currentDistAfterSit = dist/2;
        if(maxDistAfterSit + 1 <= currentDistAfterSit){
            maxDistAfterSit = currentDistAfterSit;
            sitHere = (i + i + dist)/2;
        }
        i += dist;
    }

    if(librarySeatInfo[0] == '0'){
        int dist = 1;
        while(librarySeatInfo[dist] == '0' && librarySeatInfo[dist] != '\0'){
            dist++;
        }
        if(librarySeatInfo[dist] != '\0'){
             if(maxDistAfterSit + 1 <= dist){
                maxDistAfterSit = dist;
                sitHere = 0;
            }
        }
    }

    if(librarySeatInfo[N-1] == '0'){
        int dist = 1;
        while(0 <= N-1-dist && librarySeatInfo[N-1-dist] == '0'){
            dist++;
        }
        if(0 <= N-1-dist){
            if(maxDistAfterSit + 1 <= dist){
                maxDistAfterSit = dist;
                sitHere = N-1;
            }
        }
    }

    return sitHere;
}

int main() {
    cin >> N;

    cin >> librarySeatInfo;

    int sitHere = calcWhereSit();

    librarySeatInfo[sitHere] = '1';
    int minDist = INT_MAX;
    for(int i = 0; librarySeatInfo[i] != '\0';){
        if(librarySeatInfo[i] == '0'){
            i++;
            continue;
        }

        int dist = 1;
        while(librarySeatInfo[i+dist] == '0' && librarySeatInfo[i+dist] != '\0'){
            dist++;
        }
        if(librarySeatInfo[i+dist] == '\0'){
            break;
        }
        if(dist + 1 <= minDist){
            minDist = dist;
        }
        i += dist;
    }

    cout << minDist;
    return 0;
}