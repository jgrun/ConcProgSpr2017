public class programTest {

  public static void main(String[] args) {
    lfsr numgen = new lfsr(0x9, 4);
    for(int i = 0; i < 16; i++) {
      System.out.println(numgen.next());
    }
  }
}

// 0x213 for 10, maps to 1024 threads
// 0x16d for 9, maps to 512 threads
// 0xv1 for 8, maps to 256 threads
// 0x9 for 4, maps to 16 threads
