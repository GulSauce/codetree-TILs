#include <iostream>
#include <algorithm>

using namespace std;


bool cmp(const pair<int, int>& a, const pair<int, int>& b){
    if(a.second == b.second){
        return a.first < b.first;
    }
    return a.second < b.second;
}

int main() {
    int N;
    cin >> N;

    pair<int, int> distInfo[1000];

    for(int i = 0; i <= N-1; i++){
        int x, y;
        cin >> x >> y;

        distInfo[i].first = i+1;
        distInfo[i].second = abs(x)+abs(y);
    }

    sort(distInfo, distInfo + N, cmp);

    for(int i = 0; i <= N-1; i++){
        cout << distInfo[i].first << '\n';
    }
    return 0;
}