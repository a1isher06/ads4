/**
 * Main class to demonstrate the graph implementations and search algorithms.
 * This class creates graphs, adds edges, and runs different search algorithms.
 */

public class Main {

    /**
     * Main method to run the demonstration.
     *
     * @param args Command line arguments (not used)
     */

    public static void main(String[] args) {
        // Create and populate weighted graph
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(true);
        fillWithWeights(weightedGraph);

        // Run Dijkstra's algorithm
        System.out.println("Dijkstra:");
        Search<String> djk = new DijkstraSearch<>(weightedGraph, "Almaty");
        outputPath(djk, "Kyzylorda");
        System.out.println("--------------------------------");

        // Create and populate unweighted graph
        UnweightedGraph<String> graph = new UnweightedGraph<>(true);
        fillWithoutWeights(graph);

        // Run Depth-First Search
        System.out.println("DFS:");
        Search<String> dfs = new DepthFirstSearch<>(graph, "Almaty");
        outputPath(dfs, "Kyzylorda");
        System.out.println("--------------------------------");

        // Run Breadth-First Search
        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(weightedGraph, "Almaty");
        outputPath(bfs, "Kyzylorda");
    }

    /**
     * Populates an unweighted graph with edges representing connections between cities.
     *
     * @param graph The unweighted graph to populate
     */

    public static void fillWithoutWeights(UnweightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana");
        graph.addEdge("Shymkent", "Atyrau");
        graph.addEdge("Atyrau", "Astana");
        graph.addEdge("Almaty", "Shymkent");
        graph.addEdge("Shymkent", "Astana");
        graph.addEdge("Astana", "Kostanay");
        graph.addEdge("Shymkent", "Kyzylorda");
    }

    /**
     * Populates a weighted graph with edges and weights representing connections between cities.
     *
     * @param graph The weighted graph to populate
     */

    public static void fillWithWeights(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    /**
     * Outputs the path found by a search algorithm.
     *
     * @param search The search algorithm that found the path
     * @param key The destination vertex
     */

    public static void outputPath(Search<String> search, String key) {
        for (String v : search.pathTo(key)) {
            System.out.print(v + " -> ");
        }
        System.out.println();
    }
}