#include <iostream>

using namespace std;

int panaltyCountForEachStudent[101];

int main() {
    int N, M, K;
    cin >> N >> M >> K;
    int ans = -1;
    while(M--){
        int panaltyNubmer;
        cin >> panaltyNubmer;
        if(K <= ++panaltyCountForEachStudent[panaltyNubmer]){
            ans = panaltyNubmer;
            break;
        }
    }

    cout << ans;
    return 0;
}