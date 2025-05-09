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
    HashSet<Integer> remainGroups = new HashSet<>();

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
            boolean notInvited = true;
            HashSet<Integer> nextRemainGroups = new HashSet<>();
            for (Integer i : remainGroups) {
                HashSet<Integer> groupMembersChecker = groupInfos.get(i).groupMembersChecker;
                if (1 < groupMembersChecker.size()) {
                    nextRemainGroups.add(i);
                    continue;
                }
                if (groupMembersChecker.isEmpty()) {
                    continue;
                }

                notInvited = false;
                answer.addAll(groupMembersChecker);
                int newInvited = groupMembersChecker.iterator().next();

                for (Integer j : remainGroups) {
                    HashSet<Integer> otherGroupMembersChecker = groupInfos.get(j).groupMembersChecker;
                    otherGroupMembersChecker.remove(newInvited);
                    if (!otherGroupMembersChecker.isEmpty()) {
                        continue;
                    }
                    nextRemainGroups.add(j);
                }
                groupMembersChecker.clear();
            }
            remainGroups = nextRemainGroups;
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
            remainGroups.add(i);
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