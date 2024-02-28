#include <iostream>
#include <string>

using namespace std;

int main() {
    string str;
    cin >> str;

    int ans = 0;
    int lastIndex = str.length() - 1;
    for(int first = 0; first <= lastIndex; first++){
        for(int second = first+1; second <= lastIndex; second++){
            if(str[first] == '(' && str[second] == ')'){
                ans++;    
            }
        }
    }

    cout << ans;
    
    return 0;
}