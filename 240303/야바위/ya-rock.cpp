#include <iostream>

using namespace std;

class GameHistory{
    public:
        int first;
        int second;
        int openIndex;

        GameHistory(int first, int second, int openIndex){
            this->first = first;
            this->second = second;
            this->openIndex = openIndex;
        }

        GameHistory(){};
};

int N;

bool cup[4];

GameHistory gameHistory[100];

void init(){
    for(int i = 0; i <= 3; i++){
        cup[i] = false;
    }
}

int calcPoint(int startIndex){
    init();

    cup[startIndex] = true;

    int point = 0;

    for(int i = 0; i <= N-1; i++){
        int first = gameHistory[i].first;
        int second = gameHistory[i].second;
        int openIndex = gameHistory[i].openIndex;

        swap(cup[first], cup[second]);

        if(cup[openIndex]){
            point++;
        }
    }

    return point;
}

int main() {
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        int a, b, c;
        cin >> a >> b >> c;
        
        gameHistory[i] = GameHistory(a, b, c);
    }

    int maxPoint = 0;

    for(int i = 1; i <= 3; i++){
        int currentPoint = calcPoint(i);
        maxPoint = max(currentPoint, maxPoint);
    }
    
    cout << maxPoint;
    return 0;
}