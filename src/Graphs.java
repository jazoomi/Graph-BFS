import java.util.HashSet;
import java.util.Set;

// Added imports
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;


public class Graphs {
	/**
	 * Description
	 *
	 * @param graph         input graph
	 * @param origin        starting vertex
	 * @param numberOfEdges
	 * @return Set of vertices within the specified numberOfEdges
	 */
	public static Set<String> nearby(Graph graph, String origin, int numberOfEdges) {
		//DUMMY CODE (STUB) TODO
		Set<String> result = new HashSet<>();
		// But instead of these you have to return the set of string and their hops in relation to an origin, with
		// what I assume to be a BFS algorithm, each time you do one hop, create a string and add the count to it,
		// continue this process recursively, perhaps you need to somehow mark the vertices as visited to avoid cycles,
		// You get the list of edges connecting to the neighbouring vertices
		// maybe you can create a map to keep track of visited cities, with String:Boolean relationship

		// Hashmap to determine if the city vertices were searched before or not
		HashMap<String, Boolean> isMarked = new HashMap<>();

		for (String vertex : graph.vertices()) {
			isMarked.put(vertex, false);
		}

		// Queue for BFS
		Queue<String> queue = new LinkedList<>();

		// Queue for storing the level or the depth of the vertex relative to the origin vertex
		Queue<Integer> levelQueue = new LinkedList<>();

		queue.add(origin);
		levelQueue.add(0);

		String str = "";

		// Continues as long as there are adjacent vertices in the queue and the number of hops less than the numberOfEdges variable
		while (!queue.isEmpty()) {
			String current = queue.poll();
			int level = levelQueue.poll();

			// In case the level exceeds the numberOfEdges allowed, exit the loop
			if (level > numberOfEdges) {
				break;
			}

			// If the map has the city in it, and it is not visited
			if (isMarked.containsKey(current) && !isMarked.get(current)) {
				// Mark it as visited
				isMarked.put(current, true);
				// Create the string and add it onto the set
				str = current + ", " + level;
				result.add(str);
			}

			// All the neighbours of the current vertex
			List<Edge> edgeList = graph.adj.get(current);

			for (Edge edge : edgeList) {
				// Adding all the neighbours of the current vertex that have not been visited
				if (!isMarked.get(edge.other(edge.either()).name)) {
					queue.add(edge.other(edge.either()).name);
					levelQueue.add(level + 1);
				}
			}


		}

		// Removing the origin vertex that was added
		result.remove(origin + ", 0");

		return result;
	}
}


// private methods go here if needed