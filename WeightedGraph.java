import java.util.*;

/**
 * WeightedGraph represents a graph with weighted edges.
 * This implementation uses vertices that maintain their own adjacency lists.
 *
 * @param <T> The type of data stored in the graph's vertices
 */

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<T, Vertex<T>> vertices;

    /**
     * Constructs a new weighted graph with default undirected behavior.
     */

    public WeightedGraph() {
        this(true);
    }

    /**
     * Constructs a new weighted graph.
     *
     * @param undirected true if the graph is undirected, false if directed
     */

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.vertices = new HashMap<>();
    }

    /**
     * Adds a vertex with the given data to the graph if it doesn't already exist.
     *
     * @param data The data for the new vertex
     */

    public void addVertex(T data) {
        if (!vertices.containsKey(data)) {
            vertices.put(data, new Vertex<>(data));
        }
    }

    /**
     * Gets the vertex object associated with the given data.
     *
     * @param data The data to look up
     * @return The vertex containing the data, or null if not found
     */

    public Vertex<T> getVertex(T data) {
        return vertices.get(data);
    }

    /**
     * Checks if a vertex with the given data exists in the graph.
     *
     * @param data The data to look for
     * @return true if a vertex with the data exists, false otherwise
     */

    public boolean hasVertex(T data) {
        return vertices.containsKey(data);
    }

    /**
     * Adds an edge between two vertices with the given weight.
     * Creates the vertices if they don't already exist.
     *
     * @param source The source vertex data
     * @param destination The destination vertex data
     * @param weight The weight of the edge
     */

    public void addEdge(T source, T destination, double weight) {
        // Ensure vertices exist
        addVertex(source);
        addVertex(destination);

        // Add the edge
        vertices.get(source).addAdjacentVertex(vertices.get(destination), weight);

        // If undirected, add the reverse edge too
        if (undirected) {
            vertices.get(destination).addAdjacentVertex(vertices.get(source), weight);
        }
    }

    /**
     * Removes an edge between two vertices.
     *
     * @param source The source vertex data
     * @param destination The destination vertex data
     */

    public void removeEdge(T source, T destination) {
        if (hasVertex(source) && hasVertex(destination)) {
            vertices.get(source).removeAdjacentVertex(vertices.get(destination));

            if (undirected) {
                vertices.get(destination).removeAdjacentVertex(vertices.get(source));
            }
        }
    }

    /**
     * Gets all vertex data keys in the graph.
     *
     * @return A set of all vertex data
     */

    public Set<T> getVerticesKeys() {
        return vertices.keySet();
    }

    /**
     * Gets all neighboring vertices of a given vertex.
     *
     * @param data The data of the vertex to find neighbors for
     * @return A list of neighboring vertices
     */

    public List<Vertex<T>> getNeighbors(T data) {
        if (!hasVertex(data)) {
            return new ArrayList<>();
        }

        List<Vertex<T>> neighbors = new ArrayList<>();
        for (Vertex<T> vertex : vertices.get(data).getAdjacentVertices().keySet()) {
            neighbors.add(vertex);
        }
        return neighbors;
    }

    /**
     * Gets the weight of an edge between two vertices.
     *
     * @param source The source vertex data
     * @param destination The destination vertex data
     * @return The weight of the edge, or POSITIVE_INFINITY if no edge exists
     */

    public double getWeight(T source, T destination) {
        if (hasVertex(source) && hasVertex(destination)) {
            Vertex<T> sourceVertex = vertices.get(source);
            Vertex<T> destVertex = vertices.get(destination);

            Double weight = sourceVertex.getWeight(destVertex);
            return weight != null ? weight : Double.POSITIVE_INFINITY;
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Checks if this graph is undirected.
     *
     * @return true if the graph is undirected, false otherwise
     */

    public boolean isUndirected() {
        return undirected;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return A string representation of the graph
     */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T data : vertices.keySet()) {
            builder.append(data.toString()).append(": ");
            for (Map.Entry<Vertex<T>, Double> entry : vertices.get(data).getAdjacentVertices().entrySet()) {
                builder.append(entry.getKey().getData().toString())
                        .append("(").append(entry.getValue()).append(") ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}