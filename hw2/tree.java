public class tree {
  int[] level;
  int[] victim;
  int size;
  public void Filter(int n) {
    level = new int[n];
    victim = new int[n];
    size = n;
    for(int i = 0; i < n; i++) {
      level[i] = 0;
    }
  }
  public void lock() {
    int me = (int) Thread.currentThread().getId() - 10;
    for(int i = 1; i < size; i++) {
      level[me] = i;
      victim[i] = me;
      int index = 0;
      if(index == me) {
        index++;
      }
      if(index > size - 1) {
        index = 0;
      }
      while(level[index] >= i && victim[i] == me) {
        index++;
        if(index == me) {
          index++;
        }
        if(index > size - 1) {
          index = 0;
        }
      }
    }
  }
  public void unlock() {
    int me = (int) Thread.currentThread().getId() - 10;
    level[me] = 0;
  }
}
