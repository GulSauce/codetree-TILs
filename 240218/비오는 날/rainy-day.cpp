#include <iostream>
#include <string>

using namespace std;

class WeatherPredictData{
    public:
        string date;
        string day;
        string weather;

        WeatherPredictData(string date, string day, string weather){
            this->date = date;
            this->day = day;
            this->weather = weather;
        }

        WeatherPredictData(){};
};

int main() {
    int n; cin >> n;
    WeatherPredictData weatherPredictData[100];

    for(int i = 0 ; i <= n-1; i++){
        string date, day, weather;
        cin >> date >> day >> weather;
        weatherPredictData[i] = WeatherPredictData(date, day, weather);
    }

    WeatherPredictData nearRainPredictData("9999-99-99", "Sun", "Rain");

    for(int i = 0 ; i <= n-1; i++){
        if(weatherPredictData[i].weather == "Rain" &&
        weatherPredictData[i].date <= nearRainPredictData.date){
            nearRainPredictData = weatherPredictData[i];
        }
    }

    cout << nearRainPredictData.date << ' ' 
    << nearRainPredictData.day << ' ' 
    << nearRainPredictData.weather;
    return 0;
}