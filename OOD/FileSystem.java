// 9. Explain the data structures and algorithms that you would use to design an in-
// memory file system. Illustrate with an example in code where possible.

// If we think through the components of a file system,
// we can tackle this problem just like any other object-oriented design question.
// A file system, in its most simplistic version, consists of Files and Directories. Each
// Directory contains a set of Files and Directories. Since Files and Directories share so
// many characteristics, we've implemented them such that they inherit from the same
// class, Entry.

public abstract class Entry {

	protected Directory parent;

	protected long created;

	protected long lastUpdated;

	protected long lastAccessed;

	protected String name;

	public Entry(String n, Directory p) {

		name = n;

		parent = p;

		created = System.currentTimeMillis();

		lastUpdated = System.currentTimeMillis();

		lastAccessed = System.currentTimeMillis();

	}

	public boolean delete() {

		if (parent == null) return false;

		return parent.deleteEntry(this);

	}

	public abstract int size();

	public String getFullPath() {

		if (parent == null) return name;

		else return parent.getFullPath() + "/" + name;

	}

	/* Getters and setters. */

	public long getCreationTime() { return created; }

	public long getLastUpdatedTime() { return lastUpdated; }

	public long getLastAccessedTime() { return lastAccessed; }

	public void changeName(String n) { name = n; }

	public String getName() { return name; }

}

public class File extends Entry {

	private String content;

	private int size;

	public File(String n, Directory p, int sz) {

		super(n, p);

		size = sz;

	}

	public int size() {return size; }

	public String getContents() { return content; }

	public void setContents(String c) { content = c; }

}

public class Directory extends Entry {

	protected ArrayList<Entry> contents;

	public Directory(String n, Directory p) {

		super(n, p);

		contents = new ArrayList<Entry>();

	}

	public int size() {

		int size = 0;

		for (Entry e : contents) {

			size += e.size();

		}

		return size;

	}

	public int numberOfFiles() {

		int count = 0;

		for (Entry e : contents) {

			if (e instanceof Directory) {

				count++; // Directory counts as a file

				Directory d = (Directory) e;

				count += d.numberOfFiles();

			} else if (e instanceof File) {

				count++;

			}

		}

		return count;

	}

	public boolean deleteEntry(Entry entry) {

		return contents.remove(entry);

	}

	public void addEntry(Entry entry) {

		contents.add(entry);

	}

	protected ArrayList<Entry> getContents() { return content; }

}

// Alternatively, we could have implemented Directory such that it contains separate lists
// for files and subdirectories. This makes the numberOfFiles() method a bit cleaner, since
// 	it doesn't need to use the instanceof operator, but it does prohibit us from cleanly sorting
// files and directories by dates or names.
