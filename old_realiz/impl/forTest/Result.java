package old_realiz.impl.forTest;

import java.util.*;

/**
 * Created by Student on 31-May-16.
 */
class Result<T> implements ListIterator{
    List<T> list = new ArrayList<>();
    ListIterator<T> resultIterator = list.listIterator();

    public Result(List<T> list) {
        this.list = list;
    }
    public void addAll(Collection<T> collection){
        list.addAll(collection);
    }
    @Override
    public boolean hasNext() {
        return resultIterator.hasNext();
    }

    @Override
    public T next() {
        return  resultIterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return resultIterator.hasPrevious();
    }

    @Override
    public T previous() {
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
        resultIterator.set((T) o);
    }

    @Override
    public void add(Object o) {
        resultIterator.add((T) o);

    }
}
