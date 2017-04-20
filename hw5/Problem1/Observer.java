public class Observer{
  static Empire game;
  static long endTime;
  public Observer(Empire TBO) {
    game = TBO;
    long start = System.currentTimeMillis();
    endTime = start + 5000;
  }
  public void result() {
    while(true) {
      int status = game.status.get();
      if(status == 1) {
        System.out.println("HOME TEAM WINS!");
        return;
      }
      else if(status == 2 ) {
        System.out.println("ENEMY TEAM WINS!");
        return;
      }
      else if(System.currentTimeMillis() >= endTime) {
        System.out.println("TIE!");
        return;
      }
    }
  }
}
