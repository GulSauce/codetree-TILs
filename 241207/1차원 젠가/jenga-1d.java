import java.util.*;

public class Main {
    private static class Solver{
        ArrayList<Integer> jengaBlocks;
        BlockRemovalInfo[] blockRemovalInfos;

        public Solver(
                ArrayList<Integer> jengaBlocks,
                BlockRemovalInfo[] blockRemovalInfos
        ){
            this.jengaBlocks = jengaBlocks;
            this.blockRemovalInfos = blockRemovalInfos;
        }

        public  void solve(){
            for(BlockRemovalInfo blockRemovalInfo: blockRemovalInfos){
                jengaBlocks = removeBlockAtRange(blockRemovalInfo);
            }
            printResult();
        }

        private void printResult(){
            System.out.println(jengaBlocks.size());
            for(int jengaBlock: jengaBlocks){
                System.out.println(jengaBlock);
            }
        }

        private ArrayList<Integer> removeBlockAtRange(BlockRemovalInfo blockRemovalInfo){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i = 0; i < jengaBlocks.size(); i++){
                if(isInBlockRemovalInfoRange(i, blockRemovalInfo)){
                    continue;
                }
                temp.add(jengaBlocks.get(i));
            }
            return temp;
        }

        private boolean isInBlockRemovalInfoRange(int index, BlockRemovalInfo blockRemovalInfo){
            if(blockRemovalInfo.s <= index && index <= blockRemovalInfo.e){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> jengaBlocks = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int block = sc.nextInt();
            jengaBlocks.add(block);
        }
        int s1 = sc.nextInt();
        int e1 = sc.nextInt();
        int s2 = sc.nextInt();
        int e2 = sc.nextInt();

        BlockRemovalInfo[] indexedBlockRemovalInfos = {new BlockRemovalInfo(s1, e1), new BlockRemovalInfo(s2, e2)};
        new Solver(jengaBlocks, indexedBlockRemovalInfos).solve();
    }

    private static class BlockRemovalInfo{
        int s;
        int e;

        public BlockRemovalInfo(
                int s,
                int e
        ){
            this.s = s-1;
            this.e = e-1;
        }
    }
}