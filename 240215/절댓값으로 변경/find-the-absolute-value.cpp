#include <iostream>

using namespace std;

int N; 

void makeAbsNumber(int* notAbsNumbers){
    for(int i = 0; i <= N-1; i++){
        if(notAbsNumbers[i] < 0){
            notAbsNumbers[i] = -1*notAbsNumbers[i];
        }
    }
}

int main() {
    cin >> N;

    int notAbsNumbers[50];
    for(int i = 0; i <= N-1; i++){
        cin >> notAbsNumbers[i];
    }

    makeAbsNumber(notAbsNumbers);

    for(int i = 0; i <= N-1; i++){
        cout << notAbsNumbers[i] << ' ';
    }
    return 0;
}