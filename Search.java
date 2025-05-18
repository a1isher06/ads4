import java.util.List;

/**
 * Search interface for graph traversal algorithms.
 * Classes implementing this interface provide methods to find paths in a graph.
 *
 * @param <T> The type of data stored in the graph's vertices
 */

public interface Search<T> {

    /**
     * Finds a path from the source vertex to the specified destination vertex.
     *
     * @param key The data of the destination vertex
     * @return A list representing the path from source to destination,
     *         or an empty list if no path exists
     */

    List<T> pathTo(T key);
}