
public class trinaryConsensus{
  trinary reg;
  int n;
  public trinaryConsensus(int regNum) {
    n = regnum;
    reg = new trinary(n);
  }
  public boolean propose(int value) {
    int j = 1;
    if(reg.compareAndSet(0, 2, 1)) {
      while(value != 0) {
        int temp = value % 2;
        reg.compareAndSet(j, 2, temp);
        value >>> 1;
        j++;
      }
    }
  }
}
