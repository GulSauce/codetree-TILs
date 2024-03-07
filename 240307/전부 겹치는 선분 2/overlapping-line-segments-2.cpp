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

Line lines[100];

bool checkIsNointersect(int i, int j){
    if(lines[i].right + 1 <= lines[j].left
    || lines[j].right + 1 <= lines[i].left){
        return true;
    }

    return false;
}

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        int x, y;
        cin >> x >> y;
        lines[i] = Line(x, y);
    }

    for(int nonUse = 0; nonUse <= n-1; nonUse++){
        for(int i = 0; i <= n-1; i++){
            bool isAllIntersect = true;
            for(int j = i + 1; j <= n-1; j++){
                if(checkIsNointersect(i, j)){
                    isAllIntersect = false;
                    break;
                }
            }
            if(isAllIntersect){
                cout << "Yes";
                return 0;
            }
        }
    }
    cout << "No";
    return 0;
}