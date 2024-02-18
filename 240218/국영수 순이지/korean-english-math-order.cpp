#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class Score{
    public:
        string name;
        int korean;
        int english;
        int math;
    
    Score(string name, int korean, int english, int math){
        this->name = name;
        this->korean = korean;
        this->english = english;
        this->math = math;
    }

    Score(){}
};

bool cmp(Score& a, Score& b){
    if(a.korean == b.korean){
        if(a.english == b.english){
            return a.math > b.math;
        }
        return a.english > b.english;
    }
    return a.korean > b.korean;
}

int main() {
    int n; cin >> n;

    Score score[10];

    for(int i = 0; i <= n-1; i++){
        string name;
        int korean;
        int english;
        int math;
        cin >> name >> korean >> english >> math;
        score[i] = Score(name, korean, english, math);
    }

    sort(score, score + n, cmp);

    for(int i = 0; i <= n-1; i++){
        cout << score[i].name << ' ';
        cout << score[i].korean << ' ';
        cout << score[i].english << ' ';
        cout << score[i].math << '\n';
    }
    return 0;
}