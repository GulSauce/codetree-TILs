#include <iostream>
#include <algorithm>

using namespace std;

class PrivateInfo{
    public:
        int number;
        int height;
        int weight;

        PrivateInfo(int number, int height, int weight){
            this->number = number;
            this->height = height;
            this->weight = weight;
        }

        PrivateInfo(){}
};

bool cmp(const PrivateInfo& a, const PrivateInfo& b){
    if(a.height == b.height){
        return b.weight < a.weight;
    }
    return a.height < b.height;
}

int main() {
    PrivateInfo privateInfo[1000];

    int N;
    cin >> N;

    for(int i = 0; i <= N-1; i++){
        int h, w;
        cin >> h >> w;
        privateInfo[i] = PrivateInfo(i+1, h, w);
    }

    sort(privateInfo, privateInfo + N, cmp);

    for(int i = 0; i <= N-1; i++){
        cout << privateInfo[i].height << ' ';
        cout << privateInfo[i].weight << ' ';
        cout << privateInfo[i].number << '\n';
    }

    return 0;
}