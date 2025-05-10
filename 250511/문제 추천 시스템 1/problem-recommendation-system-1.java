import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Problem> problems = new ArrayList<>();
        int M;
        int x, P, L;
        ArrayList<Command> commands = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            problems.add(new Problem(sc.nextInt(), sc.nextInt()));
        }
        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            CommandName commandName = CommandName.valueOf(sc.next());
            if (commandName == CommandName.rc) {
                x = sc.nextInt();
                commands.add(new Command(commandName, x));
            } else {
                P = sc.nextInt();
                L = sc.nextInt();
                commands.add(new Command(commandName, P, L));
            }
        }
        sc.close();

        new Solver(problems, commands).solve();
    }
}

class Solver {
    TreeSet<Problem> problemTreeSet;
    ArrayList<Problem> problems;
    ArrayList<Command> commands;


    public Solver(
            ArrayList<Problem> problems,
            ArrayList<Command> commands
    ) {
        this.problems = problems;
        this.commands = commands;
    }

    public void solve() {
        problemTreeSet = new TreeSet<>(problems);
        for (Command command : commands) {
            switch (command.commandName) {
                case rc:
                    if (command.value1 == 1) {
                        System.out.println(problemTreeSet.last().number);
                        break;
                    }
                    if (command.value1 == -1) {
                        System.out.println(problemTreeSet.first().number);
                        break;
                    }
                case ad:
                    problemTreeSet.add(new Problem(command.value1, command.value2));
                    break;
                case sv:
                    problemTreeSet.remove(new Problem(command.value1, command.value2));
                    break;
            }
        }
    }
}

class Problem implements Comparable<Problem> {

    @Override
    public int compareTo(Problem other) {
        if (difficulty == other.difficulty) {
            return number - other.number;
        }
        return difficulty - other.difficulty;
    }

    int number;
    int difficulty;

    public Problem(
            int P,
            int L
    ) {
        this.number = P;
        this.difficulty = L;
    }
}

class Command {
    CommandName commandName;
    int value1;
    int value2;

    public Command(
            CommandName commandName,
            int value1
    ) {
        this.commandName = commandName;
        this.value1 = value1;
    }

    public Command(
            CommandName commandName,
            int value1,
            int value2
    ) {
        this.commandName = commandName;
        this.value1 = value1;
        this.value2 = value2;
    }
}

enum CommandName {
    rc,
    ad,
    sv
}