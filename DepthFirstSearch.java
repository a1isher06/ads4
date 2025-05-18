import java.util.*;

/**
 * Implementation of depth-first search algorithm for traversing a graph.
 * DFS explores as far as possible along each branch before backtracking.
 *
 * @param <T> The type of data stored in the graph's vertices
 */

public class DepthFirstSearch<T> implements Search<T> {
    private final Map<T, Boolean> marked; // Tracks visited vertices
    private final Map<T, T> edgeTo; // Stores the path (which vertex we came from)
    private final T source; // Source vertex for the search
    private final UnweightedGraph<T> graph; // Graph to search

    /**
     * Constructs a DFS object and performs the search from the source vertex.
     *
     * @param graph The graph to search
     * @param source The source vertex to start the search from
     */

    public DepthFirstSearch(UnweightedGraph<T> graph, T source) {
        this.graph = graph;
        this.source = source;
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();

        // Initialize all vertices as not visited
        for (T vertex : graph.getVertices()) {
            marked.put(vertex, false);
        }

        // Run DFS starting from the source vertex
        dfs(source);
    }

    /**
     * Performs depth-first search recursively from the specified vertex.
     *
     * @param vertex The current vertex in the DFS traversal
     */

    private void dfs(T vertex) {
        marked.put(vertex, true); // Mark current vertex as visited

        // Visit all unvisited neighbors recursively
        for (T neighbor : graph.getAdjVertices(vertex)) {
            if (!marked.get(neighbor)) {
                edgeTo.put(neighbor, vertex); // Remember how we got to this neighbor
                dfs(neighbor); // Recursive call to DFS
            }
        }
    }

    /**
     * Checks if there is a path from the source to the specified vertex.
     *
     * @param vertex The destination vertex
     * @return true if a path exists, false otherwise
     */

    private boolean hasPathTo(T vertex) {
        return marked.getOrDefault(vertex, false);
    }

    /**
     * Returns the path from the source to the specified vertex.
     * The path is returned as a list of vertices.
     *
     * @param key The destination vertex
     * @return A list representing the path from source to destination,
     *         or an empty list if no path exists
     */

    @Override
    public List<T> pathTo(T key) {
        if (!hasPathTo(key)) {
            return new ArrayList<>(); // No path exists
        }

        // Reconstruct the path by working backwards from the destination
        LinkedList<T> path = new LinkedList<>();
        T current = key;
        while (!current.equals(source)) {
            path.addFirst(current); // Add each vertex to the beginning of the list
            current = edgeTo.get(current);  // Move to the previous vertex in the path
        }
        path.addFirst(source); // Add the source vertex

        return path;
    }
}