#include <iostream>

using namespace std;

int board[100];

int getSectionSum(int start, int end){
    int sum = 0;
    for(int i = start; i <= end; i++){
        sum += board[i];
    }
    return sum;
}

bool checkIsMatched(int avg, int start, int end){
    for(int i = start; i <= end; i++){
        if(board[i] == avg){
            return true;
        }
    }
    return false;
}

int main() {
    int N;
    cin >> N;
    
    for(int i = 0; i <= N-1; i++){
        cin >> board[i];
    }
    
    int ans = 0;

    for(int start = 0; start <= N-1; start++){
        for(int end = start; end <= N-1; end++){
            int sectionSum = getSectionSum(start, end);

            if(1 <= sectionSum % (end-start+1)){
                continue;
            }
            
            int avg = sectionSum/(end-start+1);
            bool isMatcehd = checkIsMatched(avg, start, end);
            if(isMatcehd){
                ans++;
            }
        }
    }

    cout << ans;
    return 0;
}