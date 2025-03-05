import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int memberIndex;
        int targetChatIndex;

        int chatsIndex;
        List<Chat> chats;

        List<String> readMembers = new ArrayList<>();
        List<String> nonReadMembers = new ArrayList<>();

        public Solver(
            int N,
            int M,
            int p,
            List<Chat> chats
        ) {
            this.memberIndex = N - 1;
            this.chatsIndex = M - 1;
            this.targetChatIndex = p - 1;
            this.chats = chats;
        }

        public void solve() {
            getReadMembers();
            getNonReadMembers();
            printAnswer();
        }

        private void printAnswer() {
            for (String member : nonReadMembers) {
                System.out.print(member + " ");
            }
        }

        private void getNonReadMembers() {
            boolean[] alphabetExist = new boolean[26];
            for (String member : readMembers) {
                alphabetExist[member.charAt(0) - 'A'] = true;
            }
            for (int i = 0; i <= memberIndex; i++) {
                if (alphabetExist[i]) {
                    continue;
                }
                nonReadMembers.add(String.valueOf((char) (i + 'A')));
            }
        }

        private void getReadMembers() {
            List<String> readMembers = new ArrayList<>();
            Chat target = chats.get(targetChatIndex);
            if (target.unread == 0) {
                for (int i = 0; i <= memberIndex; i++) {
                    readMembers.add(String.valueOf((char) (i + 'A')));
                }
            } else {
                for (int i = 0; i < targetChatIndex; i++) {
                    if (chats.get(i).unread != target.unread) {
                        continue;
                    }
                    readMembers.add(chats.get(i).sender);
                }
                for (int i = targetChatIndex; i <= chatsIndex; i++) {
                    readMembers.add(chats.get(i).sender);
                }
            }
            this.readMembers = readMembers;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int p = sc.nextInt();
        List<Chat> chats = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            chats.add(new Chat(sc.next(), sc.nextInt()));
        }
        new Solver(N, M, p, chats).solve();
    }

    private static class Chat {

        String sender;
        int unread;

        public Chat(
            String c,
            int u
        ) {
            this.sender = c;
            this.unread = u;
        }
    }
}