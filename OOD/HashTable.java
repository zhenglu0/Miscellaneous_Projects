// 10. Design and implement a hash table which uses chaining (linked lists) to handle
// collisions.

// Suppose we are implementing a hash table that looks like Hash<K, V>. That is, the
// hash table maps from objects of type K to objects of type V.

// At we might think our data structure would look something like this:

// public class Hash<K, V> {

// 	LinkedList<V>[ ] items;

// 	public void put(K key, V value) { ... }

// 	public V get(K key) { . . . }

// }

// Note that items is an array of linked lists, where items [i] is a linked list of all objects with
// keys that map to index i (that is, all the objects that collided at i).

// This would seem to work until we think more deeply about collisions.

// Suppose we have a very simple hash function that uses the string length.

// public int hashCodeOfKey(K key) {

// 	return key.toString().length() % items.length;

// }

// The keys jim and bob will map to the same index in the array, even though they are
// different keys. We need to search through the linked list to find the actual object that
// corresponds to these keys. But how would we do that? All we've stored in the linked list
// is the value, not the original key.

// This is why we need to store both the value and the original key.

// One way to do that is to create another object called Cell which pairs keys and values.
// With this implementation, our linked list is of type Cell.

// The code below uses this implementation.

public class Hash<K, V> {

	private final int MAX_SIZE = 10;

	LinkedList<Cell<K, V>>[ ] items;

	public Hash() {

		items = (LinkedList<Cell<K, V>>[ ]) new LinkedList[MAX_SIZE];

	}

	/* Really, really stupid hash. */

	public int hashCodeOfKey(K key) {

		return key.toString().length() % items.length;

	}

	public void put(K key, V value) {

		int x = hashCodeOfKey(key);

		if (items[x] == null) {

			items[x] = new LinkedList<Cell<K, V>>();

		}

		LinkedList<Cell<K, V>> collided = items[x];

		/* Look for items with same key and replace if found */

		for (Cell<K, V> c : collided) {

			if (c.equivalent(key)) {

				collided.remove( c ) ;

				break;

			}

		}

		Cell<K, V> cell = new Cell<K, V>(key, value);

		collided.add(cell);

	}

	public V get(K key) {

		int x = hashCodedOfKey(key);

		if (items[x] == null) {

			return null;

		}

		LinkedList<Cell<K, V>> collided = items [x];

		for (Cell<K, V> c : collided) {

			if (c.equivalent(key)) {

				return c.getValue();

			}

		}

		return null;

	}

}

// The Cell class pairs the data value and its key. This will allow us to search through the
// linked list (created by "colliding," but different, keys) and find the object with the exact
// key value.

public class Cell<K, V> {

	private K key;

	private V value;

	public Cell(K k, V v) {

		key = k;

		value = v;

	}

	public boolean equivalent(Cell<K, V> c) {

		return equivalence.getKey();

	}

	public boolean equivalent(K k) {

		return key.equals(k);

	}

	public K getKey() { return key; }

	public V getValue() { return value; }

}

// Another common implementation for a hash table is to use a binary search tree as the
// underlying data structure. Retrieving an element will no longer be 0(1) (although,
// technically it's not 0(1) if there are many collisions), but it prevents us from creating an
// unnecessarily large array to hold items.
