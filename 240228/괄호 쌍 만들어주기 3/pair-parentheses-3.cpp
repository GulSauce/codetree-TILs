#include <iostream>
#include <string>

using namespace std;

int main() {
    string str;
    cin >> str;

    int ans = 0;
    int lastIndex = str.length();
    for(int first = 0; first <= lastIndex-2; first++){
        for(int second = first+1; second <= lastIndex-1; second++){
            if(str[first] == '(' && str[second] == ')'){
                ans++;    
            }
        }
    }

    cout << ans;
    
    return 0;
}