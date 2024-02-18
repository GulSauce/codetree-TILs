#include <iostream>

using namespace std;

class PrivateInfo{
    public:
        string name;
        string addr;
        string city;

    PrivateInfo(string name, string addr, string city){
        this->name = name;
        this->addr = addr;
        this->city = city;        
    }

    PrivateInfo(){}
};

int main() {
    int n;
    cin >> n;
    
    PrivateInfo privateInfoArray[10];

    for(int i = 0; i <= n-1; i++){
        string name;
        string addr;
        string city;
        cin >> name >> addr >> city;
        privateInfoArray[i] = PrivateInfo(name, addr, city);
    }

    PrivateInfo leastDictPriavteInfo = privateInfoArray[0];
    for(int i = 1; i <= n-1; i++){
        if(leastDictPriavteInfo.name <= privateInfoArray[i].name){
            leastDictPriavteInfo = privateInfoArray[i];
        }
    }

    cout << "name " << leastDictPriavteInfo.name << '\n';
    cout << "addr " << leastDictPriavteInfo.addr << '\n';
    cout << "city " << leastDictPriavteInfo.city;
    return 0;
}