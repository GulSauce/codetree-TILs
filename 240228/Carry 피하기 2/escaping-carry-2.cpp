#include <iostream>

using namespace std;

int numbers[20];

bool isMatchedCondition(int n1, int n2, int n3){
    while(n1 || n2 || n3){
        if(10 <= n1 % 10 + n2 % 10 + n3 % 10){
            return false;
        }
        n1 /= 10;
        n2 /= 10;
        n3 /= 10;
    }
    return true;
}

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n; i++){
        cin >> numbers[i];
    }

    int result = -1;
    for(int i = 0; i <= n-1; i++){
        for(int j = i+1; j <= n-1; j++){
            for(int k = j+1; k <= n-1; k++){
                if(isMatchedCondition(numbers[i], numbers[j], numbers[k])){
                    result = max(result, numbers[i] + numbers[j] + numbers[k]);
                }
            }
        }
    }
    cout << result;
    return 0;
}