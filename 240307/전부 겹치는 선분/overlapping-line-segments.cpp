#include <iostream>

using namespace std;

class Line{
    public:
        int left;
        int right;
        Line(int left, int right){
            this->left = left;
            this->right = right;
        }
        Line(){}
};

Line line[100];

bool checkIsNonintersect(int i, int j){
    if(line[j].right + 1 <= line[i].left
    || line[i].right + 1 <= line[j].left){
        return true;
    }
    return false;
}

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        int x1, x2;
        cin >> x1 >> x2;
        line[i] = Line(x1, x2);
    }

    for(int i = 0; i <= n-1; i++){
        for(int j = i + 1; j <= n-1; j++){
            if(checkIsNonintersect(i, j)){
                cout << "No";
                return 0;
            }
        }
    }

    cout << "Yes";
    return 0;
}