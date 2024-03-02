#include <iostream>

using namespace std;

class Line{
    public:
        int start;
        int end;
        
        Line(int start, int end){
            this->start = start;
            this->end = end;
        }

        Line(){}
};

Line lines[10];
int pointCount[101];

bool checkIsOverlapped(int i, int j, int k){
    int s1 = lines[i].start;
    int e1 = lines[i].end;
    int s2 = lines[j].start;
    int e2 = lines[j].end;
    int s3 = lines[k].start;
    int e3 = lines[k].end;

    for(int i = s1; i <= e1; i++){
        pointCount[i]--;
    }
    for(int i = s2; i <= e2; i++){
        pointCount[i]--;
    }
    for(int i = s3; i <= e3; i++){
        pointCount[i]--;
    }

    bool isOverLapped = false;
    for(int i = 0; i <= 99; i++){
        if(2 <= pointCount[i]){
            isOverLapped = true;
        }
    }

    for(int i = s1; i <= e1; i++){
        pointCount[i]++;
    }
    for(int i = s2; i <= e2; i++){
        pointCount[i]++;
    }
    for(int i = s3; i <= e3; i++){
        pointCount[i]++;
    }

    return isOverLapped;
}

int main() {
    int n;
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        int a, b;
        cin >> a >> b;
        lines[i] = Line(a, b);
        for(int i = a; i <= b; i++){
            pointCount[i]++;
        }
    }

    int ans = 0;
    for(int i = 0; i <= n-1; i++){
        for(int j = i+1; j <= n-1; j++){
            for(int k = j+1; k <= n-1; k++){
                if(checkIsOverlapped(i, j, k) == false){
                    ans++;
                }
            }
        }
    }

    cout << ans;
    return 0;
}