#include <iostream>

using namespace std;

int N;
pair<int, int> matchHistory[100];

int main() {
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        int first, second;
        cin >> first >> second;

        matchHistory[i] = {first, second};
    }

    int maxWinCount = 0;

    int winCountFirstCase = 0;
    for(int i = 0; i<= N-1; i++){
        int first = matchHistory[i].first;
        int second = matchHistory[i].second;
        
        if(first == 1 && second == 2){
            winCountFirstCase++;
        }
        if(first == 2 && second == 3){
            winCountFirstCase++;
        }
        if(first == 3 && second == 1){
            winCountFirstCase++;
        }
    }
    maxWinCount = max(winCountFirstCase, maxWinCount);

    int winCountSecondCase = 0;

    for(int i = 0; i<= N-1; i++){
        int first = matchHistory[i].first;
        int second = matchHistory[i].second;
        
        if(first == 1 && second == 3){
            winCountSecondCase++;
        }
        if(first == 2 && second == 1){
            winCountSecondCase++;
        }
        if(first == 3 && second == 2){
            winCountSecondCase++;
        }
    }

    maxWinCount = max(winCountSecondCase, maxWinCount);

    cout << maxWinCount;
    return 0;
}