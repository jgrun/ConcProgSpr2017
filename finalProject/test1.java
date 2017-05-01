import java.lang.Thread;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.Random;

public class test1 {
  static int threadCount = 1; // Implement this many threads to test with
  static int bufSize = 512; // Size of circBuf to test with
  static int i;
  final static CyclicBarrier gate = new CyclicBarrier((2*threadCount) + 1);
  static noLock buf = new noLock(bufSize);
  static Random numgen = new Random();

  public static void main(String[] args) {
    Thread[] thread = new Thread[threadCount];
    Thread[] getter = new Thread[threadCount];
    for(i = 0; i < thread.length; i++) {
      thread[i] = new Thread(new Runnable() {
        public void run() {
          try {
            gate.await();
          } catch(InterruptedException e) {}
          catch(BrokenBarrierException e) {}
          int i = 0;
          while(i <= 1024) {
            i++;
            while(buf.add((int)Thread.currentThread().getId(), numgen.nextInt(512)) == 1);
          }
        }
      });
    }
    for(i = 0; i < getter.length; i++) {
      getter[i] = new Thread(new Runnable() {
        public void run() {
          try {
            gate.await();
          } catch(InterruptedException e) {}
          catch(BrokenBarrierException e) {}
          int i = 0;
          while(i <= 1024) {
            i++;
            while(buf.remove(numgen.nextInt(512)) == -3);
          }
        }
      });
    }
    for(i = 0; i < thread.length; i++) {
      thread[i].start();
    }
    for(i = 0; i < getter.length; i++) {
      getter[i].start();
    }
    long startTime = System.nanoTime();
    try {
      gate.await();
    } catch(InterruptedException e) {}
    catch(BrokenBarrierException e) {}
    for(i = 0; i < thread.length; i++) {
      try {
        thread[i].join();
        getter[i].join();
      }
      catch(InterruptedException e) {

      }
    }
    long stopTime = System.nanoTime();
    long runTime = stopTime - startTime;
    //buf.bufferDump();
    System.out.print("Execution time: ");
    System.out.print(runTime);
    System.out.println("ns");
  }
}
