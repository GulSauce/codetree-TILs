#include <iostream>
#include <climits>

using namespace std;

int n, k;
int nubmers[101];

bool checkCanGo(int maxNumber){
    int cnt = 0;
    int nextIndex[101];

    for(int i = 1; i <= n; i++){
        if(maxNumber + 1 <= nubmers[i]){
            continue;
        }
        nextIndex[cnt++] = i; 
    }
    
    for(int i = 1; i <= cnt-1; i++){
        int dist = nextIndex[i] - nextIndex[i-1];
        if(k + 1 <= dist){
            return false;
        }
    }

    return true;
}

int main() {
    cin >> n >> k;
    for(int i = 1; i <= n; i++){
        cin >> nubmers[i];
    }
    
    for(int maxNumber = max(nubmers[1], nubmers[n]); maxNumber <= 100; maxNumber++){
        if(checkCanGo(maxNumber) == false){
            continue;
        }
        cout << maxNumber;
        return 0;
    }
    return -1;
}