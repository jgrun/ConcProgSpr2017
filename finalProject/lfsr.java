public class lfsr {
  private int seed;
  private boolean a[];
  private int value;
  private int base;
  public lfsr(int inputSeed, int b) {
    seed = inputSeed;
    base = b;
    value = 1;
    a = new boolean[32];
    for(int i = 0; i <32; i++) {
      a[i] = false;
    }
    if((seed & 0x80000000) != 0) a[31] = true;
    if((seed & 0x40000000) != 0) a[30] = true;
    if((seed & 0x20000000) != 0) a[29] = true;
    if((seed & 0x10000000) != 0) a[28] = true;
    if((seed & 0x8000000) != 0) a[27] = true;
    if((seed & 0x4000000) != 0) a[26] = true;
    if((seed & 0x2000000) != 0) a[25] = true;
    if((seed & 0x1000000) != 0) a[24] = true;
    if((seed & 0x800000) != 0) a[23] = true;
    if((seed & 0x400000) != 0) a[22] = true;
    if((seed & 0x200000) != 0) a[21] = true;
    if((seed & 0x100000) != 0) a[20] = true;
    if((seed & 0x80000) != 0) a[19] = true;
    if((seed & 0x40000) != 0) a[18] = true;
    if((seed & 0x20000) != 0) a[17] = true;
    if((seed & 0x10000) != 0) a[16] = true;
    if((seed & 0x8000) != 0) a[15] = true;
    if((seed & 0x4000) != 0) a[14] = true;
    if((seed & 0x2000) != 0) a[13] = true;
    if((seed & 0x1000) != 0) a[12] = true;
    if((seed & 0x800) != 0) a[11] = true;
    if((seed & 0x400) != 0) a[10] = true;
    if((seed & 0x200) != 0) a[9] = true;
    if((seed & 0x100) != 0) a[8] = true;
    if((seed & 0x80) != 0) a[7] = true;
    if((seed & 0x40) != 0) a[6] = true;
    if((seed & 0x20) != 0) a[5] = true;
    if((seed & 0x10) != 0) a[4] = true;
    if((seed & 0x8) != 0) a[3] = true;
    if((seed & 0x4) != 0) a[2] = true;
    if((seed & 0x2) != 0) a[1] = true;
    if((seed & 0x1) != 0) a[0] = true;
  }
  public int next() {
    int temp = 0;
    for(int i = 0; i < base; i++) {
      temp ^= ((value & ((a[i] ? 1 : 0) << i))) >> i;
    }
    //System.out.println(temp);
    value = (value >> 1) & 0xffffffff;
    value |= temp << (base - 1);
    return value;
  }
}
