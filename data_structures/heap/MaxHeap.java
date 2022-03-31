package org.example.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Three primary operations that a MaxHeap should perform
 * -- add()
 * -- extractMax()
 * -- size()
 *- - - - - - - - - - - - -
 * @author : subham-santra
 */
public class MaxHeap<T> {

    private final int SIZE;
    private final List<T> heap;
    private final Comparator<T> comparator;

    public MaxHeap(int SIZE, T INF, Comparator<T> comparator) {
        this.SIZE = SIZE;
        heap = new ArrayList<>(SIZE);
        heap.add(INF);
        this.comparator = comparator;
    }

    int parent(int index) {
        return (index >> 1);
    }

    int leftChild(int index) {
        return (index << 1);
    }

    int rightChild(int index) {
        return (index << 1) + 1;
    }

    public int size() {
        return heap.size();
    }

    public void add(T val) {
        heap.add(val);
        heapify(size() - 1);
    }

    public T extractMax() {
        final T max = heap.get(1);
        heap.remove(1);
        for (int i = heap.size() - 1; i >= 1; --i) {
            heapify(i);
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < size(); ++i) {
            builder.append('{');
            builder.append(
//                    String.format("node=%d -- leftChild=%d | rightChild=%d", get(i), get(leftChild(i)), get(rightChild(i)))
                    String.format("node=%d", get(i))
            );
            builder.append('}');
            builder.append(' ');
        }
        return builder.toString();
    }

    private T get(int i) {
        return i < size() ? heap.get(i) : null;
    }

    private void swap(int fromIndex, int toIndex) {
        final T temp = heap.get(fromIndex);
        heap.set(fromIndex, heap.get(toIndex));
        heap.set(toIndex, temp);
    }

    private void heapify(int index) {
        int parent;
        while (comparator.compare(heap.get(index), heap.get((parent = parent(index)))) > 0) {
            swap(index, parent);
            index = parent;
        }
    }


    /**
     * Driver Code
     *
     * @param args
     */
    public static void main(String[] args) {
        MaxHeap<Integer> integerMaxHeap = new MaxHeap<>(4, Integer.MAX_VALUE, (x, y) -> x - y);

        for(int item : new int[] {4, 3, 2, 1, 5, 6, 0, 6, 11}) {
            integerMaxHeap.add(item);
            System.out.println(integerMaxHeap);
        }
    }
}
