import java.lang.Thread;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.Random;

public class test {
  static int threadCount = 100; // Implement this many threads to test with
  static int bufSize = 512; // Size of circBuf to test with
  static int i;
  final static CyclicBarrier gate = new CyclicBarrier(threadCount + 1);
  static coarseReentrant buf = new coarseReentrant(bufSize);
  static Random numgen = new Random();

  public static void main(String[] args) {
    Thread[] thread = new Thread[threadCount];
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
            buf.add((int)Thread.currentThread().getId(), numgen.nextInt(512));
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
    //buf.bufferDump();
    System.out.print("Execution time: ");
    System.out.print(runTime);
    System.out.println("ns");
  }
}

// all in ns
// noLock:     2129290,  1772544,  1262338,  1245811,  1218823,  1536571,  1428526,  1169048,  1201552,  1469575
// coarse 30:  20230247, 14488204, 1585960, 12861409, 13824603, 15662229, 14853039, 13809323, 15322534, 13757168
// coarse 100:
