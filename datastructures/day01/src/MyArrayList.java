public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // Runtime: O(1)*
    public void add(Cow c) {
        if (size == elems.length) {
            Cow[] newElems = new Cow[size*2];
            System.arraycopy(elems,0,newElems,0,size);
            elems = newElems;
        }
        elems[size] = c;
        size++;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // Runtime: O(n)*
    public Cow remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Cow r = elems[index];
        for (int i = index; i < size; i++){
            if (i == size-1){
                elems[i] = null;
            } else {
                elems[i] = elems[i+1];
            }
        }
        size--;
        if (size < elems.length/4 && size > 1) {
            Cow[] newElems = new Cow[size/2];
            System.arraycopy(elems,0,newElems,0,size);
            elems = newElems;
        }
        return r;
    }

    // Runtime: O(n)*
    public void add(int index, Cow c) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if (size == elems.length) {
            Cow[] newElems = new Cow[size*2];
            System.arraycopy(elems,0,newElems,0,size);
            elems = newElems;
        }
        for (int i=size; i>index; i--){
            elems[i] = elems[i-1];
        }
        elems[index] = c;
        size++;
    }
}