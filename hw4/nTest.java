import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

public class nTest {
  static int barrierNum = 64;
  static int barrier = barrierNum;
  static long timing;
  static AtomicInteger k = new AtomicInteger(0);
  static nBoolean b = new nBoolean(barrierNum);
  public static void main(String[] args) {
    Thread[] barrierTest = new Thread[barrierNum];
    for(int i = 0; i < barrierTest.length; i++) {
      barrierTest[i] = new Thread(new Runnable() {
        public void run() {
          ThreadLocal<Integer> id = new ThreadLocal<Integer>();
          id.set(k.getAndIncrement());
          System.out.println("Foo");
          long start = System.currentTimeMillis();
          while((System.currentTimeMillis() - start) < 20);
          b.nBarrierSet(id.get());
          System.out.println("Bar");
        }
      });
    }
    timing = System.currentTimeMillis();
    for(int i = 0; i < barrierTest.length; i++) {
      barrierTest[i].start();
    }
    for(int i = 0; i < barrierTest.length; i++) {
      try {
        barrierTest[i].join();
      }
      catch(InterruptedException e) {}
    }
    System.out.print("Execution Time: ");
    System.out.print((System.currentTimeMillis() - timing));
    System.out.println("ms");
  }
}

// n times 4:  27,  23,  23,  23,  23  :  23.00
// n times 16: 83,  77,  82,  103, 88  :  84.33
// n times 64: 671, 508, 400, 609, 614 :  577.00
