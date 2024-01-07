package com.DmitriyDevv.utils.findingPath;

import com.DmitriyDevv.Coords;
import com.DmitriyDevv.WorldMap;
import com.DmitriyDevv.entitys.movingEntities.Creature;

import java.util.*;

public class Astar {
    private final WorldMap worldMap;
    private final Creature creature;
    private final Comparator<Node> nodeComparator =
            (node1, node2) -> {
                return Comparator.comparing(Node::getFCost).compare(node1, node2);
            };

    public Astar(WorldMap worldMap, Creature creature) {
        this.worldMap = worldMap;
        this.creature = creature;
    }

    public List<Coords> getPath() {
        Coords startPoint = new Coords(creature.getX(), creature.getY());
        Coords endPoint = getGoalCoords();

        if (endPoint == null) {
            return null;
        }

        Node start = new Node(startPoint.getX(), startPoint.getY());
        Node goal = new Node(endPoint.getX(), endPoint.getY());

        PriorityQueue<Node> openList = new PriorityQueue<>(nodeComparator);
        HashSet<Node> closeList = new HashSet<>();

        List<Coords> path = new ArrayList<>();

        start.gCost = 0;
        start.hCost = calculateHCost(start, goal);

        openList.add(start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            if (currentNode.equals(goal)) {
                restorePath(path, currentNode);
                break;
            }

            for (Node neighbour : getNeighbors(currentNode, goal)) {
                if (closeList.contains(neighbour)) {
                    continue;
                }

                double tentativeGCost = start.gCost + calculateGCost(currentNode, neighbour);

                if (tentativeGCost < neighbour.gCost || !openList.contains(neighbour)) {
                    neighbour.gCost = tentativeGCost;
                    neighbour.hCost = calculateHCost(neighbour, goal);
                    neighbour.previous = currentNode;

                    if (!openList.contains(neighbour)) {
                        openList.add(neighbour);
                    }
                }
            }
            closeList.add(currentNode);
        }
        return path;
    }

    public Coords getGoalCoords() {
        int rows = worldMap.getHEIGHT() + 1;
        int cols = worldMap.getWIDTH() + 1;
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {creature.getY(), creature.getX()});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            if (worldMap.containsEntity(x, y)
                    && worldMap.getEntity(x, y).getClass().isAssignableFrom(creature.getGoal())) {
                return new Coords(x, y);
            }

            for (Coords coord : getCoordsNeighbors(x, y)) {
                int newX = coord.getX();
                int newY = coord.getY();

                if (!visited[newY][newX]) {
                    queue.add(new int[] {newY, newX});
                    visited[newY][newX] = true;
                }
            }
        }
        return null;
    }

    public double calculateHCost(Node current, Node goal) {
        int cost = 2;
        if (current.getX() == goal.getX() || current.getY() == goal.getY()) {
            cost = 1;
        }
        return Math.sqrt(Math.pow(current.x - goal.x, 2) + Math.pow(current.y - goal.y, 2)) + cost;
    }

    private void restorePath(List<Coords> path, Node currentNode) {
        while (currentNode != null) {
            path.add(new Coords(currentNode.x, currentNode.y));
            currentNode = currentNode.previous;
        }
        Collections.reverse(path);
    }

    private List<Node> getNeighbors(Node current, Node goal) {
        List<Node> neighbors = new ArrayList<>();
        for (Coords coord : getCoordsNeighbors(current.getX(), current.getY())) {
            int x = coord.getX();
            int y = coord.getY();

            if (!worldMap.containsEntity(x, y) || (goal.getX() == x && goal.getY() == y)) {
                neighbors.add(new Node(x, y));
            }
        }
        return neighbors;
    }

    private List<Coords> getCoordsNeighbors(int x, int y) {
        List<Coords> neighbors = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newY <= worldMap.getHEIGHT()
                    && newX <= worldMap.getWIDTH()
                    && newY >= 0
                    && newX >= 0) {
                neighbors.add(new Coords(newX, newY));
            }
        }
        return neighbors;
    }

    private double calculateGCost(Node current, Node neighbor) {
        int dx = Math.abs(current.getX() - neighbor.getX());
        int dy = Math.abs(current.getY() - neighbor.getY());
        int cost = dx == dy ? 2 : 3;

        return Math.abs(current.x - neighbor.x) + Math.abs(current.y - neighbor.y) + cost;
    }

    private static class Node {
        int x;
        int y;
        double gCost;
        double hCost;
        Node previous;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getFCost() {
            return gCost + hCost;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
