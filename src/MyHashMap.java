import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    public static void main(String[] args) {
        String a = "f";
        String b = "f";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

    }
    public static class Node<K, V> {
        private final K key;
        private V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
    }
    /**
     *  put get remove containskey size()
     */
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] array;
    private static final int INITIAL_CAPACITY = 1 << 4;

    private int size = 0;
    private float loadFactor;
    public MyHashMap() { //int capacity

    }
    public MyHashMap(int cap, float loadFactor) {
        if (cap < 0) {
            throw new IllegalArgumentException("cap can not be <= 0");
        }
        this.array = (Node<K, V>[]) (new Node[cap]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hashNumber = key.hashCode();
        return hashNumber;
    }
    private int getIndex(K key) {
        return key.hashCode() % array.length;
    }

    private boolean equalsVaule(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    public boolean containsValue(V value) {
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> node : array) {
            while (node != null) {
                if (equalsVaule(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    private boolean equalsKey(K k1, K k2) {
        return k1 == k2 || k1 != null && k1.equals(k2);
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = array[index];
        Node<K, V> node = head;
        while (node != null) {
            if (equalsKey(node.key, key)) {
                V result = node.value;
                node.value = value;
                return result;
            }
            node = node.next;
        }
        Node<K, V> newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
//        if ()
        return null;
    }

    private void reHashing() {
        Node<K, V>[] oldArray = array;
        array = (Node<K, V>[]) (new Node[array.length * 2]);
        for (Node<K, V> node : oldArray) {
            while (node != null) {
                Node<K, V> next = node.next;
                int index = getIndex(node.key);
                node.next = array[index];
                array[index] = node;
                node = next;
            }
        }
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        Node<K, V> pre = null;
        while (node != null) {
            if (equalsKey(node.key, key)) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    array[index] = node.next;
                }
                size--;
                return node.value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }


}

