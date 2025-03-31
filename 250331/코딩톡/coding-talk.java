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
            setReadMembers();
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

        private void setReadMembers() {
            List<String> readMembers = new ArrayList<>();
            Chat target = chats.get(targetChatIndex);
            // 타겟 채팅이 다 읽혔으면 모든 사람
            if (target.unread == 0) {
                for (int i = 0; i <= memberIndex; i++) {
                    readMembers.add(String.valueOf((char) (i + 'A')));
                }

            } else {
                // 앞 채팅을 안읽은 사람 수  == 타겟 채팅을 안읽은 사람의 수이면 앞 채팅을 보낸 사람
                // - 앞 채팅 읽은 사람을 `a, b, c`라고 하자
                // - 타겟 채팅을 새로운 사람 `d`가 포함된 사람 3명이 읽었다고 하자
                // - 그렇다면 d도 앞 채팅을 읽었고 앞 채팅을 읽은 사람은 `a, b, c, d`가 되어야하는데 4명이 되어 모순이다
                // - 따라서 앞 채팅을 읽은 사람이 타겟 채팅을 읽었다
                // - 앞 채팅을 읽은 사람 중 앞 채팅을 보낸 사람만 특정할 수 있다
                for (int i = 0; i < targetChatIndex; i++) {
                    if (chats.get(i).unread != target.unread) {
                        continue;
                    }
                    readMembers.add(chats.get(i).sender);
                }
                // 타겟 채팅을 보낸 사람
                readMembers.add(chats.get(targetChatIndex).sender);
                // 타겟 채팅 이후에 채팅을 보낸 사람
                for (int i = targetChatIndex + 1; i <= chatsIndex; i++) {
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