public class bakery {
  boolean[] flag;
  int[] label;
  int max;
  int size;
  public bakery (int n) {
    flag = new boolean[n];
    label = new int[n];
    max = 0;
    size = n;
    for(int i = 0; i < n; i++) {
      flag[i] = false;
      label[i] = 0;
    }
  }

  // Lock the bakery class
  public void lock() {
    int i = (int)(Thread.currentThread().getId() - 10)  ;
    flag[i] = true; // generate flag
    label[i] = ++max; // label with max
    int j = 0;
    while(true) {
      if(j >= size) j = 0;
      if(!flag[j] && ((label[i] > label[j]) || (label[i] == label[j] && i > j))) {
        break;
      }
      j++;
    }
  }

  // unlock the bakery class
  public void unlock() {
    flag[(int)Thread.currentThread().getId() - 10] = false;
  }
}
