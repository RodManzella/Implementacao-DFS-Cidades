import java.util.*;

public class Graph {
    private Map<String, Integer> cityIndexMap;
    private LinkedList<Edge>[] adj;

    public Graph(int numberOfCities) {
        this.cityIndexMap = new HashMap<>();
        this.adj = new LinkedList[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(String city1, String city2, int distance) {
        int index1 = getOrCreateCityIndex(city1);
        int index2 = getOrCreateCityIndex(city2);
        
        this.adj[index1].add(new Edge(index2, distance));
        this.adj[index2].add(new Edge(index1, distance)); // Grafo não direcionado
    }

    private int getOrCreateCityIndex(String city) {
        if (!cityIndexMap.containsKey(city)) {
            int newIndex = cityIndexMap.size();
            cityIndexMap.put(city, newIndex);
        }
        return cityIndexMap.get(city);
    }

    private class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public void dfs(String startCity) {
        boolean[] visited = new boolean[adj.length];
        Stack<Integer> stack = new Stack<>();
        int startIndex = cityIndexMap.get(startCity);
        
        stack.push(startIndex);

        while (!stack.isEmpty()) {
            int currentCityIndex = stack.pop();
            if (!visited[currentCityIndex]) {
                visited[currentCityIndex] = true;
                System.out.println(getCityName(currentCityIndex));

                for (Edge neighbor : adj[currentCityIndex]) {
                    if (!visited[neighbor.destination]) {
                        stack.push(neighbor.destination);
                    }
                }
            }
        }
    }

    private String getCityName(int index) {
        for (Map.Entry<String, Integer> entry : cityIndexMap.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Graph cityGraph = new Graph(4);
        cityGraph.addEdge("New York", "Los Angeles", 2800);
        cityGraph.addEdge("New York", "Chicago", 800);
        cityGraph.addEdge("Chicago", "Los Angeles", 2000);
        cityGraph.addEdge("Chicago", "San Francisco", 2200);

        System.out.println("Depth-First Search (iterative) starting from New York:");
        cityGraph.dfs("New York");
    }
}