import java.util.concurrent.atomic.AtomicIntegerArray;

public class nBoolean {
  static AtomicIntegerArray array;
  static int size;
  public nBoolean(int i) {
    int[] temp = new int[i];
    for(int j = 0; j < i; j++) {
      temp[j] = 0;
    }
    array = new AtomicIntegerArray(temp);
    size = i;
  }
  public void nBarrierSet(int index) {
    while(!nBarrierCheck(index));
    array.set(index, 1);
    while(!nBarrierCheck(size)){
      System.out.print("");
    }
  }
  private boolean nBarrierCheck(int index) {
    if(index == 0) {
      return true;
    }
    if(array.get(index - 1) == 1) return true;
    return false;
  }
  public void printArray() {
    for(int i = 0; i < size; i++) {
      System.out.println(array.get(i));
    }
  }
}
