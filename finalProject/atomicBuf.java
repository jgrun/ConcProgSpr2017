import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicInteger;

public class atomicBuf {
  protected AtomicInteger numItems; // Contains the number of items in the buffer
  protected int length; // length of the buffer
  protected AtomicIntegerArray base; // Actual buffer
  protected AtomicIntegerArray valid;
  public boolean bufferFull() {
    if(numItems.get() == length) return true;
    else return false;
  }
  public boolean bufferEmpty() {
    if(numItems.get() == 0) return true;
    else return false;
  }
  public void bufferDump() {
    for(int i = 0; i < length; i++) {
      System.out.println(base.get(i));
    }
  }
  public atomicBuf(int l) {
    length = l;
    int i;
    int j[] = new int[length];
    int k[] = new int[length];
    for(i = 0; i < length; i++) {
      j[i] = -1;
      k[i] = 0;
    }
    base = new AtomicIntegerArray(j); // Initialize buffer of length l
    valid = new AtomicIntegerArray(k);
    numItems = new AtomicInteger(0); // Init number of items in buffer to 0
  }
  public int add(int item, int location) {
    if(bufferFull()) {
      return 1;
    }
    if(valid.get(location) == 1) return 1; // If location already taken, try again
    valid.set(location, 1);
    base.set(location, item);
    numItems.getAndIncrement();
    return 0; // return no error
  }
  public int remove(int location) {
    if(bufferEmpty()) {
      return -3;
    }
    if(valid.get(location) == 0) return -3; // If location invalid, try again
    valid.set(location, 0);
    numItems.getAndDecrement();
    return base.get(location); // return popped value
  }
}
