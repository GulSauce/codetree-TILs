#include <iostream>

using namespace std;

int N;

int iceHeight[100];

int getIceCount(int seaHeight){
    for(int i = 0; i <= N-1; i++){
        iceHeight[i] -= seaHeight;
    }

    bool isNewIce = true;
    int iceCount = 0;
    for(int i = 0; i <= N-1; i++){
        if(iceHeight[i] <= 0){
            isNewIce = true;
        }
        else{
            if(isNewIce){
                iceCount++;
                isNewIce = false;
            }
        }
    }

    for(int i = 0; i <= N-1; i++){
        iceHeight[i] += seaHeight;
    }

    return iceCount;
}

int main() {
    cin >> N;
    
    for(int i = 0; i <= N-1; i++){
        cin >> iceHeight[i];
    }

    int maxIceCount = 0;

    for(int seaHeight = 0; seaHeight <= 1000; seaHeight++){
        maxIceCount = max(maxIceCount, getIceCount(seaHeight));
    }

    cout << maxIceCount << '\n';
    return 0;
}