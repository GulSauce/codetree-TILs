#include <iostream>

using namespace std;

int n, m;
int nums[100];

int getSpecialSum(){
    int specialSum = 0;

    specialSum += nums[m-1];
    while(m != 1){
        if(m%2 == 0){
            m/= 2;
        }
        else{
            m-=1;
        }
        specialSum += nums[m-1];
    }
    return specialSum;
}

int main() {
    cin >> n >> m;

    for(int i = 0; i <= n-1; i++){
        cin >> nums[i];
    }

    int specialSum = getSpecialSum();

    cout << specialSum;
    return 0;
}