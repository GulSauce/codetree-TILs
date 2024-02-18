#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class Score{
    public:
        string name;
        int subject1;
        int subject2;
        int subject3;

    Score(string name, int subject1, int subject2, int subject3){
        this->name = name;
        this->subject1 = subject1;
        this->subject2 = subject2;
        this->subject3 = subject3;
    }

    Score(){}

    int getScoreSum(){
        return this->subject1 + this->subject2 + this->subject3;
    }
};

bool cmp(Score& a, Score& b){
    int aScoreSumValue = a.getScoreSum();
    int bScoreSumValue = b.getScoreSum();

    return aScoreSumValue < bScoreSumValue;
}

int main() {
    int n;
    cin >> n;

    Score score[10];
    for(int i = 0; i <= n-1; i++){
        string name;
        int subject1, subject2, subject3;
        cin >> name >> subject1 >> subject2 >> subject3;
        score[i] = Score(name, subject1, subject2, subject3);
    }

    sort(score, score + n, cmp);

    for(int i = 0; i <= n-1; i++){
        cout << score[i].name << ' ';
        cout << score[i].subject1 << ' ';
        cout << score[i].subject2 << ' ';
        cout << score[i].subject3 << '\n';
    }
    return 0;
}