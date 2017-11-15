// Object-Oriented Design
// Concepts and Algorithms


// 1. Design the data structures for a generic deck of cards. Explain how you would
// subclass the data structures to implement blackjack.

// First, we need to recognize that a "generic" deck of cards can mean many things.
// Generic could mean a standard deck of cards that can play a poker-like game, or it
// could even stretch to Uno or Baseball cards. It is important to ask what
// she means by generic.
// Let's assume that the deck is a standard 52-card set, like
// you might see used in a blackjack or poker game. If so, the design might look like this:

public enum Suit {

	Club (0), Diamond (1), Heart (2), Spade (3);

	private int value;

	private Suit(int v) { value = v; }

	public int getValue() { return value; }

	public static Suit getSuitFromValue(int value) { … }

}

public class Deck <T extends Card> {

	private ArrayList<T> cards; // all cards, dealt or not

	private int dealtlndex = 0; // marks first undealt card

	public void setDeckOfCards (ArrayList<T> deckOfCards) { ... }

	public void shuffle() { ... }

	public int remainingCards() {

		return cards.size() - dealtlndex;

	}

	public T[] dealHand(int number) { ... }

	public T dealCard() { ... }

}

public abstract class Card {

	private boolean available = true;

	/* number or face that's on card - a number 2 through 10, or 11
	 * for Jack, 12 for Queen, 13 for King, or 1 for Ace */

	protected int faceValue;

	protected Suit suit;

	public Card(int c, Suit s) {

	faceValue = c;

	suit = s;

    }

	public abstract int value();

	public Suit suit() { return suit; }

	/* Checks if the card is available to be given out to someone */

	public boolean isAvailableQ { return available; }

	public void markUnavailable() { available = false; }

	public void markAvailable() { available = true; }

}

public class Hand <T extends Card> {

	protected ArrayList<T> cards = new ArrayList<T>();

	public int score() {

		int score = 0;

		for (T card : cards) {

			score += card.value();

		}

		return score;

	}

	public void addCard(T card) {

		cards.add(card);

	}

}

// In the above code, we have implemented Deck with generics but restricted the type of T
// to Card. We have also implemented Card as an abstract class, since methods like
// value() don't make much sense without a specific game attached to them. (You could
// make a compelling argument that they should be implemented anyway, by defaulting to
// standard poker rules.)
// Now, let's say we're building a blackjack game, so we need to know the value of the
// cards. Face cards are 10 and an ace is 11 (most of the time, but that's the job of the
// Hand class, not the following class).

public class BlackJackHand extends Hand<BlackJackCard> {

	/* There are multiple possible scores for a blackjack hand,
	 * since aces have multiple values. Return the highest possible
	 * score that's under 21, or the lowest score that's over. */

	public int score() {

		ArrayList<Integer> scores = possibleScores();

		int maxUnder = Integer.MIN_VALUE;

		int minOver = Integer.MAX_VALUE;

		for (int score : scores) {

			if (score > 21 && score < minOver) {

				minOver = score;

			} else if (score <= 21 && score > maxUnder) {

				maxUnder = score;

			}

		}

		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;

	}

	/* return a list of all possible scores this hand could have
	 * (evaluating each ace as both 1 and 11 */

	private ArrayList<Integer> possibleScores() { … }

	public boolean busted() { return score() > 21; }

	public boolean is21() { return score() == 21; }

	public boolean isBlackJack() { ... }

}

public class BlackJackCard extends Card {

	public BlackDackCard(int c, Suit s) { super(c, s); }

	public int value() {

		if (isAce()) return 1;

		else if (faceValue >= 11 && faceValue <= 13) return 10;

		else return faceValue;

	}

	public int minValue() {

		if (isAce()) return 1;

		else return value();

	}

	public int maxValue() {

		if (IsAceQ) return 11;

		else return value();

	}

	public boolean isAce() {

		return faceValue == 1;

	}

	public boolean isFaceCard() {

		return faceValue >= 11 && faceValue <= 13;

	}

}

// This is just one way of handling aces. We could, alternatively, create a class of type Ace
// that extends BlackJackCard.


// 2. Imagine you have a call center with three levels of employees: respondent,
// manager and director. An incoming telephone call must be first allocated to a
// respondent who is free. If the respondent can't handle the call, he or she must
// escalate the call to a manager. If the manager is not free or not able to handle it,
// then the call should be escalated to a director. Design the classes and data
// structures for this problem. Implement a method dispatchCaLL() which assigns a
// call to the first available employee.

// All three ranks of employees have different work to be done, so those specific functions
// are profile specific. We should keep these things within their respective class.

// There are a few things which are common to them, like address, name, job title, and
// age. These things can be kept in one class and can be extended or inherited by others.

// Finally, there should be one CallHandler class which would route the calls to the correct
// person.

// CallHandler is implemented as a singleton class. It represents the body of the program,
// and all calls are funneled first through it.

public class CallHandler {

	private static CallHandler instance;

	/* 3 levels of employees: respondents, managers, directors. */

	private final int LEVELS = 3;

	/* Initialize 10 respondents, 4 managers, and 2 directors. */

	private final int NUM_RESPONDENTS = 10;

	private final int NUM_MANAGERS = 4;

	private final int NUM_DIRECTORS = 2;

	/* List of employees, by level.
	 * employeeLevels[0] = respondents
	 * employeeLevels[1] = managers
	 * employeeLevels[2] = directors
	 */

	List<List<Employee>> employeeLevels;

	/* queues for each call's rank */

	List<List<Call>> callQueues;

	protected CallHandler() { ... }

	/* Get instance of singleton class. */

	public static CallHandler getlnstance() {

		if (instance == null) instance = new CallHandler();

		return instance;

	}

	/* Gets the first available employee who can handle this call. */

	public Employee getHandlerForCall(Call call) { ... }

	/* Routes the call to an available employee, or saves in a queue
	 * if no employee available. */

	public void dispatchCall(Caller caller) {

		Call call = new Call(caller);

		dispatchCall(call);

	}

	/* Routes the call to an available employee, or saves in a queue
	 * if no employee available. */

	public void dispatchCall(Call call) {

		/* Try to route the call to an employee with minimal rank. */

		Employee emp = getHandlerForCall(call);

		if (emp != null) {

			emp.receiveCall(call);

			call.setHandler (emp);

		} else {

			/* Place the call into corresponding call queue according
			 * to its rank. */

			call.reply ("Please wait for free employee to reply");

			callQueues.get (call.getRank().getValue()).add(call);

		}
	}

	/* An employee got free. Look for a waiting call that emp. can
	 * serve. Return true if we assigned a call, false otherwise. */

	public boolean assignCall(Employee emp) { ... }

}

// Call represents a call from a user. A call has a minimum rank and is assigned to the first
// employee who can handle it.

public class Call {

	/* Minimal rank of employee who can handle this call */

	private Rank rank;

	/* Person who is calling. */

	private Caller caller;

	/* Employee who is handling call. */

	private Employee handler;

	public Call(Caller c) {

		rank = Rank.Responder;

		caller = c;

	}

	/* Set employee who is handling call. */

	public void setHandler(Employee e) { handler = e; }

	public void reply(String message) { ... }

	public Rank getRank() { return rank; }

	public void setRank(Rank r) { rank = r; }

	public Rank incrementRank() { ... }

	public void disconnect() { ... }

}

// Employee is a super class for the Director, Manager, and Respondent classes. It is
// implemented as an abstract class since there should be no reason to instantiate an
// Employee type directly.

abstract class Employee {

	private Call currentCall = null;

	protected Rank rank;

	public Employee() { }

	/* Start the conversation */

	public void receiveCall(Call call) { ... }

	/* the issue is resolved, finish the call */

	public void callCompleted() { ... }

	/* The issue has not been resolved. Escalate the call, and
	 * assign a new call to the employee. */

	public void escalateAndReassign() { ... }


	// Assign a new call to an employee, if the employee is free.

	public boolean assignNewCall() { ... }

	/* Returns whether or not the employee is free. */

	public boolean isFree() { return currentCall == null; }

	public Rank getRank() { return rank; }

}

// The Respondent, Director, and Manager classes are now just simple extensions of the
// Employee class.

class Director extends Employee {

	public Director() {

		rank = Rank.Director;

	}

}

class Manager extends Employee {

	public Manager() {

		rank = Rank.Manager;

	}

}

class Respondent extends Employee {

	public Respondent() {

		rank = Rank.Responder;

	}

}

// This is just one way of designing this problem. Note that there are many other ways that
// are equally good.


// 3. Design a musical jukebox using object-oriented principles.

// In any object-oriented design question, you first want to start off with asking
// some questions to clarify design constraints. Is this jukebox playing CD?
// Records? MP3s? Is it a simulation on a computer, or is it supposed to represent a
// physical jukebox? Does it take money, or is it free? And if it takes money, which
// currency? And does it deliver change?

// We'll make some assumptions. We'll assume that the jukebox is a computer
// simulation that closely mirrors physical jukeboxes, and we'll assume that it's free.

// Now that we have that out of the way, we'll outline the basic system components.

// • Jukebox
// • CD
// • Song
// • Artist
// • Playlist
// • Display (displays details on the screen)

// Now, let’s break this down further and think about the possible actions.

// • Playlist creation (includes add, delete, and shuffle)
// • CD selector
// • Song selector
// • Queuing up a song
// • Get next song from playlist

// A user also can be introduced:

// • Adding
// • Deleting
// • Credit information

// Each of the main system components translates roughly to an object, and each action
// translates to a method. Let's walk through one potential design.

// The Jukebox class represents the body of the problem. Many of the interactions
// between the components of the system, or between the system and the user, are
// channeled through here.

public class Jukebox {

	private CDPlayer cdPlayer;

	private User user;

	private Set<CD> cdCollection;

	private SongSelector ts;

	public Jukebox(CDPlayer cdPlayer, User user,

		Set<CD> cdCollection, SongSelector ts) {

		…

	}

	public Song getCurrentSong() {

		return ts.getCurrentSong();

	}

	public void setUser (User u) {

		this. user = u;

	}

}

// Like a real CD player, the CDPlayer class supports storing just one CD at a time. The
// CDs that are not in play are stored in the jukebox.

class CDPlayer {

	private Playlist p;

	private CD c;

	/* Constructors. */

	public CDPlayer(CD c, Playlist p) { ... }

	public CDPlayer(Playlist p) { this.p = p; }

	public CDPlayer(CD c) { this.c = c; }

	/* Play song */

	public void playSong(Song s) { ... }

	/* Getters and setters */

	public Playlist getPlaylist() { return p; }

	public void setPlaylist(Playlist p) { this.p = p; }

	public CD getCD() { return c; }

	public void setCD(CD c) { this.c = c; }

}

// The Playlist manages the current and next songs to play. It is essentially a wrapper
// class for a queue and offers some additional methods for convenience.

public class Playlist {

	private Song song;

	private Queue<Song> queue;

	public Playlist(Song song, Queue<Song> queue) {

		…

	}

	public Song getNextSToPlayQ {

		return queue. peek();

	}

	public void queueUpSong(Song s) {

		queue. add(s);

	}

}

// The classes for CD, Song, and User are all fairly straightforward. They consist mainly of
// member variables and getters and setters.

public class CD {

	/* data for id, artist, songs, etc */

}

public class Song {

	/* data for id, CD (could be null), title, length, etc */

}

public class User {

	private String name;

	public String getName() { return name; }

	public void setName(String name) { this.name }

	public long getlD() { return ID; }

	public void setID(long ID) { ID = iD; }

	private long ID;

	public User(String name, long iD) { … }

	public User getUser() { return this; }

	public static User addUser(String name, long iD) { ... }

}


// 4. Design a parking lot using object-oriented principles.

// The wording of this question is vague. This
// requires you to have a conversation about what types of vehicles it
// can support, whether the parking lot has multiple levels, and so on.

// For our purposes right now, we'll make the following assumptions. We made these
// specific assumptions to add a bit of complexity to the problem without adding too much.
// If you made different assumptions, that's totally fine.

// • The parking lot has multiple levels. Each level has multiple rows of spots.
// • The parking lot can park motorcycles, cars, and buses.
// • The parking lot has motorcycle spots, compact spots, and large spots.
// • A motorcycle can park in any spot.
// • A car can park in either a single compact spot or a single large spot.
// • A bus can park in five large spots that are consecutive and within the same row. It
// cannot park in small spots.

// In the below implementation, we have created an abstract class Vehicle, from which
// Car, Bus, and Motorcycle inherit. To handle the different parking spot sizes, we have
// just one class ParkingSpot which has a member variable indicating the size.

public enum VehicleSize { Motorcycle, Compact, Large }

public abstract class Vehicle {

	protected ArrayList<ParkingSpot> parkingSpots =  new ArrayList<ParkingSpot>();

	protected String licensePlate;

	protected int spotsNeeded;

	protected VehicleSize size;

	public int getSpotsNeeded() { return spotsNeeded; }

	public VehicleSize getSize() { return size; }

	/* Park vehicle in this spot (among others, potentially) */

	public void parkInSpot(ParkingSpot s) { parkingSpots.add(s); }

	/* Remove car from spot, and notify spot that it's gone */

	public void clearSpots() { ... }

	/* Checks if the spot is big enough for the vehicle (and is
     * available). This compares the SIZE only. It does not check if it
     * has enough spots. */

	public abstract boolean canFitInSpot(ParkingSpot spot);

}

public class Bus extends Vehicle {

	public Bus() {

		spotsNeeded = 5;

		size = VehicleSize.Large;

	}

	/* Checks if the spot is a Large. Doesn't check num of spots */

	public boolean canFitInSpot(ParkingSpot spot) { ... }

}

public class Car extends Vehicle {

	public Car() {

		spotsNeeded = 1;

		size = VehicleSize.Compact;

	}

	/* Checks if the spot is a Compact or a Large. */

	public boolean canFitlnSpot(ParkingSpot spot) { ... }

}

public class Motorcycle extends Vehicle {

	public Motorcycle() {

		spotsNeeded = 1;

		size = VehicleSize.Motorcycle;

	}

	public boolean canFitInSpot(ParkingSpot spot) { … }

}

// The ParkingLot class is essentially a wrapper class for an array of Levels. By
// implementing it this way, we are able to separate out logic that deals with actually
// finding free spots and parking cars out from the broader actions of the ParkingLot. If we
// didn’t do it this way, we would need to hold parking spots in some sort of double array
// (or hash table which maps from a level number to the list of spots). It's cleaner to just
// separate ParkingLot from Level.

public class ParkingLot {

	private Level[ ] levels;

	private final int NUM_LEVELS = 5;

	public ParkingLot() { ... }

	/* Park the vehicle in a spot (or multiple spots).

	* Return false if failed. */

	public boolean parkVehicle(Vehicle vehicle) { ... }

}

/* Represents a level in a parking garage */

public class Level {

	private int floor;

	private ParkingSpot[ ] spots;

	private int availableSpots = 0; // number of free spots

	private static final int SPOTS_PER_ROW = 10;

	public Level(int flr, int numberSpots) { ... }

	public int availableSpots() { return availableSpots; }

	/* Find a place to park this vehicle. Return false if failed. */

	public boolean parkVehicle(Vehicle vehicle) { ... }

	/* Park a vehicle starting at the spot spotNumber, and
	 * continuing until vehicle.spotsNeeded. */

	private boolean parkStartingAtSpot(int num, Vehicle v) { ... }

	/* Find a spot to park this vehicle. Return index of spot, or -1
	 * on failure. */

	private int findAvailableSpots(Vehicle vehicle) { ... }

	/* When a car was removed from the spot, increment
	 * availableSpots */

	public void spotFreed() { availableSpots++; }

}

// The parkingSpot is implemented by having just a variable which represents the size of
// the spot. We could have implemented this by having classes for LargeSpot,
// CompactSpot, and MotorcycleSpot which inherit from ParkingSpot, but this is probably
// overkill. The spots probably do not have different behaviors, other than their sizes.

public class ParkingSpot {

	private Vehicle vehicle;

	private VehicleSize spotSize;

	private int row;

	private int spotNumber;

	private Level level;

	public ParkingSpot(Level Ivl, int r, int n, VehicleSize s) {...}

	public boolean isAvailable() { return vehicle == null; }

	/* Check if the spot is big enough and is available */

	public boolean canFitVehicle(Vehicle vehicle) { ... }

	/* Park vehicle in this spot. */

	public boolean park(Vehicle v) { ... }

	public int getRow() { return row; }

	public int getSpotNumber() { return spotNumber; }

	/* Remove vehicle from spot, and notify level that a new spot is
	 * available */

	public void removeVehicle() { ... }

}


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


// 6. Implement a jigsaw puzzle. Design the data structures and explain an algorithm
// to solve the puzzle. You can assume that you have a fitsWith method which,
// when passed two puzzle pieces, returns true if the two pieces belong together.

// We will assume that we have a traditional, simple jigsaw puzzle. The puzzle is grid-like,
// with rows and columns. Each piece is located in a single row and column and has four
// edges. Each edge comes in one of three types: inner, outer, and flat. A corner piece, for
// example, will have two flat edges and two other edges, which could be inner or outer.

// As we solve the jigsaw puzzle (manually or algorithmically), we'll need to store the
// position of each piece. We could think about the position as absolute or relative:

// • Absolute Position: "This piece is located at position (12, 23)." Absolute position
// would belong to the Piece class itself and would include an orientation as well.
// • Relative Position: “I don’t know where this piece is actually located, but I know that it
// is next to this other piece.” The relative position would belong to the Edge class.

// For our solution, we will use only the relative position, by adjoining edges to neighboring
// edges.

// A potential object-oriented design looks like the following:

public class Edge {

	enum Type { inner, outer, flat }

	Piece parent;

	Type type;

	int index; // Index into Piece.edges

	Edge attached_to; // Relative position

	/* See Algorithm section. Returns true if the two pieces
	 * should be attached to each other. */

	boolean fitswith(Edge edge) { ... };

}

public class Piece {

	Edge[ ] edges;

	boolean isCorner() { ... }

}

public class Puzzle {

	Piece[ ] pieces; /* Remaining pieces left to put away. */

	Piece[ ][ ] solution;

	/* See algorithm section. */

	Edge[ ] inners, outers, flats;

	Piece[ ] corners;

	/* See Algorithm section. */

	void sort() { ... }

	void solve() { ... }

}

// Algorithm to Solve the Puzzle

// We will sketch this algorithm using a mix of pseudocode and real code.

// Just as a kid might in solving a puzzle, we'll start with the easiest pieces first: the
// corners and edges. We can easily search through all the pieces to find just the edges.
// While we're at it though, it probably makes sense to group all the pieces by their edge
// types.

public void sort() {

	for each Piece p in pieces {

		if (P has two flat edges) then add p to corners

			for each edge in p.edges {

				if edge is inner then add to inners

				if edge is outer then add to outers

			}

		}
	}
}

// We now have a quicker way to zero in on potential matches for any given edge. We
// then go through the puzzle, line by line, to match pieces.

// The solve method, implemented below, operates by picking an arbitrary start with. It
// then finds an open edge on the corner and tries to match it to an open piece. When it
// finds a match, it does the following:

// . Attaches the edge.
// . Removes the edge from the list of open edges.
// . Finds the next open edge.

// The next open edge is defined to be the one directly opposite the current edge, if it is
// available. If it is not available, then the next edge can be any other edge. This will cause
// the puzzle to be solved in a spiral-like fashion, from the outside to the inside.

// The spiral comes from the fact that the algorithm always moves in a straight line,
// whenever possible. When we reach the end of the first edge, the algorithm moves to the
// only available edge on that corner piece—a 90-degree turn. It continues to take 90-
// degree turns at the end of each side until the entire outer edge of the puzzle is
// completed. When that last edge piece is in place, that piece only has one exposed edge
// remaining, which is again a 90-degree turn. The algorithm repeats itself for subsequent
// rings around the puzzle, until finally all the pieces are in place.

// This algorithm is implemented below with Java-like pseudocode.

public void solve() {

	/* Pick any corner to start with */

	Edge currentEdge = getExposedEdge(corner[0]);

	/* Loop will iterate in a spiral like fashion until the puzzle
	 * is full. */

	while (currentEdge != null) {

		/* Match with opposite edges. Inners with outers, etc. */

		Edge[ ] opposites = currentEdge.type == inner ?

		outers : inners;

		for each Edge fittingEdge in opposites {

			if (currentEdge.fitsWith(fittingEdge)) {

				attachEdges(currentEdge, fittingEdge); //attach edge

				removeFromlist(currentEdge);

				removeFromList(fittingEdge);

				/* get next edge */

				currentEdge = nextExposedEdge(fittingEdge);

				break; // Break out of inner loop. Continue in outer.

			}

		}

	}

}

private void removeFromList(Edge edge) {

	(edge.type == flat) return;

	Edge[ ] array = currentEdge.type == inner ? inners : outers;

	array.remove(edge);

}

/* Return the opposite edge if possible. Else, return any exposed
 * edge. */

private Edge nextExposedEdge(Edge edge) {

	int next_index = (edge.index + 2) % 4; // Opposite edge

	Edge next_edge = edge.parent.edges[next_index];

	if isExposed(next_edge) {

		return next_edge;

	}

	return getExposedEdge(edge.parent);

}

private Edge attachEdges(Edge e1, Edge e2) {

	el.attached_to = e2;

	e2.attached_to = e1;

}

private Edge isExposed(Edge e1) {

	return edge.type != flat && edge.attached_to == null;

}

private Edge getExposedEdge(Piece p) {

	for each Edge edge in p.edges {

		if (isExposed(edge)) {

			return edge;

		}

	}

	return null;
}

// For simplicity, we’re represented inners and outers as an Edge array. This is actually
// not a great choice, since we need to add and removed elements from it frequently. If we
// were writing a real code, we would probably want to implement these variables as
// linked lists.


// 7. Explain how you would design a chat server. In particular, provide details about
// the various backend components, classes, and methods. What would be the
// hardest problems to solve?

// For our purposes, we'll focus on the core user management and conversation aspects:
// adding a user, creating a conversation, updating one's status, and so on. In the interest
// of time and space, we will not go into the networking aspects of the problem, or how the
// data actually gets pushed out to the clients.

// We will assume that "friending" is mutual; I am only your contact if you are mine. Our
// chat system will support both group chat and one-on-one (private) chats. We will not
// worry about voice chat, video chat, or file transfer.

// What specific actions does it need to support?

// This is also something to discuss, but here are some ideas:

// • Signing online and offline.
// • Add requests (sending, accepting, and rejecting).
// • Updating a status message.

// • Creating private and group chats.
// • Adding new messages to private and group chats.

// This is just a partial list. If you have more time, you can add more actions.

// What can we learn about these requirements?

// We must have a concept of users, add request status, online status, and messages.

// What are the core components of the system?

// The system would likely consist of a database, a set of clients, and a set of servers. We
// won't include these parts in our object-oriented design, but we can discuss the overall
// view of the system.

// The database will be used for more permanent storage, such as the user list or chat
// archives. A SQL database is a good bet, or, if we need more scalability, we could
// potentially use BigTable or a similar system.

// For communication between the client and servers, using XML will work well. Although
// it’s not the most compressed format (and you should point this out),
// it’s nice because it's easy for both computers and humans to read. Using XML will make
// your debugging efforts easier and that matters a lot.

// The server will consist of a set of machines. Data will be divided up across machines,
// requiring us to potentially hop from machine to machine. When possible, we will try to
// replicate some data across machines to minimize the lookups. One major design
// constraint here is to prevent having a single point of failure. For instance, if one machine
// controlled all the user sign-ins, then we'd cut off millions of users potentially if a single
// machine lost network connectivity.

// What are the key objects and methods?

// The key objects of the system will be a concept of users, conversations, and status
// messages. We've implemented a User-Management class. If we were looking more at
// the networking aspects of the problem, or a different component, we might have instead
// dived into those objects.

/* UserManager serves as a central place for core user actions. */

public class UserManager {

	private static UserManager instance;

	/* maps from a user id to a user */

	private HashMap<Integer, User> usersByld;

	/* maps from an account name to a user */

	private HashMap<String, User> usersByAccountName;

	/* maps from the user id to an online user */

	private HashMap<Integer, User> onlineUsers;

	public static UserManager getlnstance() {

		if (instance == null) instance = new UserManager();

		return instance;

	}

	public void addUser(User fromUser, String toAccountName) { ... }

	public void approveAddRequest(AddRequest req) { ... }

	public void rejectAddRequest(AddRequest req) { ... }

	public void userSignedOn(String accountName) { ... }

	public void userSignedOff(String accountName) { ... }

}

// The method receivedAddRequest, in the User class, notifies User B that User A has
// requested to add him. User B approves or rejects the request (via UserManager.
// 	approvedAddRequest or rejectAddRequest), and the UserManager takes care of adding
// the users to each other's contact lists.

// The method sentAddRequest in the User class is called by User-Manager to add an
// AddRequest to User A's list of requests. So the flow is:

// . User A clicks "add user" on the client, and it gets sent to the server.
// . User A calls requestAddUser(User B).
// . This method calls UserManager. addUser.
// . UserManager calls both User A.sentAddRequest and

// User B.receivedAddRequest.

// Again, this is just one way of designing these interactions. It is not the only way or even
// the only "good" way.

public class User {

	private int id;

	private UserStatus status null;

	/* maps from the other participant's user id to the chat */

	private HashMap<Integer, PrlvateChat> privateChats;

	/* maps from the group chat id to the group chat */

	private HashMap<Integer, GroupChat> groupChats;

	/* maps from the other person's user id to the add request */

	private HashMap<Integer, AddRequest> receivedAddRequests;

	/* maps from the other person's user id to the add request */

	private HashMap<Integer, AddRequest> sentAddRequests;

	/* maps from the user id to the add request */

	private HashMap<Integer, User> contacts;

	private String accountName;

	private String fullName;

	public User(int id, String accountName, String fullName) { … }

	public boolean sendMessageToUser(User to, String content){ … }

	public boolean sendMessageToGroupChat(int id, String cnt){ … }

	public void setStatus(UserStatus status) { ... }

	public UserStatus getStatus() { ... }

	public boolean addContact(User user) { ... }

	public void receivedAddRequest(AddRequest req) { … }

	public void sentAddRequest (AddRequest req) { ... }

	public void removeAddRequest(AddRequest req) { ... }

	public void requestAddUser(String accountName) { … }

	public void addConversation(PrivateChat conversation) { … }

	public void addConversation(GroupChat conversation) { … }

	public int getld() { ... }

	public String getAccountName() { ... }

	public String getFullName() { ... }

}

// The Conversation class is implemented as an abstract class, since all Conversations
// must be either a GroupChat or a PrivateChat, and since these two classes each have
// their own functionality.

public abstract class Conversation {

	protected ArrayList<User> participants;

	protected int id;

	protected ArrayList<Message> messages;

	public ArrayList<Message> getMessages() { … }

	public boolean addMessage(Message m) { … }

	public int getld() { ... }

}

public class GroupChat extends Conversation {

	public void removeParticipant(User user) { ... }

	public void addParticipant(User user) { ... }

}

public class PrivateChat extends Conversation {

	public PrivateChat(User user1, User user2) { ...

	public User getOtherParticipant(User primary) { … }

}

public class Message {

	private String content;

	private Date date;

	public Message(String content, Date date) { ... }

	public String getContent() { ... }

	public Date getDate() { … }

}

// AddRequest and UserStatus are simple classes with little functionality. Their main
// purpose is to group data that other classes will act upon.

public class AddRequest {

	private User fromUser;

	private user toUser;

	private Date date;

	Requeststatus status;

	public AddRequest(User from, User to, Date date) { … }

	public RequestStatus getStatus() { … }

	public User getFromUser() { ... }

	public User getToUser() { ... }

	public Date getDate() { ... }

}

public class UserStatus {

	private String message;

	private UserStatusType type;

	public UserStatus(UserStatusType type, String message) f

	public UserStatusType getStatusTypeQ { ... }

	public String getMessageQ { ... }

}

public enum UserStatusType {

	Offline, Away, Idle, Available, Busy

}

public enum RequestStatus {

	Unread, Read, Accepted, Rejected

}


// What problems would be the hardest to solve (or the most interesting)?

// The following questions may be interesting to discuss further.

// Q 1: How do we know if someone is online, really, really know?

// While we would like users to tell us when they sign off, we can't know for sure. A user's
// connection might have died, for example. To make sure that we know when a user has
// signed off, we might try regularly pinging the client to make sure it's still there.

// Q2: How do we deal with conflicting information?

// We have some information stored in the computer's memory and some in the database.
// What happens if they get out of sync? Which one is "right"?

// Q3: How do we make our server scale?

// While we designed out chat server without worrying—too much—about scalability, in
// real life this would be a concern. We'd need to split our data across many servers,
// which would increase our concern about out-of-sync data.

// Q4: How we do prevent denial of service attacks?

// Clients can push data to us—what if they try to DOS (denial of service) us? How do we
// prevent that?


// 8. Othello is played as follows: Each Othello piece is white on one side and black
// on the other. When a piece is surrounded by its opponents on both the left and
// right sides, or both the top and bottom, it is said to be captured and its color is
// flipped. On your turn, you must capture at least one of your opponent's pieces.
// The game ends when either user has no more valid moves. The win is assigned
// to the person with the most pieces.

// Let’s start with an example. Suppose we have the following moves in an Othello game:

// . Initialize the board with two black and two white pieces in the center. The black
// pieces are placed at the upper left hand and lower right hand corners.
// . Play a black piece at (row 6, column 4). This flips the piece at (row 5, column 4) from
// white to black.
// . Play a white piece at (row 4, column 3). This flips the piece at (row 4, column 4) from
// black to white.

// This sequence of moves leads to the board below.

// The core objects in Othello are probably the game, the board, the pieces (black or
// 	white), and the players. How do we represent these with elegant object-oriented
// design?

// Should BlackPiece and WhitePiece be classes?

// At first, we might think we want to have a BlackPiece class and a WhitePiece class,
// which inherit from an abstract Piece. However, this is probably not a great idea. Each
// piece may flip back and forth between colors frequently, so continuously destroying and
// creating what is really the same object is probably not wise. It may be better to just have
// a Piece class, with a flag in it representing the current color.

// Do we need separate Board and Game classes?

// Strictly speaking, it may not be necessary to have both a Game object and a Board
// object. Keeping the objects separate allows us to have a logical separation between the
// board (which contains just logic involving placing pieces) and the game (which involves
// 	times, game flow, etc.). However, the drawback is that we are adding extra layers to our
// program. A function may call out to a method in Game, only to have it immediately call
// Board. We have made the choice below to keep Game and Board separate.

// Who keeps score?

// We know we should probably have some sort of score keeping for the number of black
// and white pieces. But who should maintain this information? One could make a strong
// argument for either Game or Board maintaining this information, and possibly even for
// Piece (in static methods). We have implemented this with Board holding this
// information, since it can be logically grouped with the board. It is updated by Piece or
// Board calling colorChanged and colorAdded methods within Board.

// Should Game be a Singleton class?

// Implementing Game as a singleton class has the advantage of making it easy for
// anyone to call a method within Game, without having to pass around references to the
// Game object.

// Making Game a singleton though means that it can only be instantiated once. Can we
// make this assumption? You should discuss.

// One possible design for Othello is below.

public enum Direction {

	left, right, up, down

}

public enum Color {

	White, Black

}

public class Game {

	private Player[ ] players;

	private static Game instance;

	private Board board;

	private final int ROWS = 10;

	private final int COLUMNS = 10;

	private Game() {

		board = new Board(ROWS, COLUMNS);

		players = new Player[2];

		players[0] = new Player(Color.Black);

		players[1] = new Player(Color.White);

	}

	public static Game getlnstance() {

		if (instance == null) instance = new Game();

		return instance;

	}

	public Board getBoard() {

		return board;

	}

}

// The Board class manages the actual pieces themselves. It does not handle much of the
// game play, leaving that up to the Game class.

public class Board {

	private int blackCount = 0;

	private int whiteCount = 0;

	private Piece[ ][ ] board;

	public Board(int rows, int columns) {

		board = new Piece[rows][columns];

	}

	public void initialize() {

		/* initialize center black and white pieces */

	}

	/* Attempt to place a piece of color color at (row, column).
	 * Return true if we were successful. */

	public boolean placeColor(int row, int column, Color color) {

		…

    }

	/* flips pieces starting at (row, column) and proceeding in
	 * direction d. */

	private int flipSection(int row, int column, Color color,

		Direction d) { ... }

	public int getScoreForColor(Color c) {

		if (c == Color.Black) return blackCount;

		else return whiteCount;

	}

	/* Update board with additional newPieces pieces of color
     * newColor. Decrease score of opposite color. */

	public void updateScore(Color newColor, int newPieces) { ... }

}

// As described earlier, we implement the black and white pieces with the Piece class,
// which has a simple Color variable representing whether it is a black or white piece.

public class Piece {

	private Color color;

	public Piece(Color c) { color = c; }

	public void flip() {

		if (color == Color.Black) color = Color.White;

		else color = Color.Black;

	}

	public Color getColor() { return color; }

}

// The Player holds only a very limited amount of information. It does not even hold its own
// score, but it does have a method one can call to get the score PIayer.getScore() will call
// out to the GameManager to retrieve this value.

public class Player {

	private Color color;

	public Player(Color c) { color = c; }

	public int getScore() { ... }

	public boolean playPiece(int r, int c) {

		return Game.getlnstance().getBoard().placeColor(r, c, color);

	}

	public Color getColor() { return color; }

}

// Remember that in many problems, what you did is less important than why you did it.


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


// 10. Design and implement a hash table which uses chaining (linked lists) to handle
// collisions.

// Suppose we are implementing a hash table that looks like Hash<K, V>. That is, the
// hash table maps from objects of type K to objects of type V.

// At we might think our data structure would look something like this:

public class Hash<K, V> {

	LinkedList<V>[ ] items;

	public void put(K key, V value) { ... }

	public V get(K key) { . . . }

}

// Note that items is an array of linked lists, where items [i] is a linked list of all objects with
// keys that map to index i (that is, all the objects that collided at i).

// This would seem to work until we think more deeply about collisions.

// Suppose we have a very simple hash function that uses the string length.

public int hashCodeOfKey(K key) {

	return key.toString() .length() % items.length;

}

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
