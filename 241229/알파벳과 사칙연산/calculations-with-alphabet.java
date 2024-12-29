import java.util.*;

public class Main {
    private static class Solver{
        String expression;
        int numberCount = 0;
        int result = Integer.MIN_VALUE;

        List<Integer> permutation = new ArrayList<>();

        HashSet<Character> alphabetHash = new HashSet<>();

        public Solver(
                String expression
        ){
            this.expression = expression;
        }

        public void solve(){
            numberCount = getNumberCount();
            setPermutation(0);
            System.out.println(result);
        }

        private void setPermutation(int size){
            if(size == numberCount){
                result = Math.max(result, getExpressionResult());
                return;
            }

            for(int i = 1; i <= 4; i++){
                permutation.add(i);
                setPermutation(size+1);
                permutation.remove(permutation.size()-1);
            }
        }

        private int getExpressionResult(){
            HashMap<Character, Integer> intFinder = new HashMap<>();

            int index = 0;
            for(Character alphabet: alphabetHash){
                intFinder.put(alphabet, permutation.get(index));
                index++;
            }

            int currentValue = intFinder.get(expression.charAt(0));
            for(int i = 0; i < expression.length(); i++){
                if(expression.charAt(i) == '+'){
                    int nextValue = intFinder.get(expression.charAt(i+1));
                    currentValue += nextValue;
                }
                if(expression.charAt(i) == '-'){
                    int nextValue = intFinder.get(expression.charAt(i+1));
                    currentValue -= nextValue;
                }
                if(expression.charAt(i) == '*'){
                    int nextValue = intFinder.get(expression.charAt(i+1));
                    currentValue *= nextValue;
                }
            }
            return currentValue;
        }

        private int getNumberCount(){
            int numberCount = 0;
            for(int i = 0; i < expression.length(); i++){
                if(isMathExpression(expression.charAt(i))){
                    continue;
                }
                if(alphabetHash.contains(expression.charAt(i))){
                    continue;
                }
                alphabetHash.add(expression.charAt(i));
                numberCount++;
            }
            return numberCount;
        }

        private boolean isMathExpression(Character ch){
            return ch == '+' || ch == '-' || ch == '*';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.next();
        new Solver(expression).solve();
    }
}