import java.util.*;

/**
 * UnweightedGraph represents a graph without edge weights.
 * This implementation is included for compatibility with the Main class
 * and to demonstrate the difference between weighted and unweighted graphs.
 *
 * @param <T> The type of data stored in the graph's vertices
 */

public class UnweightedGraph<T> {
    private final boolean undirected; // Flag indicating if the graph is undirected
    private final Map<T, List<T>> map = new HashMap<>(); // Adjacency list representation

    /**
     * Constructs a new unweighted graph with default undirected behavior.
     */

    public UnweightedGraph() {
        this(true);
    }

    /**
     * Constructs a new unweighted graph.
     *
     * @param undirected true if the graph is undirected, false if directed
     */

    public UnweightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    /**
     * Adds a vertex to the graph if it doesn't already exist.
     *
     * @param vertex The vertex to add
     */

    public void addVertex(T vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination) {
        map.putIfAbsent(source, new ArrayList<>());
        map.putIfAbsent(destination, new ArrayList<>());

        map.get(source).add(destination);

        if (undirected) {
            map.get(destination).add(source);
        }
    }

    public List<T> getAdjVertices(T vertex) {
        return map.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<T> getVertices() {
        return map.keySet();
    }

    public boolean hasVertex(T vertex) {
        return map.containsKey(vertex);
    }

    public boolean isUndirected() {
        return undirected;
    }
}