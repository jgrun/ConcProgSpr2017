public class sharedTest {
  static int barrierNum = 4;
  static int barrier = barrierNum;
  static long timing;
  static sharedFooBar b = new sharedFooBar(barrierNum);
  public static void main(String[] args) {
    Thread[] barrierTest = new Thread[barrierNum];
    for(int i = 0; i < barrierTest.length; i++) {
      barrierTest[i] = new Thread(new Runnable() {
        public void run() {
          System.out.println("Foo");
          long start = System.currentTimeMillis();
          while((System.currentTimeMillis() - start) < 20);
          b.incrementBarrier();
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
// In ms
// Shared times 4:  21,  23,  22,  30,  23  :  22.667
// Shared times 16: 74,  55,  70,  81,  72  :  72.00
// Shared times 64: 233, 216, 199, 193, 243 :  216.00
