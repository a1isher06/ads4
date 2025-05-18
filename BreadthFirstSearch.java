import java.util.*;

/**
 * Implementation of breadth-first search algorithm for traversing a graph.
 * BFS explores all vertices at the current depth before moving to vertices at the next depth level.
 *
 * @param <T> The type of data stored in the graph's vertices
 */

public class BreadthFirstSearch<T> implements Search<T> {
    private final Map<T, Boolean> marked;
    private final Map<T, T> edgeTo;
    private final T source;
    private final WeightedGraph<T> graph;

    /**
     * Constructs a BFS object and performs the search from the source vertex.
     *
     * @param graph The graph to search
     * @param source The source vertex to start the search from
     */

    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        this.graph = graph;
        this.source = source;
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();

        // Initialize all vertices as not visited

        for (T vertex : graph.getVerticesKeys()) {
            marked.put(vertex, false);
        }

        // Run BFS starting from the source vertex

        bfs(source);
    }

    /**
     * Performs breadth-first search from the specified source vertex.
     *
     * @param source The source vertex to start the search from
     */

    private void bfs(T source) {
        Queue<T> queue = new LinkedList<>(); // Queue for BFS traversal
        marked.put(source, true); // Mark source as visited
        queue.add(source); // Add source to the queue

        while (!queue.isEmpty()) {
            T current = queue.poll(); // Get the next vertex from the queue

            // Visit all unvisited neighbors
            for (Vertex<T> neighbor : graph.getNeighbors(current)) {
                T neighborData = neighbor.getData();
                if (!marked.get(neighborData)) {
                    edgeTo.put(neighborData, current); // Remember how we got to this neighbor
                    marked.put(neighborData, true); // Mark neighbor as visited
                    queue.add(neighborData);  // Add neighbor to the queue
                }
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
            current = edgeTo.get(current); // Move to the previous vertex in the path
        }
        path.addFirst(source);  // Add the source vertex

        return path;
    }
}