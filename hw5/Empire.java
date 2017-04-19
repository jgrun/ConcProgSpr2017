import java.util.concurrent.atomic.AtomicInteger;

public class Empire {
  static boundedQueue land;
  static AtomicInteger status;
  public Empire(int size) {
    land = new boundedQueue(size);
    status = new AtomicInteger(0); // holds game state; 1 = build win, 2 = destroy win
  }
  public void build() {
    if(!land.push(1)) {
      status.compareAndSet(0, 1);
      return;
    }
  }
  public void destroy() {
    if(land.pop() == -1) {
      status.compareAndSet(0, 2);
      return;
    }
  }
  public int getItems() {
    return land.getItems();
  }
}
