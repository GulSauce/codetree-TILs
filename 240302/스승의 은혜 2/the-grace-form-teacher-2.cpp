#include <iostream>
#include <algorithm>

using namespace std;

int wantedPrice[1000];

int main() {
    int N, B;
    cin >> N >> B;

    for(int i = 0; i <= N-1; i++){
        cin >> wantedPrice[i];
    }

    sort(wantedPrice, wantedPrice + N);

    int studentCount = 0;
    int currentPriceSum = 0;
    bool isCouponUsed = false;

    for(int i = 0; i <= N-1; i++){
        currentPriceSum += wantedPrice[i];
        if(B + 1 <= currentPriceSum){
            if(isCouponUsed){
                break;
            }
            isCouponUsed = true;
            currentPriceSum -= wantedPrice[i] / 2;
        }
        if(currentPriceSum <= B){
            studentCount++;
        }
    }

    cout << studentCount;
    return 0;
}