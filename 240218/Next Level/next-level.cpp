#include <iostream>

using namespace std;

class UserInfo{
    public:
        string id;
        int level;
    
    UserInfo(string id = "", int level = 0){
        this->id = id;
        this->level = level;
    }
};

int main() {
    UserInfo firstUserInfo = UserInfo(); 
    firstUserInfo.id = "codetree";
    firstUserInfo.level = 10;
    
    string id;
    int level;

    cin >> id >> level;

    UserInfo secondUserInfo = UserInfo(id, level);

    cout << "user " << firstUserInfo.id << ' ' << "lv " << firstUserInfo.level << '\n'; 
    cout << "user " << secondUserInfo.id << ' ' << "lv " << secondUserInfo.level << '\n'; 
    
    return 0;
}