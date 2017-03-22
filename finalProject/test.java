import java.lang.Thread;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class test {
  static int threadCount = 1; // Implement this many threads to test with
  static int bufSize = 1000; // Size of circBuf to test with
  static int i;
  final static CyclicBarrier gate = new CyclicBarrier(threadCount + 1);
  static noLock buf = new noLock(bufSize);

  public static void main(String[] args) {
    Thread[] thread = new Thread[threadCount];
    for(i = 0; i < thread.length; i++) {
      thread[i] = new Thread(new Runnable() {
        public void run() {
          try {
            gate.await();
          } catch(InterruptedException e) {}
          catch(BrokenBarrierException e) {}
          while(!buf.bufferFull()) {
            buf.add((int)Thread.currentThread().getId());
          }
        }
      });
    }
    for(i = 0; i < thread.length; i++) {
      thread[i].start();
    }
    long startTime = System.nanoTime();
    try {
      gate.await();
    } catch(InterruptedException e) {}
    catch(BrokenBarrierException e) {}
    for(i = 0; i < thread.length; i++) {
      try {
        thread[i].join();
      }
      catch(InterruptedException e) {

      }
    }
    long stopTime = System.nanoTime();
    long runTime = stopTime - startTime;
    // while(!buf.bufferEmpty()) {
    //   System.out.println(buf.remove());
    // }
    System.out.print("Execution time: ");
    System.out.print(runTime);
    System.out.println("ns");
  }
}
