#include <iostream>

using namespace std;

int main() {
    int N;
    cin >> N;

    string str;
    cin >> str;
    
    int cnt = 0;
    int personPosition[1000] = {};

    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == '1'){
            personPosition[cnt++] = i;
        }
    }

    int maxDist = 0;
    for(int i = 1; i <= cnt - 1; i++){
        maxDist = max(personPosition[i] - personPosition[i-1], maxDist);
    }

    int result = maxDist/2;
    if(str[0] == '0'){
        result = max(result, personPosition[0] - 0);
    }
    if(str[N-1] == '0'){
        result = max(result, N-1 - personPosition[cnt-1]);
    }

    cout << result;
    return 0;
}