#include <iostream>
#include <algorithm>

using namespace std;

class HeigthAndWeight{
    public:
        int height;
        int weight;
        int index;
    
    HeigthAndWeight(int height, int weight, int index){
        this->height = height;
        this->weight = weight;
        this->index = index;
    }

    HeigthAndWeight(){}
};

bool cmp(const HeigthAndWeight& a, const HeigthAndWeight& b){
    if(a.height == b.height){
        if(a.weight == b.weight){
            return a.index < b.index;
        }
        return b.weight < a.weight;
    }
    return b.height < a.height;
}

int main() {
    HeigthAndWeight heightAndWeight[1000];

    int N; cin >> N;
    for(int i = 0; i <= N-1; i++){
        int h, w;
        cin >> h >> w;
        heightAndWeight[i] = HeigthAndWeight(h, w, i+1);
    }

    sort(heightAndWeight, heightAndWeight  + N, cmp);

    for(int i = 0; i <= N-1; i++){
        cout << heightAndWeight[i].height << ' ';
        cout << heightAndWeight[i].weight << ' ';
        cout << heightAndWeight[i].index << '\n';
    }
    return 0;
}