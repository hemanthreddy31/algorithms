public class BitSetCode {

        public static void main(String[] args){
            System.out.println(getAns(3,5));
        }
        public static int getAns(int a,int N){
            int x=a,ans=1;
            while(N!=0){
                if((N&1)==1){
                    ans=ans*x;
                }
                x=x*x;
                N=N>>1;
            }
            return ans;
        }

}
