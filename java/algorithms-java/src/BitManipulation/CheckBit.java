package BitManipulation;

import java.util.Scanner;

public class CheckBit {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        int a= sc.nextInt();
        int b= sc.nextInt();
        System.out.println(PositionBitCheck(a,b));
    }
    public static boolean PositionBitCheck(int a, int b){
        return (((a>>b) & 1) == 1);
    }
}
