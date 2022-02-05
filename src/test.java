public class test {
    public static void main(String[] args) {
        if (args.length != 0) {
            String filename = args[0];
            System.out.println(filename);
        }
        else{
            System.out.println("Usage: java test [filename]");
        }
    }
}
