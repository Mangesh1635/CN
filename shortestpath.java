 import java.util.*;

class NetworkRouting {
    protected Map<Character, Map<Character, Integer>> graph;

    public NetworkRouting(Map<Character, Map<Character, Integer>> graph) {
        this.graph = graph;
    }

    public List<Character> shortestPath(char source, char destination) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            return Collections.emptyList(); // source or destination not in graph
        }

        Map<Character, Integer> dist = new HashMap<>();
        Map<Character, Character> prev = new HashMap<>();

        for (char node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );
        pq.offer(new AbstractMap.SimpleEntry<>(source, 0));

        while (!pq.isEmpty()) {
            char node = pq.poll().getKey();

            if (node == destination)
                break;

            for (Map.Entry<Character, Integer> neighborEntry : graph.get(node).entrySet()) {
                char neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();

                int newDist = dist.get(node) + weight;
                if (newDist < dist.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, node);
                    pq.offer(new AbstractMap.SimpleEntry<>(neighbor, newDist));
                }
            }
        }

        if (!dist.containsKey(destination) || dist.get(destination) == Integer.MAX_VALUE) {
            return Collections.emptyList(); // No path found
        }

        List<Character> path = new ArrayList<>();
        for (char at = destination; at != source; at = prev.get(at)) {
            path.add(0, at);
        }
        path.add(0, source);
        return path;
    }
}

class AODV extends NetworkRouting {
    private Map<Character, Map<Character, List<Character>>> routeTable;

    public AODV(Map<Character, Map<Character, Integer>> graph) {
        super(graph);
        routeTable = new HashMap<>();
    }

    public List<Character> discoverRoute(char source, char destination) {
        routeTable.putIfAbsent(source, new HashMap<>());
        if (!routeTable.get(source).containsKey(destination)) {
            List<Character> path = shortestPath(source, destination);
            if (path.isEmpty()) return Collections.emptyList();
            routeTable.get(source).put(destination, path);
        }
        return routeTable.get(source).get(destination);
    }
}

public class shortestpath {
    public static void main(String[] args) {
        Map<Character, Map<Character, Integer>> graph = new HashMap<>();

        graph.put('A', Map.of('B', 1, 'C', 2));
        graph.put('B', Map.of('A', 1, 'C', 1, 'D', 3));
        graph.put('C', Map.of('A', 2, 'B', 1, 'D', 1, 'E', 4));
        graph.put('D', Map.of('B', 3, 'C', 1, 'E', 1));
        graph.put('E', Map.of('C', 4, 'D', 1));

        NetworkRouting nr = new NetworkRouting(graph);
        AODV aodv = new AODV(graph);

        List<Character> path1 = nr.shortestPath('A', 'E');
        System.out.print("Shortest path from A to E: ");
        if (path1.isEmpty()) {
            System.out.println("No path found");
        } else {
            path1.forEach(node -> System.out.print(node + " "));
            System.out.println();
        }

        List<Character> path2 = aodv.discoverRoute('A', 'E');
        System.out.print("AODV route from A to E: ");
        if (path2.isEmpty()) {
            System.out.println("No route found");
        } else {
            path2.forEach(node -> System.out.print(node + " "));
            System.out.println();
        }

        List<Character> path3 = nr.shortestPath('E', 'B');
        System.out.print("Shortest path from E to B: ");
        if (path3.isEmpty()) {
            System.out.println("No path found");
        } else {
            path3.forEach(node -> System.out.print(node + " "));
            System.out.println();
        }
    }
}