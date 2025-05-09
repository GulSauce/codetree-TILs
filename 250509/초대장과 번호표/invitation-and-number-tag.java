import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, G;
        ArrayList<GroupInfo> groupInfos = new ArrayList<>();
        groupInfos.add(new GroupInfo(-1, new ArrayList<>()));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        G = sc.nextInt();
        for (int i = 0; i < G; i++) {
            int groupMemberCount = sc.nextInt();
            ArrayList<Integer> groupMembers = new ArrayList<>();
            for (int j = 0; j < groupMemberCount; j++) {
                groupMembers.add(sc.nextInt());
            }
            groupInfos.add(new GroupInfo(groupMemberCount, groupMembers));
        }
        sc.close();

        new Solver(N, groupInfos).solve();
    }
}

class Solver {

    int personCount;
    ArrayList<GroupInfo> groupInfos;
    HashSet<Integer> answer = new HashSet<>();

    public Solver(
            int personCount,
            ArrayList<GroupInfo> groupInfos
    ) {
        this.personCount = personCount;
        this.groupInfos = groupInfos;
    }

    public void solve() {
        init();

        while (true) {
            ArrayList<GroupInfo> nextGroupInfo = new ArrayList<>();
            nextGroupInfo.add(new GroupInfo(-1, new ArrayList<>()));
            boolean notInvited = true;
            for (int i = 1; i < groupInfos.size(); i++) {
                HashSet<Integer> groupMembersChecker = groupInfos.get(i).groupMembersChecker;
                if (1 < groupMembersChecker.size()) {
                    continue;
                }
                notInvited = false;
                answer.addAll(groupMembersChecker);
                int newInvited = 0;
                for (Integer member : groupMembersChecker) {
                    newInvited = member;
                }
                for (int j = 1; j < groupInfos.size(); j++) {
                    HashSet<Integer> otherGroupMembersChecker = groupInfos.get(j).groupMembersChecker;
                    otherGroupMembersChecker.remove(newInvited);
                    if (!otherGroupMembersChecker.isEmpty()) {
                        nextGroupInfo.add(groupInfos.get(j));
                    }
                }
                groupMembersChecker.clear();
            }
            groupInfos = nextGroupInfo;
            if (notInvited) {
                break;
            }
        }
        System.out.println(answer.size());
    }


    private void init() {
        final int INVITED_MEMBER = 1;
        answer.add(INVITED_MEMBER);
        ArrayList<GroupInfo> nextGroupInfo = new ArrayList<>();
        nextGroupInfo.add(new GroupInfo(-1, new ArrayList<>()));
        for (int i = 1; i < groupInfos.size(); i++) {
            HashSet<Integer> groupMembersChecker = groupInfos.get(i).groupMembersChecker;
            groupMembersChecker.remove(INVITED_MEMBER);
            if (groupMembersChecker.isEmpty()) {
                continue;
            }
            nextGroupInfo.add(groupInfos.get(i));
        }
        groupInfos = nextGroupInfo;
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