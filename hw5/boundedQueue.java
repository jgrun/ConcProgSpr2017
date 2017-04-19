import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicInteger;

// Should be FIFO of set size
public class boundedQueue {
  static int size;
  static AtomicInteger head;
  static AtomicInteger tail;
  static AtomicInteger items;
  static AtomicIntegerArray array;
  public boundedQueue(int set_size) {
    size = set_size;
    int[] setter = new int[size];
    for(int z = 0; z < set_size; z++) {
      setter[z] = 0; // Init array to false
    }
    array = new AtomicIntegerArray(setter); // set array to all false
    head = new AtomicInteger(0);
    tail = new AtomicInteger(2);
    items = new AtomicInteger(2);
  }
  public boolean push(int item) {
    if(full()) return false;
    array.set(tail.getAndIncrement() % size, item);
    items.getAndIncrement();
    return true;
  }
  public int pop() {
    if(empty()) return -1;
    int ret = array.get(head.getAndIncrement() % size);
    items.getAndIncrement();
    return ret;
  }
  public boolean full() {
    if(items.get() == size) return true;
    return false;
  }
  public boolean empty() {
    if(items.get() == 0) return true;
    return false;
  }
  public int getItems() {
    return items.get();
  }
}
