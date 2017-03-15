import java.util.concurrent.locks.ReentrantLock;

public class node {
  public boolean marked; // holds whether the node is marked or not
  public node next; // next node
  public node prev; // previous node
  public int data; // This is the key variable in the psuedo code\
  private static ReentrantLock lock;
  public node(node item) {
    data = item.data;
    lock = new ReentrantLock();
    next = item;
    prev = item;
    marked = false;
  }
  public void lock() {
    lock.lock();
  }
  public void unlock() {
    lock.unlock();
  }
}
