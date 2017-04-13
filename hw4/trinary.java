import java.util.concurrent.atomic.AtomicIntegerArray;

public class trinary {
  int n;
  AtomicIntegerArray i;
  public trinary(int registers) {
    n = registers;
    int[] array = new int[n];
    for(int j = 0; j < n; j++) {
      array[j] = 2;
    }
    i = new AtomicIntegerArray(array);
  }
  public boolean compareAndSet(int j, int expect, int update) {
    return i.compareAndSet(i, expect, update);
  }
  public int get(int j) {
    return i.get(j);
  }
}
