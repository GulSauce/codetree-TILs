#include <iostream>
#include <climits>

using namespace std;

string str;

int getMinDist(){
    int minDist = INT_MAX;

    for(int i = 0; str[i] != '\0'; i++){
        for(int j = i + 1; str[j] !='\0'; j++){
            if(str[i] == '0' || str[j] == '0'){
                continue;
            }

            if(j-i < minDist){
                minDist = j - i;
            }
            break;
        }
    }

    return minDist;
}

int main() {
    int N;
    cin >> N;

    cin >> str;

    int maxDist = 0;
    pair<int, int> maxDistPosition;

    for(int i = 0; str[i] != '\0'; i++){
        for(int j = i + 1; str[j] !='\0'; j++){
            if(str[i] == '0' || str[j] == '0'){
                continue;
            }

            if(maxDist < j - i){
                maxDist = j - i;
                maxDistPosition = {i, j};
            }
            break;
        }
    }

    int newIndex = (maxDistPosition.first + maxDistPosition.second) / 2;

    str[newIndex] = '1';

    int ans = getMinDist();
    cout << ans;
    return 0;
}