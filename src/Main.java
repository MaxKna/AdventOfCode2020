import util.Day;
import util.Logger;

public class Main {

    public static void main(String[] args) {
        for(int i = 0; i< 24; i++){
            try {
                Class.forName("Day"+(i+1)+".Day"+(i+1)).newInstance();
            } catch (ClassNotFoundException e) {
                Logger.error("Not found: Day"+(i+1));
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        Logger.setDebug(true);
        Day.run(Day.TestMode.TASK_ONLY, Day.RunMode.LATEST);
    }
}
