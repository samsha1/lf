import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SequtialMechanism {
    public static class Foo {
        public void first() { print("first"); }
        public void second() { print("second"); }
        public void third() { print("third"); }
    }

    static void print(String text) {
        System.out.println(text);
    }

    static class Step extends Thread {
        private final CountDownLatch starter = new CountDownLatch(1);
        private final List<Step> nextSteps = new ArrayList<>();
        private final Runnable action;

        Step(final Runnable action) {
            this.action = action;
        }

        @Override
        public void run() {
            try {
                starter.await(); // wait until someone kicks the starter with countDown()
                action.run();
                for (Step s : nextSteps) { // let's start the following steps
                    s.starter.countDown();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                for (Step s : nextSteps) { // let's propagate 
                    s.interrupt(); // the interruption
                }
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        Foo foo = new Foo();
        
        Step firstStep = new Step(() -> foo.first());
        Step secondStep = new Step(() -> foo.second());
        Step thirdStep = new Step(() -> foo.third());

        firstStep.nextSteps.add(secondStep); // set sequence of execution
        secondStep.nextSteps.add(thirdStep);

        secondStep.start();
        thirdStep.start(); // order of start doesn't matter
        firstStep.start();

        firstStep.starter.countDown(); // kick the starterof the first step
    }
}