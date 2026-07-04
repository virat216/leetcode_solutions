/*
============================================================
LEETCODE 2492 - Minimum Score of a Path Between Two Cities

Problem:
Given an undirected graph where each road has a distance,
find the minimum possible score of a path from city 1 to
city n.

Score of a Path:
The score is the minimum edge distance present in that path.

------------------------------------------------------------
Intuition

The problem allows revisiting cities and roads.

This means we are not restricted to a simple path.
Any road inside the connected component containing city 1
can be included in some valid path to city n.

Therefore, instead of searching for a particular path,
we only need to traverse the connected component of city 1
and find the minimum edge weight.

------------------------------------------------------------
Approach

1. Build the graph using an adjacency list.
2. Perform BFS (or DFS) starting from city 1.
3. Traverse every reachable city.
4. For every edge visited, update the minimum edge weight.
5. Return the minimum edge found.

------------------------------------------------------------
Algorithm

1. Create an adjacency list.
2. Insert every road in both directions.
3. Initialize a queue with city 1.
4. Mark visited cities.
5. While queue is not empty:
      - Remove front city.
      - Visit all neighbours.
      - Update answer using
            ans = Math.min(ans, distance)
      - Push unvisited neighbours.
6. Return ans.

------------------------------------------------------------
Why It Works

Since revisiting cities is allowed, every edge inside the
connected component of city 1 can belong to some valid path.

Hence, the smallest edge in that connected component is
guaranteed to be the minimum score.

------------------------------------------------------------
Time Complexity

Building Graph : O(m)

BFS Traversal  : O(n + m)

Overall        : O(n + m)

------------------------------------------------------------
Space Complexity

Adjacency List : O(n + m)

Visited Array  : O(n)

Queue          : O(n)

Overall        : O(n + m)

------------------------------------------------------------
Functions Used

add()       -> Add neighbour in adjacency list.
offer()     -> Insert into queue.
poll()      -> Remove front element from queue.
Math.min()  -> Update minimum edge weight.

============================================================
*/


import java.util.*;
public class leetcode2492 {

    public static int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {

            int u = road[0];
            int v = road[1];
            int distance = road[2];

            graph[u].add(new int[]{v, distance});
            graph[v].add(new int[]{u, distance});
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visited[1] = true;

        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int[] edge : graph[node]) {

                int next = edge[0];
                int distance = edge[1];

                ans = Math.min(ans, distance);

                if (!visited[next]) {

                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();

        System.out.print("Enter number of roads: ");
        int m = sc.nextInt();

        int[][] roads = new int[m][3];

        System.out.println("Enter roads (city1 city2 distance):");

        for (int i = 0; i < m; i++) {

            roads[i][0] = sc.nextInt();
            roads[i][1] = sc.nextInt();
            roads[i][2] = sc.nextInt();
        }

        int ans = minScore(n, roads);

        System.out.println("Minimum Score = " + ans);

        sc.close();
    }
}
