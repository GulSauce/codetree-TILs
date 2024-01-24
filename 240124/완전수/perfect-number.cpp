#include <iostream>

using namespace std;

int main() {
    int start, end; cin >> start >> end;
    int perfectNumberCnt = 0;
    for(int curNumber = start; curNumber <= end; curNumber++){
        int childSum = 0;
        for(int child = 1; child < curNumber; child++){
            if(curNumber % child == 0){
                childSum += child;
            }
        }
        if(childSum == curNumber){
             perfectNumberCnt++;
        }
    }
    cout << perfectNumberCnt;
    return 0;
}