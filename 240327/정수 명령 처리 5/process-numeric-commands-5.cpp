#include <iostream>
#include <vector>

using namespace std;

int main() {
    vector<int> numbers;

    int N;
    cin >> N;

    while(N--){
        string cmd;
        cin >> cmd;
        if(cmd == "push_back"){
            int number;
            cin >> number;
            numbers.push_back(number);
        }
        if(cmd == "pop_back"){
            numbers.pop_back();
        }
        if(cmd == "get"){
            int indexPlus1;
            cin >> indexPlus1;
            cout << numbers[indexPlus1-1] << '\n';
        }
        if(cmd == "size"){
            cout << numbers.size() << '\n';
        }
    }
    return 0;
}