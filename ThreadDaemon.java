// 3. How can we make a regular thread Daemon thread in Java?
public class ThreadDaemon {
    
    public static void main(String[] args) {
        new SlaveThread().start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Exception handling thread: " + e.getMessage()) ;
        }

        System.out.println("Main Thread terminates") ;
    }

}

class SlaveThread extends Thread {
    
    public SlaveThread() {
        setDaemon(true); // when set false, the slaveThread continuous to run. When true (daemon) the slaveThread terminates when the main thread / user defined thread terminates.
    }
    
    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Running Slave:  "+count++);

            try {
                sleep(500);
            } catch (InterruptedException e) {
                // handle exception here
            }
        }
    }
}