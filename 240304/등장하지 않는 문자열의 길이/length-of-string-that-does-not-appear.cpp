#include <iostream>

using namespace std;

int N;
string str;


bool checkIsDuplicated(int length){
    for(int i = 0; i <= N-length; i++){
        int sameCount = 0;
        string subStr = str.substr(0, length);
        for(int j = length; j <= N-length; j++){
            if(subStr == str.substr(j, length)){
                sameCount++;
            }
        }
        if(1 <= sameCount){
            return true;
        }
    }

    return false;
}

int main() {
    cin >> N;
    cin >> str;

    for(int length = 1; length <= N; length++){
        if(checkIsDuplicated(length)){
            continue;
        }
        cout << length << '\n';
        return 0;
    }
    return 0;
}