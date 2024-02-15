#include <iostream>

using namespace std;

void swap(int* n, int* m){
    int temp = *n;
    *n = *m;
    *m = temp;
}

int main() {
    int n, m;
    cin >> n >> m;
    swap(&n, &m);
    cout << n << ' ' << m;
    return 0;
}