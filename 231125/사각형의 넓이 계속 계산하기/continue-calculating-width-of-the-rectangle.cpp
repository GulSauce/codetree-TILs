#include <iostream>

using namespace std;

int main() {
    while(1){
        int width, height;
        char ch;
        cin >> width >> height >> ch;
        cout << width * height << '\n';
        if(ch == 'C')
            break;
    }
    return 0;
}