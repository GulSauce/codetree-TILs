#include <iostream>

using namespace std;

int main() {
    int n ; cin >> n;
    int carValue[1000];
    for(int i = 0; i <= n-1; i++){
        cin >> carValue[i];
    }

    int maxBenefit = 0;
    for(int curYear = 0; curYear <= n-2; curYear++){
        for(int nextYear = curYear;  nextYear <= n-1; nextYear++){
            int curBenefit = carValue[nextYear] - carValue[curYear];
            if(maxBenefit < curBenefit){
                maxBenefit = curBenefit;
            }
        }
    }
    cout << maxBenefit;
    return 0;
}