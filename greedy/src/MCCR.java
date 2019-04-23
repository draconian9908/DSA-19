import java.util.HashSet;
import java.util.PriorityQueue;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // SETUP
            int cost = 0;
            HashSet<Integer> seen = new HashSet<>(G.numberOfV());
            HashSet<Integer> looking = new HashSet<>(G.numberOfV());
            PriorityQueue<Edge> edges = new PriorityQueue<>((a,b) -> a.weight()-b.weight());

            // INITIAL VALUES
            for (Edge e : G.edges(0)) {
                edges.add(e);
                looking.add(e.other(0));
            }
            seen.add(0);
            Edge checking;
            int v;

            // SOLVE
            while (seen.size() < G.numberOfV()) {
                checking = edges.poll();
                cost += checking.weight();
                if (seen.contains(checking.either())) {
                    v = checking.other(checking.either());
                }
                else v = checking.either();
                Iterable<Edge> newEdges = G.edges(v);
                for (Edge e : newEdges) {
                    if (looking.contains(e.other(v)) ) {
                        Edge eOld = find(e.other(v), edges);
                        if (eOld != null && eOld.weight() > e.weight()) {//eOld != null &&
                            edges.remove(eOld);
                            edges.add(e);
                        }
                    }
                    else if (!edges.contains(e) && !seen.contains(e.other(v))) {
                        edges.add(e);
                        looking.add(e.other(v));
                    }
                }
                looking.remove(v);
                seen.add(v);
            }

            // DONE
            return cost;
        }

        private static Edge find(int v, PriorityQueue<Edge> edges) {
            for (Edge e : edges) {
                int tempV = e.either();
                if (tempV == v || e.other(tempV) == v) {
                    return e;
                }
            }
            return null;
        }
    }

