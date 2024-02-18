#include <iostream>
#include <climits>

using namespace std;

class AgentInfo{
    public:
        char codeName;
        int score;

        AgentInfo(char codeName, int score){
            this->codeName = codeName;
            this->score = score;
        }

        AgentInfo(){}
};

int main() {
    AgentInfo agentInfo[5];

    for(int i = 0; i <= 4; i++){
        char codeName;
        int score;
        cin >> codeName >> score;

        agentInfo[i] = AgentInfo(codeName, score);
    }


    char leastScoreAgentCodeName = ' ';
    int leastScoreAgentScore = INT_MAX;

    for(int i = 0; i <= 4; i++){
        if(leastScoreAgentScore <= agentInfo[i].score){
            continue;
        }
        leastScoreAgentCodeName = agentInfo[i].codeName;
        leastScoreAgentScore = agentInfo[i].score;
    }

    cout << leastScoreAgentCodeName << ' ' << leastScoreAgentScore;
    return 0;
}