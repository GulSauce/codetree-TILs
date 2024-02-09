#include <iostream>
#include <string>

using namespace std;

int main() {
    int n;
    cin >> n;
    
    int lengthSum = 0;
    int aCnt = 0;
    while(n--){
        string str; 
        cin >> str;
        lengthSum += str.length();
        if(str[0] == 'a'){
            aCnt++;
        }
    }
    cout << lengthSum << ' ' << aCnt;
    return 0;
}