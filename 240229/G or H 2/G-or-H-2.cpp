#include <iostream>

using namespace std;

char alphabetArray[101];

bool isInRange(int start, int distance){
    return 0 <= start && start <= 100 
    && 0 <= start+distance && start+distance <= 100;
}

bool checkIsMatched(int start, int distance){
    int GCount = 0;
    int HCount = 0;
    for(int i = start; i <= start+distance; i++){
        if(alphabetArray[i] == 'G'){
            GCount++;
        }
        if(alphabetArray[i] == 'H'){
            HCount++;
        }
    }
    if(GCount * HCount == 0){
        return true;
    }
    if(GCount == HCount){
        return true;
    }
    return false;
}

int main() {
    int N;
    cin >> N;
    while(N--){
        int position;
        char alphabet;
        cin >> position >> alphabet;
        alphabetArray[position] = alphabet;
    }

    int maxDist = 0;
    for(int i = 0; i <= 100; i++){
        for(int distance = 0; distance <= 100; distance++){
            if(!isInRange(i, distance)){
                continue;
            }
            if(alphabetArray[i] == '\0' || alphabetArray[i+distance] == '\0'){
                continue;
            }
            
            bool isMatched = checkIsMatched(i, distance);
            if(isMatched){
                maxDist = max(maxDist, distance);
            }
        }
    }

    cout << maxDist;
    return 0;
}