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
  public void lock() {
    int i = (int)Thread.currentThread().getId();
    flag[i] = true;
    label[i] = max++;
    int j = 0;
    while(true) {
      if(j >= size) j = 0;
      if(j == i) j += 1;
      if(flag[j] && ((label[i] < label[j] || label[i] == label[j]) && i < j)) {
        break;
      }
      j++;
    }
  }
  public void unlock() {
    flag[(int)Thread.currentThread().getId()] = false;
  }
}
