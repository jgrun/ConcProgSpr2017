public class bakeryTest {
  static int count = 0; // Set count as a global variable
  private static final bakery lock = new bakery(50);
  public static void main(String[] args) {
    Thread[] thread = new Thread[50]; // implement 128 threads
    for(int i = 0; i < thread.length; i++) {
      thread[i] = new Thread(new Runnable() { // use anonymous class to define all 128 threads
        public void run() {
          // increments will document number of incrementations
          for(int increments = 0; increments < 100; increments++) {
            lock.lock(); // lock here because the shared resource is only used in the next line
            try {
              count += 1; // increment count
            }
            finally {
              lock.unlock(); // unlock here because shared rescource is no longer being used
            }
          }
        }
      });
    }
    for(int i = 0; i < thread.length; i++) {
      thread[i].start(); // Start threads
    }
    for(int i = 0; i < thread.length; i++) {
      try{
        thread[i].join(); // Wait for threads to complete
      }
      catch(InterruptedException e) { // I'm not actually sure what this does, but I needed it to compile correctly

      }
    }
    System.out.println(count); // Print out count value
  }
}
