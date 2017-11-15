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
