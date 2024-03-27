#include <iostream>
#include <list>

using namespace std;

int main() {
    list<char> toasts;

    int n, m;
    cin >> n >> m;

    string toastString;
    cin >> toastString;

    for(int i = 0; toastString[i] != '\0'; i++){
        toasts.push_back(toastString[i]);
    }

    list<char>::iterator it = toasts.end();

    while(m--){
        char command;
        cin >> command;
        if(command == 'L'){
            if(it == toasts.begin()){
                continue;
            }
            it--;
        }
        if(command == 'R'){
            if(it == toasts.end()){
                continue;
            }
            it++;
        }
        if(command == 'D'){
             if(it == toasts.end()){
                continue;
            }
            it = toasts.erase(it);
        }
        if(command == 'P'){
            char toast;
            cin >> toast;
            toasts.insert(it, toast);
        }
    }

    for(it = toasts.begin(); it != toasts.end(); it++){
        cout << *it;
    }
    return 0;
}