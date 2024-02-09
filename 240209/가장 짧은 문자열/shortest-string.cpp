#include <iostream>

using namespace std;

int findMax(int len1, int len2, int len3){
    if(len1 >= len2 && len1 >= len3){
        return len1;
    }
    if(len2 >= len1 && len2 >= len3){
        return len2;
    }
    if(len3 >= len1 && len3 >= len1){
        return len3;
    }
}


int findMin(int len1, int len2, int len3){
    if(len1 <= len2 && len1 <= len3){
        return len1;
    }
    if(len2 <= len1 && len2 <= len3){
        return len2;
    }
    if(len3 <= len1 && len3 <= len1){
        return len3;
    }
}

int main() {
    string str1, str2, str3;
    cin >> str1 >> str2 >> str3;
    int len1 = str1.length();
    int len2 = str2.length();
    int len3 = str3.length();
    int maxLen = findMax(len1, len2, len3);
    int minLen = findMin(len1, len2, len3);

    cout << maxLen - minLen;
    return 0;
}