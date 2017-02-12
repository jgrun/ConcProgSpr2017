public class ThreadID {
  private static volatile int nextID = 0;
  private static class ThreadLocalID extends ThreadLocal<Integer> {
    protected synchronized Integer initialValue() {
      return nextID++;
    }
  }
  private static ThreadLocalID threadID = new ThreadLocalID();
  public static int get() {
    return threadID.get();
  }
  public static void set(int index) {
    threadID.set(index);
  }

  public static void main(String[] args) {
    Thread[] thread = new Thread[8];
    for(int i = 0; i < 8; i++) {
      thread[i](new Runnable() {
        public void run() {
          System.out.println("Hello world from thread" + ThreadID.get());
        }
      });
    }
    for(int i = 0; i < 8; i++) {
      thread[i].start();
    }
    for(int i = 0; i < 8; i++) {
      thread[i].join();
    }
  }
}
