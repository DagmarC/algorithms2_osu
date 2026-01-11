import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackGenerics<T> {

    // We use ArrayList as the backing data structure
    private final ArrayList<T> list;

    public StackGenerics() {
        this.list = new ArrayList<>();
    }

    /**
     * Pushes an item onto the top of this stack.
     * mapped to ArrayList.add() (adds to the end)
     */
    public void push(T item) {
        list.add(item);
    }

    /**
     * Removes the object at the top of this stack and returns it.
     * mapped to ArrayList.remove() at the last index
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // Remove the last element (Top of stack)
        return list.removeLast();
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     * mapped to ArrayList.get() at the last index
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.getLast();
    }

    /**
     * Tests if this stack is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}