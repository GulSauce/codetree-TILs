#include <iostream>

using namespace std;

bool isPersonExist[100];

int main() {
    int n, m;
    cin >> n >> m;

    for(int i = 0; i <= n-1; i++){
        cin >> isPersonExist[i];
    }

    int wifiCount = 0;
    for(int i = 0; i <= n-1;){
        if(isPersonExist[i] == false){
            i++;
            continue;
        }
        int nextNeedWifiIndex = i + 2 * m + 1;
        wifiCount++;
        if(n <= nextNeedWifiIndex){
            break;
        } 
        i = nextNeedWifiIndex;
    }

    cout << wifiCount;
    return 0;
}