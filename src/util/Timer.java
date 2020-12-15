package util;

public class Timer {

    private  Long start;
    private  Long stop;

    private long cache = 0;

    private boolean running = false;



    public void start(){
        start = System.currentTimeMillis();
        running = true;
    }

    public void stop(){
        if(!running){
            return;
        }
        stop = System.currentTimeMillis();
        cache += stop-start;
        restart();
    }

    public String getRuntime(){
        long duration = cache;
        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long millis = duration % 1000;
       return minutes +" min "+seconds % 60 + " sec " + millis + " millis";
    }

    private void restart(){
        start = null;
        stop = null;
        running = false;
    }

    public void reset(){
        restart();
        cache = 0;
    }
}
