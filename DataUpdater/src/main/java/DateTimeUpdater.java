public class DateTimeUpdater extends Thread {

    private GUI gui;

    public DateTimeUpdater(GUI gui){
        this.gui = gui;
    }

    @Override
    public void run(){
        updateTimeOnInterval();
    }

    private void updateTimeOnInterval(){

        while(true){
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            gui.updateTime();
        }
    }

}
