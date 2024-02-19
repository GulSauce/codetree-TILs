#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

class PrivateInfo{
    public:
        string name;
        int height;
        double weight;

        PrivateInfo(string name, int height, double weight){
            this->name = name;
            this->height = height;
            this->weight = weight;
        }

        PrivateInfo(){}
};

bool cmpByName(const PrivateInfo& a, const PrivateInfo& b){
    return a.name < b.name;
}

bool cmpByHeigth(const PrivateInfo& a, const PrivateInfo& b){
    return b.height < a.height;
}

PrivateInfo privateInfo[5];

void printByName(){
    sort(privateInfo, privateInfo + 5, cmpByName);
    cout << "name" << '\n';
    for(int i = 0; i <= 4; i++){
        cout << privateInfo[i].name << ' ';
        cout << privateInfo[i].height << ' ';
        cout << privateInfo[i].weight << '\n';
    }
}

void printByHeight(){
    sort(privateInfo, privateInfo + 5, cmpByHeigth);
    cout << "height" << '\n';
    for(int i = 0; i <= 4; i++){
        cout << privateInfo[i].name << ' ';
        cout << privateInfo[i].height << ' ';
        cout << privateInfo[i].weight << '\n';
    }
}

int main() {
    for(int i = 0; i <= 4; i++){
        string name;
        int height;
        double weight;
        cin >> name >> height >> weight;
        privateInfo[i] = PrivateInfo(name, height, weight);
    }

    cout << fixed;
    cout.precision(1);

    printByName();

    cout << '\n';
    
    printByHeight();

    return 0;
}