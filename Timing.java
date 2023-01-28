public class Timing {
    private static String yellow = "\033[33m";
    private static String blue = "\u001b[34m";

    private static String reset = "\u001B[0m";

    public static void pause(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void loading(int inc, String colorString) {
        String color;
        int length = 30;

        switch (colorString) {
            case "Yellow": color = yellow; break;
            case "Blue": color = blue; break;
            default: color = yellow; break;
        }

        System.out.println();

        for(int i = 0; i < length + 1; i++) {
            pause(inc / length);
            System.out.print(color + "█");
        }

        System.out.println(reset + "\n");
    }
}