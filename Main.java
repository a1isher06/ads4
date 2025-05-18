/**
 * Main class to demonstrate graph implementations and search algorithms.
 * This class creates graphs, adds edges, and runs different search algorithms.
 */
public class Main {

    /**
     * Main method to run the demonstration.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create and populate a weighted graph
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(true);
        populateWithWeights(weightedGraph);

        // Run Dijkstra's algorithm
        System.out.println("Dijkstra's Algorithm:");
        Search<String> dijkstra = new DijkstraSearch<>(weightedGraph, "New York");
        printPath(dijkstra, "Phoenix");
        System.out.println("--------------------------------");

        // Create and populate an unweighted graph
        UnweightedGraph<String> unweightedGraph = new UnweightedGraph<>(true);
        populateWithoutWeights(unweightedGraph);

        // Run Depth-First Search
        System.out.println("Depth-First Search (DFS):");
        Search<String> dfs = new DepthFirstSearch<>(unweightedGraph, "New York");
        printPath(dfs, "Phoenix");
        System.out.println("--------------------------------");

        // Run Breadth-First Search
        System.out.println("Breadth-First Search (BFS):");
        Search<String> bfs = new BreadthFirstSearch<>(weightedGraph, "New York");
        printPath(bfs, "Phoenix");
    }

    /**
     * Populates an unweighted graph with edges representing connections between U.S. cities.
     *
     * @param graph The unweighted graph to populate
     */
    public static void populateWithoutWeights(UnweightedGraph<String> graph) {
        graph.addEdge("New York", "Chicago");
        graph.addEdge("Los Angeles", "Houston");
        graph.addEdge("Houston", "Chicago");
        graph.addEdge("New York", "Los Angeles");
        graph.addEdge("Los Angeles", "Chicago");
        graph.addEdge("Chicago", "Seattle");
        graph.addEdge("Los Angeles", "Phoenix");
    }

    /**
     * Populates a weighted graph with edges and weights representing distances between U.S. cities.
     *
     * @param graph The weighted graph to populate
     */
    public static void populateWithWeights(WeightedGraph<String> graph) {
        graph.addEdge("New York", "Chicago", 790.0);
        graph.addEdge("Los Angeles", "Houston", 1547.0);
        graph.addEdge("Houston", "Chicago", 1084.0);
        graph.addEdge("New York", "Los Angeles", 2445.0);
        graph.addEdge("Los Angeles", "Chicago", 2015.0);
        graph.addEdge("Chicago", "Seattle", 2064.0);
        graph.addEdge("Los Angeles", "Phoenix", 373.0);
    }

    /**
     * Outputs the path found by a search algorithm.
     *
     * @param search The search algorithm that found the path
     * @param key The destination vertex
     */
    public static void printPath(Search<String> search, String key) {
        for (String city : search.pathTo(key)) {
            System.out.print(city + " -> ");
        }
        System.out.println("END");
    }
}
