#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    double tempNum;
    double avgSum = 0;
    for(int i = 0; i < n; i++){
        cin >> tempNum;
        avgSum += tempNum;
    }
    cout << fixed;
    cout.precision(1);
    double res = avgSum / n;
    cout << res << '\n';
    if(4.0 <= res){
        cout << "Perfect";
    }
    else if(3.0 <= res){
        cout << "Good";
    }
    else{
        cout << "Poor";
    }
    return 0;
}