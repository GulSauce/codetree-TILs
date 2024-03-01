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
        int testCurrentPriceSum = currentPriceSum + wantedPrice[i];
        if(B + 1 <= testCurrentPriceSum){
            if(isCouponUsed){
                break;
            }
            isCouponUsed = true;
            testCurrentPriceSum -= wantedPrice[i] / 2;
        }
        if(testCurrentPriceSum <= B){
            currentPriceSum = testCurrentPriceSum;
            studentCount++;
        }
    }

    cout << studentCount;
    return 0;
}