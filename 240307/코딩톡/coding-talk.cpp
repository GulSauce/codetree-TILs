#include <iostream>

using namespace std;

class MessageInfo{
    public:
        char who;
        int nonReadCount;

        MessageInfo(char who, int nonReadCount){
            this->who = who;
            this->nonReadCount = nonReadCount;
        }

        MessageInfo(){}
};

MessageInfo messageInfoArray[101];

int main() {
    int n, m, p;
    cin >> n >> m >> p;

    bool whoRead[26] = {};

    for(int i = 1; i <= m; i++){
        char c;
        int u;
        cin >> c >> u;
        messageInfoArray[i] = MessageInfo(c, u);
    }

    if(messageInfoArray[p].nonReadCount == 0){
        cout << ' ';
        return 0;
    }

    int startIndex = 0;
    for(int i = 1; i <= p; i++){
        if(messageInfoArray[i].nonReadCount == messageInfoArray[p].nonReadCount){
            startIndex = i;
            break;
        }
    }

    for(int i = startIndex; i <= m; i++){
        int alphabetIndex =  messageInfoArray[i].who - 'A';
        whoRead[alphabetIndex] = true;
    }

    for(int i = 0; i <= n-1; i++){
        if(whoRead[i]){
            continue;
        }
        cout << (char)(i + 'A') << ' ';
    }
    return 0;
}