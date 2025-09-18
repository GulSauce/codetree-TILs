import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int N, G;
        HashMap<Integer, Group> groupIdToGroupMapper = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= G; i++) {
            st = new StringTokenizer(br.readLine());
            int peopleCount = Integer.parseInt(st.nextToken());
            HashSet<Integer> peopleIdSet = new HashSet<>();
            for (int j = 0; j < peopleCount; j++) {
                int peopleId = Integer.parseInt(st.nextToken());
                peopleIdSet.add(peopleId);
            }
            groupIdToGroupMapper.put(i, new Group(i, peopleIdSet));
        }

        new Solver(N, groupIdToGroupMapper).solve();
    }
}

class Solver {
    int maxPeopleId;
    HashMap<Integer, Group> groupIdToGroupMapper = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> personIdToGroupIdsMapper = new HashMap<>();

    public Solver(
            int N,
            HashMap<Integer, Group> groupIdToGroupMapper
    ) {
        this.maxPeopleId = N;
        this.groupIdToGroupMapper = groupIdToGroupMapper;
    }

    public void solve() {
        init();
        int answer = 0;

        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(1);
        while (!bfsQueue.isEmpty()) {
            int personId = bfsQueue.poll();
            answer++;

            ArrayList<Integer> groupIds = personIdToGroupIdsMapper.get(personId);
            for (Integer groupId : groupIds) {
                Group group = groupIdToGroupMapper.get(groupId);
                group.personIdSet.remove(personId);

                if (group.personIdSet.size() == 1) {
                    int lastPersonId = group.personIdSet.iterator().next();
                    bfsQueue.add(lastPersonId);
                }
            }
        }
        
        System.out.println(answer);
    }

    private void init() {
        for (Group group : groupIdToGroupMapper.values()) {
            for (Integer peopleId : group.personIdSet) {
                ArrayList<Integer> groupIds = personIdToGroupIdsMapper.getOrDefault(peopleId, new ArrayList<>());
                groupIds.add(group.id);
                personIdToGroupIdsMapper.put(peopleId, groupIds);
            }
        }
    }
}

class Group {
    int id;
    HashSet<Integer> personIdSet = new HashSet<>();

    public Group(
            int id,
            HashSet<Integer> personIdSet
    ) {
        this.id = id;
        this.personIdSet = personIdSet;
    }
}