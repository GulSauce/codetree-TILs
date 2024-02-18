#include <iostream>
#include <string>

using namespace std;

class BombInfo{
    public:
        string releaseCode;
        char lineColor;
        int second;

        BombInfo(string releaseCode, char lineColor, int second){
            this->releaseCode = releaseCode;
            this->lineColor = lineColor;
            this->second = second;
        }

        BombInfo(){}
};

int main() {
    string releaseCode;
    char lineColor;
    int second;

    cin >> releaseCode >> lineColor >> second;
    
    BombInfo bombInfo = BombInfo(releaseCode, lineColor, second);

    cout << "code : " << bombInfo.releaseCode << '\n';
    cout << "color : " << bombInfo.lineColor << '\n';
    cout << "second : " << bombInfo.second;
    return 0;
}