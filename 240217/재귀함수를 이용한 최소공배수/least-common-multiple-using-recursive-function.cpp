#include <iostream>
#include <algorithm>

using namespace std;

int numbers[10];

int calcLcm(int a, int b){
    int startValue = min(a, b);
    int currentLcm = a*b;
    for(int i = startValue; i <= a*b; i++){
        if(i % a == 0 && i % b == 0){
            currentLcm = i;
            break;
        }
    }

    return currentLcm;
}

int getLcm(int index){
    if(index == 0){
        return numbers[index];
    }

    int currentLcm = calcLcm(numbers[index], getLcm(index-1));
    return currentLcm;
}

int main() {
    int n;
    cin >> n;
    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    int lcm = getLcm(n-1);

    cout << lcm;
    return 0;
}