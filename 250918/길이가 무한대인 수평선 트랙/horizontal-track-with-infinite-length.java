import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        int N, T;
        ArrayList<PersonRunInfo> personRunInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int velocity = Integer.parseInt(st.nextToken());
            personRunInfos.add(new PersonRunInfo(i, startX, velocity));
        }

        br.close();

        new Solver(T, personRunInfos).solve();
    }
}

class Solver {

    int endTime;
    ArrayList<PersonRunInfo> personRunInfos;
    PriorityQueue<Event> eventTreeSet = new PriorityQueue<>();
    TreeMap<Integer, PersonRunInfo> personRunInfoTreeMap = new TreeMap<>();

    public Solver(
            int T,
            ArrayList<PersonRunInfo> personRunInfos
    ) {
        this.endTime = T;
        this.personRunInfos = personRunInfos;
    }

    public void solve() {
        init();

        while (!eventTreeSet.isEmpty()) {
            Event event = eventTreeSet.poll();
            if (endTime < event.collideTime) {
                break;
            }
            int learId = event.leaderId;
            int joinerId = event.joinerId;
            personRunInfoTreeMap.remove(joinerId);

            Integer nearPersonId;
            if (learId < joinerId) {
                nearPersonId = personRunInfoTreeMap.higherKey(joinerId);
            } else {
                nearPersonId = personRunInfoTreeMap.lowerKey(joinerId);
            }
            if (nearPersonId != null) {
                addCollideEvent(personRunInfos.get(learId), personRunInfos.get(nearPersonId));
            }
        }

        System.out.println(personRunInfoTreeMap.size());
    }

    private void init() {
        for (int i = 0; i < personRunInfos.size() - 1; i++) {
            PersonRunInfo a = personRunInfos.get(i);
            PersonRunInfo b = personRunInfos.get(i + 1);
            addCollideEvent(a, b);
        }
        for (PersonRunInfo personRunInfo : personRunInfos) {
            personRunInfoTreeMap.put(personRunInfo.id, personRunInfo);
        }
    }

    private void addCollideEvent(PersonRunInfo a, PersonRunInfo b) {
        int xDiff = Math.abs(a.startX - b.startX);
        int vDiff = Math.abs(a.velocity - b.velocity);
        if (vDiff == 0) {
            return;
        }
        int collideTime = xDiff / vDiff;

        if (a.velocity < b.velocity && b.startX < a.startX) {
            eventTreeSet.add(new Event(a.id, b.id, collideTime));
        } else if (b.velocity < a.velocity && a.startX < b.startX) {
            eventTreeSet.add(new Event(b.id, a.id, collideTime));
        }
    }
}

class Event implements Comparable<Event> {
    int leaderId;
    int joinerId;
    int collideTime;

    public Event(int leaderId, int joinerId, int collideTime) {
        this.leaderId = leaderId;
        this.joinerId = joinerId;
        this.collideTime = collideTime;
    }

    @Override
    public int compareTo(Event other) {
        return this.collideTime - other.collideTime;
    }

}

class PersonRunInfo {
    int id;
    int startX;
    int velocity;

    public PersonRunInfo(int id, int startX, int velocity) {
        this.id = id;
        this.startX = startX;
        this.velocity = velocity;
    }

}