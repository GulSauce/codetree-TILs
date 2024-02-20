#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class PrivateInfo{
    public:
        string name;
        int height;
        int weight;
    
        PrivateInfo(string name, int height, int weight){
            this->name = name;
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
    PrivateInfo privateInfo[10];

    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        string name;
        int height, weight;
        cin >> name >> height >> weight;

        privateInfo[i] = PrivateInfo(name, height, weight);
    }

    sort(privateInfo, privateInfo+n, cmp);

    for(int i = 0; i <= n-1; i++){
        cout << privateInfo[i].name << ' ' << privateInfo[i].height << ' ' << privateInfo[i].weight << '\n';
    }
    return 0;
}