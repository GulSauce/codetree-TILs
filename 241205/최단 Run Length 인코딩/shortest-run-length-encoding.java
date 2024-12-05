import java.util.*;

public class Main {
    private static class Solver{
        String A;

        public Solver(
                String  A
        ){
            this.A = A;
        }

        public  void  solve(){
            int result = Integer.MAX_VALUE;
            String runLengthEncode = getRunLengthEncode(A);
            result = Math.min(result, runLengthEncode.length());
            String currentStr = A;
            for(int i = 0; i < A.length(); i++){
                currentStr = rightShiftString(currentStr);
                runLengthEncode = getRunLengthEncode(currentStr);
                result = Math.min(result, runLengthEncode.length());
            }
            System.out.println(result);
        }

        private String rightShiftString(String currentStr){
            String newString = "";
            char lastChar = currentStr.charAt(currentStr.length()-1);
            newString += lastChar;
            for(int i = 0; i < currentStr.length()-1; i++){
                newString += currentStr.charAt(i);
            }
            return newString;
        }

        private String getRunLengthEncode(String currentStr){
            String result = "";

            char currentAlphabet = currentStr.charAt(0);
            int length = 1;

            for(int i = 1; i < currentStr.length(); i++){
                if(currentStr.charAt(i) == currentAlphabet){
                    length++;
                    continue;
                }
                result += currentAlphabet+String.valueOf(length);
                length  = 1;
                currentAlphabet = currentStr.charAt(i);
            }
            result += currentAlphabet+String.valueOf(length);

            return  result;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        new Solver(A).solve();
    }
}