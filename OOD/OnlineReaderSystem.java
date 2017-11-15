// 5. Design the data structures for an online book reader system.

// Since the problem doesn't describe much about the functionality, let's assume we want
// to design a basic online reading system which provides the following functionality:

// • User membership creation and extension.
// • Searching the database of books.
// • Reading a book.
// • Only one active user at a time
// • Only one active book by this user.

// To implement these operations we may require many other functions, like get, set,
// update, and so on. The objects required would likely include User, Book, and Library.

// The class OnlineReaderSystem represents the body of our program. We would
// implement the class such that it stores information about all the books, deals with user
// management, and refreshes the display, but that would make this class rather hefty.
// Instead, we've chosen to tear off these components into Library, UserManager, and
// Display classes.

public class OnlineReaderSystem {

	private Library library;

	private UserManager userManager;

	private Display display;

	private Book activeBook;

	private User activeUser;

	public OnlineReaderSystem() {

		userManager = new UserManager();

		library = new Library();

		display = new Display();

	}

	public Library getLibrary() { return library; }

	public UserManager getUserManager() { return userManager; }

	public Display getDisplay() { return display; }

	public Book getActiveBook() { return activeBook; }

	public void setActiveBook(Book book) {

		activeBook = book;

		display. displayBook(book);

	}

	public User getActiveUserQ { return activeUser; }

	public void setActiveUser(User user) {

		activeUser = user;

		display. displayUser( user);

	}

}

// We then implement separate classes to handle the user manager, the library, and the
// display components.

public class Library {

	private Hashtable<Integer, Book> books;

	public Book addBook(int id, String details) {

		if (books. containsKey(id)) {

			return null;

		}

		Book book = new Book(id, details);

		books. put(id, book);

		return book;

	}

	public boolean remove(Book b) { return remove(b.getID()); }

	public boolean remove(int id) {

		if (!books. containsKey(id)) {

			return false;

		}

		books. remove (id);

		return true;

	}

	public Book find (int id) {

		return books. get (id);

	}

}

public class UserManager {

	private Hashtable< Integer, User> users;

	public User addUser(int id, String details, int accountType) {

		if (users. containsKey(id)) {

			return null;

		}

		User user = new User(id, details, accountType);

		users.put(id, user);

		return user;

	}

	public boolean remove(User u) {

		return remove(u.getlD());

	}

	public boolean remove(int id) {

		if (! users. containsKey(id)) {

			return false;

		}

		users. remove(ld);

		return true;

	}

	public User find(int id) {

		return users.get(id);

	}

}

public class Display {

	private Book activeBook;

	private User activeUser;

	private int pageNumber = 0;

	public void displayUser(User user) {

		activeUser = user;

		refreshUsername();

	}

	public void displayBook(Book book) {

		pageNumber = 0;

		activeBook = book;

		refreshTitle();

		refreshDetails();

		refreshPage();

	}

	public void turnPageForward() {

		pageNumber++;

		refreshPage();

	}

	public void turnPageBackward() {

		pageNumber- -;

		refreshPage();

	}

	public void refreshUsername() { /* updates username display */ }

	public void refreshTitle() { /* updates title display */ }

	public void refreshDetails() { /* updates details display */ }

	public void refreshPage() { /* updated page display */ }

}

// The classes for User and Book simply hold data and provide little true functionality.

public class Book {

	private int bookld;

	private String details;

	public Book(int id, String det) {

		bookld = id;

		details = det;

	}

	public int getlD() { return bookId; }

	public void setID(lnt id) { bookld = id; }

	public String getDetails() { return details; }

	public void setDetails(String d) { details = d; }

}

public class User {

	private int userld;

	private String details;

	private int accountType;

	public void renewMembership() { }

	public User(int id, String details, int accountType) {

		userld = id;

		this.details = details;

		this.accountType = accountType;

	}

	/* Getters and setters */

	public int getlD() { return userld; }

	public void setID(int id) { userId = id; }

	public String getDetails() {

		return details;

	}

	public void setDetails(String details) {

		this.details = details;

	}

	public int getAccountType() { return accountType; }

	public void setAccountType(int t) { accountType = t; }

}

// The decision to tear off user management, library, and display into their own classes,
// when this functionality could have been in the general OnlineReaderSystem class, is an
// interesting one. On a very small system, making this decision could make the system
// overly complex. However, as the system grows, and more and more functionality gets
// added to OnlineReaderSystem, breaking off such components prevents this main class
// from getting overwhelmingly lengthy.
