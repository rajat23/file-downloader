package console;

public class Display {


    public static void startProgress() {
        System.out.print("[");
    }

    public static void endProgress() {
        System.out.print("]");
        System.out.println("Download Complete ! Enjoy");
    }

    public static void progress() {
        System.out.print(".");
    }
}
