#include <climits>
#include <iostream>

using namespace std;

int N;
int roomWorkers[1003];

int getDistance(int startIndex, int totalPerson){
    int currentIndex = startIndex;
    int remainPerson = totalPerson;

    int distanceSum = 0;

    while(remainPerson){
        remainPerson -= roomWorkers[currentIndex++];
        distanceSum += remainPerson;
        currentIndex %= N;
    }

    return distanceSum;
}

int main() {
    cin >> N;

    int totalPerson = 0;
    for(int i = 0; i <= N-1; i++){
        cin >> roomWorkers[i];
        totalPerson += roomWorkers[i];
    }

    int minDistance = INT_MAX;
    for(int startIndex = 0; startIndex <= N-1; startIndex++){
        int currentDistance = getDistance(startIndex, totalPerson);
        minDistance = min(minDistance, currentDistance);
    }

    cout << minDistance;
    return 0;
}