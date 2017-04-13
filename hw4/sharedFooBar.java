public class sharedFooBar {
  static testAndTestAndSet lock;
  static int barrier;
  static int goal;
  public sharedFooBar(int i) {
    lock = new testAndTestAndSet();
    barrier = 0;
    goal = i;
  }
  public void incrementBarrier() {
    while(true) {
    //  if(lock.tryLock()) {
        lock.lock();
        try {
          barrier++;
        }
        finally {
          lock.unlock();
        }
        break;
    //  }
    }
    while(barrier < goal) {
      System.out.print("");
    }
  }
}
