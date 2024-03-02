#include <iostream>
#include <climits>

using namespace std;

char alphabetAtIndex[1001];

int getD1(int k){
    int dist = INT_MAX;
    for(int sIndex = 1; sIndex <= 1000; sIndex++){
        if(alphabetAtIndex[sIndex] == 'S'){
            dist = min(dist, abs(k - sIndex));
        }
    }
    return dist;
}

int getD2(int k){
    int dist = INT_MAX;
    for(int nIndex = 1; nIndex <= 1000; nIndex++){
        if(alphabetAtIndex[nIndex] == 'N'){
            dist = min(dist, abs(k - nIndex));
        }
    }
    return dist;
}

int main() {
    int T, a, b;
    cin >> T >> a >> b;

    while(T--){
        char c;
        int x;
        cin >> c >> x;
        alphabetAtIndex[x] = c;
    }

    int specialPosCount = 0;
    for(int k = a; k <= b; k++){
        int d1 = getD1(k);
        int d2 = getD2(k);
        if(d1 <= d2){
            specialPosCount++;
        }
    }

    cout << specialPosCount;
    return 0;
}