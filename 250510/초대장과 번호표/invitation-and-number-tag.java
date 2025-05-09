import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N, G;
        HashMap<Integer, GroupInfo> groupNumberGroupInfoMapper = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        G = sc.nextInt();
        for (int i = 0; i < G; i++) {
            int groupMemberCount = sc.nextInt();
            ArrayList<Integer> groupMembers = new ArrayList<>();
            for (int j = 0; j < groupMemberCount; j++) {
                groupMembers.add(sc.nextInt());
            }
            groupNumberGroupInfoMapper.put(i + 1, new GroupInfo(groupMemberCount, groupMembers));
        }
        sc.close();

        new Solver(N, groupNumberGroupInfoMapper).solve();
    }
}

class Solver {

    int personCount;
    HashMap<Integer, ArrayList<Integer>> personIndexGroupNumbersMapper = new HashMap<>();
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    HashMap<Integer, GroupInfo> groupNumberGroupInfoMapper;
    boolean[] visited;

    public Solver(
            int personCount,
            HashMap<Integer, GroupInfo> groupNumberGroupInfoMapper
    ) {
        this.personCount = personCount;
        this.visited = new boolean[personCount + 1];
        this.groupNumberGroupInfoMapper = groupNumberGroupInfoMapper;
    }

    public void solve() {
        init();

        Queue<Integer> personNodes = new LinkedList<>();
        personNodes.add(1);
        visited[1] = true;
        while (!personNodes.isEmpty()) {
            int cur = personNodes.poll();
            ArrayList<Integer> groupNumbers = personIndexGroupNumbersMapper.get(cur);
            for (Integer groupNumber : groupNumbers) {
                GroupInfo groupInfo = groupNumberGroupInfoMapper.get(groupNumber);
                groupInfo.groupMembersChecker.remove(cur);
            }
            for (Integer nextNode : graph.get(cur)) {
                ArrayList<Integer> nextGroupNumbers = personIndexGroupNumbersMapper.get(nextNode);
                for (Integer groupNumber : nextGroupNumbers) {
                    GroupInfo groupInfo = groupNumberGroupInfoMapper.get(groupNumber);
                    if (1 < groupInfo.groupMembersChecker.size()) {
                        continue;
                    }
                    if (visited[nextNode]) {
                        continue;
                    }
                    personNodes.add(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= personCount; i++) {
            if (visited[i]) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private void init() {
        for (int i = 0; i <= personCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (GroupInfo groupInfo : groupNumberGroupInfoMapper.values()) {
            for (Integer groupMember : groupInfo.groupMembersChecker) {
                for (Integer nextNode : groupInfo.groupMembersChecker) {
                    if (nextNode.equals(groupMember)) {
                        continue;
                    }
                    graph.get(groupMember).add(nextNode);
                }
            }
        }

        for (Map.Entry<Integer, GroupInfo> groupInfoEntry : groupNumberGroupInfoMapper.entrySet()) {
            GroupInfo groupInfo = groupInfoEntry.getValue();
            for (Integer groupMember : groupInfo.groupMembersChecker) {
                if (personIndexGroupNumbersMapper.containsKey(groupMember)) {
                    ArrayList<Integer> existGroupInfo = personIndexGroupNumbersMapper.get(groupMember);
                    existGroupInfo.add(groupInfoEntry.getKey());
                    continue;
                }
                ArrayList<Integer> newGroupInfo = new ArrayList<>();
                newGroupInfo.add(groupInfoEntry.getKey());
                personIndexGroupNumbersMapper.put(groupMember, newGroupInfo);
            }
        }
    }
}

class GroupInfo {
    int groupMemberCount;
    HashSet<Integer> groupMembersChecker;

    public GroupInfo(
            int groupMemberCount,
            ArrayList<Integer> groupMembers
    ) {
        this.groupMemberCount = groupMemberCount;
        this.groupMembersChecker = new HashSet<>(groupMembers);
    }
}