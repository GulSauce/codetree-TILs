#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class NameHeightAndWeight{
    public:
        string name;
        int height;
        int weight;
        NameHeightAndWeight(string name, int height, int weight){
            this->name = name;
            this->height = height;
            this->weight = weight;
        }
        NameHeightAndWeight(){};
};

bool cmp(const NameHeightAndWeight& a, const NameHeightAndWeight& b){
    return a.height < b.height;
}

int main() {
    int n; cin >> n;
    NameHeightAndWeight nameHeightAndWeight[10];
    for(int i = 0; i <= n-1; i++){
        string name;
        int height;
        int weight;
        cin >> name >> height >> weight;
        nameHeightAndWeight[i] = NameHeightAndWeight(name, height, weight);
    }

    sort(nameHeightAndWeight, nameHeightAndWeight+n, cmp);

    for(int i = 0; i <= n-1; i++){
        cout << nameHeightAndWeight[i].name << ' '
        << nameHeightAndWeight[i].height << ' '
        << nameHeightAndWeight[i].weight << '\n';
    }
    return 0;
}