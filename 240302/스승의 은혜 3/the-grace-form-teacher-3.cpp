#include <iostream>
#include <algorithm>

using namespace std;

class StudentPresentInfo{
    public:
        int price;
        int deliveryFee;

        StudentPresentInfo(int price, int deliveryFee){
            this->price = price;
            this->deliveryFee = deliveryFee;
        }

        StudentPresentInfo(){}
};

int N, B;
StudentPresentInfo studentPresentInfo[1000];

int getStudentNum(int salePresentIndex){
    int priceAndDeliveryFeeSumArray[1000];
    for(int i=0; i <= N-1; i++){
        int price = studentPresentInfo[i].price;
        int deliveryFee = studentPresentInfo[i].deliveryFee;
        if(i == salePresentIndex){
            price /= 2;
        }

        priceAndDeliveryFeeSumArray[i] = price + deliveryFee;
    }
    sort(priceAndDeliveryFeeSumArray, priceAndDeliveryFeeSumArray + N);

    int priceAndDeliveryFeeSum = 0;
    int count = 0;
    for(int i=0; i <= N-1; i++){
        int testSum = priceAndDeliveryFeeSum + priceAndDeliveryFeeSumArray[i];
        if(B < testSum){
            break;
        }
        count++;
        priceAndDeliveryFeeSum = testSum;
    }
    return count;
}

int main() {
    cin >> N >> B;

    for(int i = 0; i <= N-1; i++){
        int P, S;
        cin >> P >> S;
        studentPresentInfo[i] = StudentPresentInfo(P, S);
    }

    int maxStudentNum = 0;
    for(int i = 0; i <= N-1; i++){
        int studentNum = getStudentNum(i);
        maxStudentNum = max(maxStudentNum, studentNum);
    }
    cout << maxStudentNum;
    return 0;
}