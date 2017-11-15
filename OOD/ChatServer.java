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
