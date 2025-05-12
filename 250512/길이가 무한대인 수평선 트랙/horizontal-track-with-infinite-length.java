import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, T;
        ArrayList<PersonRunInfo> personRunInfos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();
        for (int i = 0; i < N; i++) {
            personRunInfos.add(new PersonRunInfo(i, sc.nextInt(), sc.nextInt()));
        }

        new Solver(T, personRunInfos).solve();
    }
}

class Solver {
    int endTime;
    ArrayList<PersonRunInfo> personRunInfos;
    TreeSet<PersonRunInfo> personRunInfoTreeSet;
    TreeSet<CollideEvent> collideEventTreeSet = new TreeSet<>();

    public Solver(
            int T,
            ArrayList<PersonRunInfo> personRunInfos
    ) {
        this.endTime = T;
        this.personRunInfos = personRunInfos;
    }

    public void solve() {
        init();
        while (!collideEventTreeSet.isEmpty()) {
            CollideEvent fastest = collideEventTreeSet.first();
            collideEventTreeSet.remove(fastest);
            if (endTime < fastest.meetTime) {
                break;
            }

            PersonRunInfo groupLeader = personRunInfos.get(fastest.groupLeader);
            if (!personRunInfoTreeSet.contains(groupLeader)) {
                continue;
            }

            personRunInfoTreeSet.remove(personRunInfos.get(fastest.joinPerson));

            PersonRunInfo nearest = personRunInfoTreeSet.lower(groupLeader);
            if (nearest == null) {
                continue;
            }
            checkAndAddEvent(nearest, groupLeader);
        }
        System.out.println(personRunInfoTreeSet.size());
    }

    private void init() {
        personRunInfoTreeSet = new TreeSet<>(personRunInfos);
        for (int i = 0; i < personRunInfos.size() - 1; i++) {
            PersonRunInfo left = personRunInfos.get(i);
            PersonRunInfo right = personRunInfos.get(i + 1);
            checkAndAddEvent(left, right);
        }
    }

    private void checkAndAddEvent(PersonRunInfo left, PersonRunInfo right) {
        if (left.velocity < right.velocity) {
            return;
        }
        collideEventTreeSet.add(new CollideEvent(left, right));
    }
}

class CollideEvent implements Comparable<CollideEvent> {
    double meetTime;
    int joinPerson;
    int groupLeader;

    public CollideEvent(
            PersonRunInfo left,
            PersonRunInfo right
    ) {
        this.meetTime = (double) (right.start - left.start) / (left.velocity - right.velocity);
        this.joinPerson = left.number;
        this.groupLeader = right.number;
    }

    @Override
    public int compareTo(CollideEvent other) {
        if (meetTime == other.meetTime) {
            return groupLeader - other.groupLeader;
        }
        if (meetTime < other.meetTime) {
            return -1;
        } else {
            return 1;
        }
    }
}

class PersonRunInfo implements Comparable<PersonRunInfo> {
    int number;
    int start;
    int velocity;

    public PersonRunInfo(
            int number,
            int start,
            int velocity
    ) {
        this.number = number;
        this.start = start;
        this.velocity = velocity;
    }

    @Override
    public int compareTo(PersonRunInfo other) {
        return start - other.start;
    }
}