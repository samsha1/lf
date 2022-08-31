// Write a program to stop a thread.
class MyThread implements Runnable {
 
    
    private boolean stop;
 
    private String name;
    Thread t;
 
    MyThread(String threadname)
    {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        stop = false;
        t.start(); // Starting the thread
    }
 
    // execution of thread starts from run() method
    public void run()
    {
        int i = 0;
        System.out.println("Run is called");
        while (!stop) {
            System.out.println(name + ": " + i);
            i++;
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        System.out.println(name + " Stopped.");
    }
 
    // for stopping the thread
    public void stop()
    {
        stop = true;
    }
}
 
// Main class
public class StopThread {
    public static void main(String args[])
    {
        // creating two objects t1 & t2 of MyThread
        MyThread t1 = new MyThread("First  thread");
        MyThread t2 = new MyThread("Second thread");
        try {
            Thread.sleep(500);
            t1.stop(); // stopping thread t1
            t2.stop(); // stopping thread t2
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            System.out.println("Caught:" + e);
        }
        System.out.println("Exiting the main Thread");
    }
}