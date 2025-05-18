import java.util.HashMap;
import java.util.Map;

/**
 * Vertex class representing a node in a graph.
 * Each vertex stores its data and maintains its own adjacency list with weights.
 *
 * @param <T> The type of data stored in the vertex
 */

public class Vertex<T> {
    private T data;
    private Map<Vertex<T>, Double> adjacentVertices; // Adjacency list with weights

    /**
     * Constructs a new vertex with the given data.
     *
     * @param data The data to store in the vertex
     */

    public Vertex(T data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    /**
     * Gets the data stored in this vertex.
     *
     * @return The vertex data
     */

    public T getData() {
        return data;
    }

    /**
     * Sets the data for this vertex.
     *
     * @param data The new data to store
     */

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the map of adjacent vertices and their edge weights.
     *
     * @return Map of adjacent vertices to edge weights
     */

    public Map<Vertex<T>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    /**
     * Adds an edge to another vertex with the specified weight.
     *
     * @param destination The destination vertex
     * @param weight The weight of the edge
     */

    public void addAdjacentVertex(Vertex<T> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    /**
     * Removes an edge to the specified vertex.
     *
     * @param destination The vertex to remove from adjacency list
     */

    public void removeAdjacentVertex(Vertex<T> destination) {
        adjacentVertices.remove(destination);
    }

    /**
     * Gets the weight of the edge to the specified vertex.
     *
     * @param destination The destination vertex
     * @return The weight of the edge, or null if no edge exists
     */

    public Double getWeight(Vertex<T> destination) {
        return adjacentVertices.get(destination);
    }

    /**
     * Checks if this vertex equals another object.
     * Vertices are considered equal if they have the same data.
     *
     * @param obj The object to compare with
     * @return true if the vertices are equal, false otherwise
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) obj;
        return data != null ? data.equals(vertex.data) : vertex.data == null;
    }

    /**
     * Returns the hash code for this vertex.
     * The hash code is based on the vertex's data.
     *
     * @return The hash code
     */

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    /**
     * Returns a string representation of this vertex.
     *
     * @return A string representation of the vertex's data
     */

    @Override
    public String toString() {
        return data.toString();
    }
}