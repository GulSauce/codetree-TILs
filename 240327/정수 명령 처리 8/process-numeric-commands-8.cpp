#include <iostream>
#include <list>

using namespace std;

int main() {
    list<int> list;

    int N;
    cin >> N;

    while(N--){
        string command;
        cin >> command;

        if(command == "push_front"){
            int A;
            cin >> A;
            list.push_front(A);
        }
        if(command == "push_back"){
            int A;
            cin >> A;
            list.push_back(A);
        }
        if(command == "pop_front"){
            cout << list.front() << '\n';
            list.pop_front();
        }

        if(command == "pop_back"){
            cout << list.back() << '\n';
            list.pop_back();
        }
        if(command == "size"){
            cout << list.size() << '\n';
        }
        if(command == "empty"){
            if(list.empty()){
                cout << 1 << '\n';
            }
            else{
                cout << 0 << '\n';
            }
        }
        if(command == "front"){
            cout << list.front() << '\n';
        }
        if(command == "back"){
            cout << list.back() << '\n';
        }
    }
    return 0;
}