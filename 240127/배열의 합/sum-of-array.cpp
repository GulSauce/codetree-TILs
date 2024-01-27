#include <iostream>

using namespace std;

int main() {
    for(int y = 0; y <= 3; y++){
        int sum = 0;
        
        for(int x = 0; x <= 3; x++){
            int num; cin >> num;
            sum += num;
        }
        
        cout << sum << '\n';
    }

    return 0;
}