package catalog.domain.mediation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Student on 31-May-16.
 */
public class Result<T> implements ListIterator{
    private List<T> list = new ArrayList<>();
    private ListIterator<T> resultIterator = list.listIterator();

    public Result(List<T> list) {
        this.list = list;
    }
    public void addAll(Collection<T> collection){
        list.addAll(collection);
    }

    public int size(){
        return list.size();
    }

    public List<T> getList() {
        return list;
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



    @Override
    public String toString() {
        return "Result{" +
                "list=" + list +
                ", resultIterator=" + resultIterator +
                '}';
    }
}
