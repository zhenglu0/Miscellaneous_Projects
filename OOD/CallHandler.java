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
