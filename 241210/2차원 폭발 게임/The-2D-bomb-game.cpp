#include <bits/stdc++.h>
using namespace std;

class Solver {
private:
    vector<vector<int>> numbers;
    int continueNumberToExplode;
    int rotateCount;
    const int MAX_INDEX;

public:
    Solver(vector<vector<int>>& nums, int M, int K)
    : numbers(nums), continueNumberToExplode(M), rotateCount(K), MAX_INDEX((int)nums.size()-1) {}

    void solve() {
        for (int i = 0; i <= rotateCount; i++) {
            explodeWithApplyingGravity();
            rotateClockWise90();
            applyGravity();
        }
        printResult();
    }

private:
    void printResult() {
        int result = 0;
        for (int y = 0; y <= MAX_INDEX; y++) {
            for (int x = 0; x <= MAX_INDEX; x++) {
                if (numbers[y][x] != 0) {
                    result++;
                }
            }
        }
        cout << result << "\n";
    }

    void explodeWithApplyingGravity() {
        explode();
        applyGravity();
    }

    void explode() {
        for (int x = 0; x <= MAX_INDEX; x++) {
            while (true) {
                auto numbersToChange = explodeColumnAt(x);
                if (isSame(numbersToChange, x)) {
                    break;
                }
                for (int i = 0; i <= MAX_INDEX; i++) {
                    numbers[i][x] = numbersToChange[i];
                }
            }
        }
    }

    bool isSame(const vector<int>& arr, int x) {
        for (int i = 0; i < (int)arr.size(); i++) {
            if (arr[i] != numbers[i][x]) {
                return false;
            }
        }
        return true;
    }

    vector<int> explodeColumnAt(int column) {
        vector<int> numbersToChange(MAX_INDEX+1, 0);
        int currentIndex = 0;
        while (currentIndex <= MAX_INDEX) {
            auto consecutiveNumbers = getConsecutiveNumberStartFrom(column, currentIndex);
            int consecutiveCount = (int)consecutiveNumbers.size();
            if (continueNumberToExplode <= consecutiveCount) {
                for (int i = currentIndex; i < currentIndex + consecutiveCount; i++) {
                    numbersToChange[i] = 0;
                }
            } else {
                for (int i = currentIndex; i < currentIndex + consecutiveCount; i++) {
                    numbersToChange[i] = consecutiveNumbers.front();
                    consecutiveNumbers.pop();
                }
            }
            currentIndex += consecutiveCount;
        }
        return numbersToChange;
    }

    queue<int> getConsecutiveNumberStartFrom(int column, int index) {
        queue<int> consecutiveNumbers;
        int targetNumber = numbers[index][column];
        for (int y = index; y <= MAX_INDEX; y++) {
            if (numbers[y][column] != targetNumber) {
                break;
            }
            consecutiveNumbers.push(numbers[y][column]);
        }
        return consecutiveNumbers;
    }

    void rotateClockWise90() {
        auto copiedNumbers = copyNumbers();
        for (int y = 0; y <= MAX_INDEX; y++) {
            for (int x = 0; x <= MAX_INDEX; x++) {
                numbers[y][x] = copiedNumbers[MAX_INDEX - x][y];
            }
        }
    }

    vector<vector<int>> copyNumbers() {
        vector<vector<int>> copiedNumbers(MAX_INDEX+1, vector<int>(MAX_INDEX+1, 0));
        for (int y = 0; y <= MAX_INDEX; y++) {
            for (int x = 0; x <= MAX_INDEX; x++) {
                copiedNumbers[y][x] = numbers[y][x];
            }
        }
        return copiedNumbers;
    }

    void applyGravity() {
        for (int x = 0; x <= MAX_INDEX; x++) {
            queue<int> numbersQueue;
            for (int y = MAX_INDEX; y >= 0; y--) {
                if (numbers[y][x] == 0) continue;
                numbersQueue.push(numbers[y][x]);
            }
            for (int y = MAX_INDEX; y >= 0; y--) {
                numbers[y][x] = 0;
            }
            int currentIndex = MAX_INDEX + 1;
            while (!numbersQueue.empty()) {
                currentIndex--;
                numbers[currentIndex][x] = numbersQueue.front();
                numbersQueue.pop();
            }
        }
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M, K;
    cin >> N >> M >> K;
    vector<vector<int>> numbers(N, vector<int>(N, 0));
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            cin >> numbers[y][x];
        }
    }

    Solver solver(numbers, M, K);
    solver.solve();

    return 0;
}