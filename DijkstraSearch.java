import java.util.*;

/**
 * Implementation of Dijkstra's algorithm for finding shortest paths in a weighted graph.
 * This algorithm finds the shortest path from a source vertex to all other vertices.
 *
 * @param <T> The type of data stored in the graph's vertices
 */

public class DijkstraSearch<T> implements Search<T> {
    private final Map<T, Double> distTo; // Distance from source to each vertex
    private final Map<T, T> edgeTo; // Previous vertex on shortest path
    private final PriorityQueue<VertexDistance<T>> pq; // Priority queue for vertices
    private final T source; // Source vertex
    private final WeightedGraph<T> graph; // Graph to search

    /**
     * Helper class to store a vertex with its distance from the source.
     * Used for the priority queue in Dijkstra's algorithm.
     *
     * @param <T> The type of data stored in the vertex
     */

    private static class VertexDistance<T> implements Comparable<VertexDistance<T>> {
        private T vertex; // The vertex
        private Double distance; // The distance from source

        /**
         * Constructs a new VertexDistance object.
         *
         * @param vertex The vertex
         * @param distance The distance from source
         */

        public VertexDistance(T vertex, Double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * Compares this vertex distance with another based on distance.
         *
         * @param other The other vertex distance
         * @return A negative integer if this distance is less than the other,
         *         zero if they're equal, or a positive integer otherwise
         */

        @Override
        public int compareTo(VertexDistance<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    /**
     * Constructs a DijkstraSearch object and performs the search from the source vertex.
     *
     * @param graph The graph to search
     * @param source The source vertex to start the search from
     */

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        this.graph = graph;
        this.source = source;
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();
        this.pq = new PriorityQueue<>();

        // Initialize distances
        for (T vertex : graph.getVerticesKeys()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        // Add source to priority queue
        pq.add(new VertexDistance<>(source, 0.0));

        // Run Dijkstra's algorithm
        while (!pq.isEmpty()) {
            relax(pq.poll().vertex); // Relax edges from the vertex with smallest distance
        }
    }

    /**
     * Relaxes (updates) the distances to all neighbors of the given vertex.
     * This is the core part of Dijkstra's algorithm.
     *
     * @param vertex The vertex to relax edges from
     */

    private void relax(T vertex) {
        // For each neighbor of this vertex
        for (Vertex<T> neighbor : graph.getNeighbors(vertex)) {
            T neighborData = neighbor.getData();
            double weight = graph.getWeight(vertex, neighborData);

            // If we found a shorter path to the neighbor
            if (distTo.get(neighborData) > distTo.get(vertex) + weight) {
                // Update distance
                double newDist = distTo.get(vertex) + weight;
                distTo.put(neighborData, newDist);

                // Update edge
                edgeTo.put(neighborData, vertex);

                // Update priority queue
                // Note: This is a simplified approach that might add duplicate entries
                // A more efficient implementation would update the priority queue in place
                pq.add(new VertexDistance<>(neighborData, newDist));
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
        return distTo.get(vertex) < Double.POSITIVE_INFINITY;
    }

    /**
     * Gets the distance from the source to the specified vertex.
     *
     * @param vertex The destination vertex
     * @return The distance to the vertex, or POSITIVE_INFINITY if no path exists
     */

    public double distTo(T vertex) {
        return distTo.get(vertex);
    }

    /**
     * Returns the shortest path from the source to the specified vertex.
     * The path is returned as a list of vertices.
     *
     * @param key The destination vertex
     * @return A list representing the shortest path from source to destination,
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
            path.addFirst(current);  // Add each vertex to the beginning of the list
            current = edgeTo.get(current); // Move to the previous vertex in the path
        }
        path.addFirst(source);  // Add the source vertex

        return path;
    }
}