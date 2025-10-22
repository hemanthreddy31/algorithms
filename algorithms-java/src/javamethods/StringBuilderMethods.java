package javamethods;

public class StringBuilderMethods {
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        sb.append(102200);
        sb.insert(0,"abcdef");
        System.out.println(sb);
        System.out.println(sb.charAt(3));
        sb.delete(2,3);
        System.out.println(sb);
        sb.deleteCharAt(3);
        System.out.println(sb);
        sb.reverse();
        System.out.println(sb);
        System.out.println(sb.length());
        sb.replace(0,2,"AA");
        System.out.println(sb);
    }
}
