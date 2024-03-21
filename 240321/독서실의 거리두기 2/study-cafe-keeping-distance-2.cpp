#include <iostream>
#include <climits>

using namespace std;

int N;
string librarySeatInfo;

int calcWhereSit(){
    int sitHere = 0;
    int maxDist = 0;
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
        if(maxDist + 1 <= dist){
            maxDist = dist;
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
             if(maxDist + 1 <= dist){
                maxDist = dist;
                sitHere = dist/2;
            }
        }
    }

    if(librarySeatInfo[N-1] == '0'){
        int dist = 1;
        while(0 <= N-1-dist && librarySeatInfo[N-1-dist] == '0'){
            dist++;
        }
        if(0 <= N-1-dist){
            if(maxDist + 1 <= dist){
                maxDist = dist;
                sitHere = (N-1 + N-1-dist)/2;
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