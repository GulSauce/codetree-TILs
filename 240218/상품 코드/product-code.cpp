#include <iostream>
#include <string>

using namespace std;

class GoodsInfo{
    public:
        string goodsName;
        int goodsCode;

        GoodsInfo(string goodsName, int goodsCode){
            this->goodsName = goodsName;
            this->goodsCode = goodsCode;
        }

        GoodsInfo(){}
};

int main() {
    GoodsInfo goodsInfo[2];

    goodsInfo[0] = GoodsInfo("codetree", 50);

    string goodsName;
    int goodsCode;

    cin >> goodsName >> goodsCode;

    goodsInfo[1].goodsName = goodsName;
    goodsInfo[1].goodsCode = goodsCode;

    for(int i = 0; i <= 1; i++){
        cout << "product " << goodsInfo[i].goodsCode << " is " << goodsInfo[i].goodsName << '\n';        
    }

    return 0;
}