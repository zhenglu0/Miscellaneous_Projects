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
