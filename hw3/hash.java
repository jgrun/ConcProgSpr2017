public class hash {
  int length;
  node n;
  lazyList[] j;
  public hash(int size) {
    int i = 0;
    length = size;
    j = new lazyList[size];
  }

  private int findHash(int data) {
    return data % length;
  }

  public void add(int item) {
    n.data = item;
    j[findHash(item)].add(n);
  }

  public void remove(int item) {
    n.data = item;
    j[findHash(item)].remove(n);
  }

  public void contains(int item) {
    n.data = item;
    j[findHash(item)].contains(n);
  }
}

/* Becuase each element of the hash table is linearizable, the overall
 * structure is also linearizable
 */
