package basicsofcomputing;

public class DecimalToBinary {
    public static void main(String[] args){
        int num = 36;
        StringBuilder sb = new StringBuilder();
        while(num!=0){
            sb.insert(0,num%2);
            num = num/2;
        }
        System.out.println(sb);
    }
}
