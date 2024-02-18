#include <iostream>

using namespace std;

int main() {
    pair<string, int> userInfo[2];
    userInfo[0] = {"codetree", 10};

    string userId; 
    int userLevel;
    cin >> userId >> userLevel;

    userInfo[1] = {userId, userLevel};

    string id[2];
    int level[2];

    for(int i = 0; i <= 1; i++){
        id[i] = userInfo[i].first;
        level[i] = userInfo[i].second;  
    }

    for(int i = 0; i <= 1; i++){
        cout << "user " << id[i] << ' ' << "lv " << level[i] << '\n'; 
    }
    
    return 0;
}