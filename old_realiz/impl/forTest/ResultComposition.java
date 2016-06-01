package old_realiz.impl.forTest;

import java.util.*;

/**
 * Created by Student on 31-May-16.
 */
public class ResultComposition implements ListIterator{
    List<String> list = new ArrayList<>();
    ListIterator<String> resultIterator = list.listIterator();

    public ResultComposition(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return resultIterator.hasNext();
    }

    @Override
    public String next() {
        return  resultIterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return resultIterator.hasPrevious();
    }

    @Override
    public String previous() {
        return resultIterator.previous();
    }

    @Override
    public int nextIndex() {
        return resultIterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return resultIterator.previousIndex();
    }

    @Override
    public void remove() {
      resultIterator.remove();
    }

    @Override
    public void set(Object o) {
         resultIterator.set((String) o);
    }

    @Override
    public void add(Object o) {
        resultIterator.add((String) o);

    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("B", "C", "D");
        ResultComposition composition = new ResultComposition(list1);
        while (composition.hasNext()){
            System.out.println(composition.next());
        }
    }
}
