package util;

public class Logger {

    private static boolean debug = false;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Logger(){

    }

    public static void setDebug(boolean debug){
        Logger.debug = debug;
    }


    private static void log(String message, LogMode mode){
        switch (mode){
            case DEBUG:
                System.out.println(ANSI_CYAN+message+ANSI_RESET);
                break;
            case ERROR:
                System.out.println(ANSI_RED+message+ANSI_RESET);
                break;
            case RESULT:
                System.out.println(ANSI_YELLOW+message+ANSI_RESET);
                break;
            case INFO:
                System.out.println(ANSI_WHITE+message+ANSI_RESET);
                break;
        }
    }

    public static void debug(Object message){
        if(debug) {
            log(message.toString(), LogMode.DEBUG);
        }
    }

    public static void error(Object message){
        log(message.toString(),LogMode.ERROR);
    }

    public static void result(Object message){
        log("Result: "+ message.toString(),LogMode.RESULT);
    }

    public static void info(Object message){
        log(message.toString(),LogMode.INFO);
    }

    private enum LogMode{
        DEBUG, ERROR, RESULT, INFO
    }
}
