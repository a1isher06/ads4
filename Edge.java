public class Edge<T> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private double weight;

    public Edge(Vertex<T> source, Vertex<T> destination) {
        this.source = source;
        this.destination = destination;
        weight = 0;
    }

    public Edge(Vertex<T> source, Vertex<T> destination, double weight) {
        this(source, destination);
        this.weight = weight;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public void setSource(Vertex<T> source) {
        this.source = source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<T> destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}