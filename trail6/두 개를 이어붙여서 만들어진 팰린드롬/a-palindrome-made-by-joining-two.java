import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<String> wordList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            wordList.add(st.nextToken());
        }

        new Solver(wordList).solve();
    }
}

class Solver {

    Node root = new Node();
    List<String> wordList;
    HashMap<String, int[]> palinRadiusHashMap = new HashMap<>();

    public Solver(List<String> wordList) {
        this.wordList = wordList;
    }

    public void solve() {
        setPalinLegnthHashMap(wordList);
        for (int i = 0; i < wordList.size(); i++) {
            String cur = wordList.get(i);
            root.insert(i, cur, (k) -> isPalinRemain(cur, k));
        }
        int answer = 0;
        for (int i = 0; i < wordList.size(); i++) {
            String cur = wordList.get(i);
            answer = Math.max(answer,
                root.walk(i, wordList.get(i), (k) -> isPalin(cur, k)));
        }
        System.out.println(answer);
    }

    private boolean isPalin(String target, int start) {
        int paddingStart = start * 2 + 1;
        int paddingEnd = target.length() * 2 - 1;
        int mid = (paddingStart + paddingEnd) / 2;
        int length = palinRadiusHashMap.get(target)[mid];
        if (target.length() - start == length) {
            return true;
        }
        return false;
    }

    private boolean isPalinRemain(String target, int remainLength) {
        if (remainLength == 0) {
            return true;
        }
        return palinRadiusHashMap.get(target)[remainLength] >= remainLength;
    }

    private void setPalinLegnthHashMap(List<String> wordList) {
        for (String word : wordList) {
            int globalCenter = 0;
            int globalRight = 0;
            String target = appendPadding(word);
            int[] radiusOf = new int[target.length()];
            int[] lengthOf = new int[target.length()];
            palinRadiusHashMap.put(word, lengthOf);
            for (int i = 0; i < target.length(); i++) {
                int determineRadius;
                if (i > globalRight) {
                    determineRadius = 0;
                } else {
                    int mirrorIndex = globalCenter - (i - globalCenter);
                    int mirrorRadius = radiusOf[mirrorIndex];
                    determineRadius = Math.min(mirrorRadius, globalRight - i);
                }
                int localLeft = i - determineRadius;
                int localRight = i + determineRadius;
                while (true) {
                    if (localLeft < 0) {
                        break;
                    }
                    if (localRight == target.length()) {
                        break;
                    }
                    if (target.charAt(localLeft) != target.charAt(localRight)) {
                        break;
                    }
                    localLeft--;
                    localRight++;
                }
                localLeft++;
                localRight--;
                radiusOf[i] = localRight - i;
                lengthOf[i] = (radiusOf[i] * 2 + 1) / 2;
                if (localRight > globalRight) {
                    globalRight = localRight;
                    globalCenter = i;
                }
            }
        }
    }

    private String appendPadding(String word) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < word.length(); i++) {
            sb.append(word.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }
}

class Node {

    int index = -1;
    int maxPalCount = 0;
    boolean end = false;
    HashMap<Character, Node> childNodes = new HashMap<>();

    public Node() {
    }

    public void insert(int index, String target, IntPredicate isPalinRemain) {
        Node cur = this;
        int pointer = -1;
        target = new StringBuilder(target).reverse().toString();
        for (char letter : target.toCharArray()) {
            Node child = cur.childNodes.get(letter);
            if (child == null) {
                child = new Node();
                cur.childNodes.put(letter, child);
            }
            pointer++;
            int remainLength = target.length() - (pointer + 1);
            if (isPalinRemain.test(remainLength)) {
                child.maxPalCount = Math.max(child.maxPalCount, target.length() - (pointer + 1));
            }
            cur = child;
        }
        cur.index = index;
        cur.end = true;
    }

    public int walk(int index, String target, IntPredicate isPalin) {
        Node cur = this;
        int pointer = -1;
        int maxLength = 0;
        for (char letter : target.toCharArray()) {
            Node child = cur.childNodes.get(letter);
            if (child == null) {
                break;
            }
            pointer++;
            if (child.index != index && child.end) {
                if (isPalin.test(pointer + 1)) {
                    maxLength = Math.max(maxLength, target.length() + pointer + 1);
                }
            }
            cur = child;
        }
        if (pointer == target.length() - 1) {
            if (cur.end && cur.index != index) {
                maxLength = Math.max(maxLength, 2 * target.length());
            }
            if (cur.maxPalCount > 0) {
                maxLength = Math.max(maxLength, 2 * target.length() + cur.maxPalCount);
            }
        }
        return maxLength;
    }
}