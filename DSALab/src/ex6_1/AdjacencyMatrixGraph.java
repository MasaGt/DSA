/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex6_1;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * No need to expand the capacity
 * @author Masaomi
 */
public class AdjacencyMatrixGraph<E> implements GraphADT<E>{
    //value is the index of a vertex
    private Map<Vertex<E>, Integer> vertices;
    private Edge[][] edgeMatrix;
    private final int CAPACITY = 10;
    private int counter;

    public AdjacencyMatrixGraph() {
        vertices = new LinkedHashMap<>();
        edgeMatrix = new AdjacencyMatrixEdge[CAPACITY][CAPACITY];
        counter = 0;
    }

    public <F extends E> AdjacencyMatrixGraph(GraphADT<F> graph) {
        this();
        addGraph(graph);
    }
    
    @Override
    public void clear() {
        vertices.clear();
        edgeMatrix = new AdjacencyMatrixEdge[CAPACITY][CAPACITY];
        counter = 0;
    }

    // returns true if the graph has no vertices nor edges
    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    // returns a view of the vertices as a Set
    @Override
    public Set<Vertex<E>> vertexSet() {
        return Collections.unmodifiableSet(vertices.keySet());
    }

    // returns a view of the edges as a Set
    @Override
    public Set<Edge<E>> edgeSet() {
        Set<Edge<E>> edges = new LinkedHashSet<>();
        
        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix[i].length; j++) {
                if (edgeMatrix[i][j] != null) {
                    edges.add(edgeMatrix[i][j]);
                }
            }
        }
       return Collections.unmodifiableSet(edges);
    }

    // generic method which adds a copy of the graph (that has elements
    // of type F which extends type E) into this graph
    @Override
    public <F extends E> void addGraph(GraphADT<F> graph) {
        
        Set<Vertex<F>> vSet = graph.vertexSet();
        for (Vertex<F> v : vSet) {
            addVertex(v.getUserObject());
        }
        
       Set<Edge<F>> eSet = graph.edgeSet();
       for (Edge<F> e : eSet) {
           Vertex[] adjacentVertecies = e.endVertices();
           addEdge(adjacentVertecies[0], adjacentVertecies[1]);
       }
    }

    // adds and returns a new isolated vertex to the graph
    @Override
    public Vertex<E> addVertex(E element) {
        Vertex<E> vertex = new AdjacencyMatrixVertex<>(element);
        //Since expansion is not implemented, this keep indexOutOfBounds from hapening
        if (counter < CAPACITY) {
            if (!vertices.containsKey(vertex)) {
                vertices.put(vertex, counter++);
                return vertex;
            }
        }

        return null;
    }

    // adds and returns a new undirected edge between two vertices
    // Note: if the end vertices are not already in the graph
    // then copies of them are added as well
    @Override
    public Edge<E> addEdge(Vertex<E> vertex0, Vertex<E> vertex1) {
        if (!(vertices.containsKey(vertex0)) || !(vertices.containsKey(vertex1))) {
            //passed vertex does not exits in the graph
            throw new IllegalArgumentException();
        }
        
        int indexI = vertices.get(vertex0);
        int indexJ = vertices.get(vertex1);
        
        Edge edge = new AdjacencyMatrixEdge(vertex0, vertex1);
        if (edgeMatrix[indexI][indexJ] == null) {
            edgeMatrix[indexI][indexJ] = edge;
            edgeMatrix[indexJ][indexI] = edge;
        } else {
            //there is already an edge.
            throw new IllegalArgumentException();
        }
        
        
        return edge;
    }

    // removes the specified vertex from the graph
    // return true if vertex is successfully removed
    @Override
    public <F> boolean removeVertex(Vertex<F> vertex) {
        if (!containsVertex(vertex)) {
            return false;
        }
        int index = vertices.get(vertex);
        
        for (int i = 0; i < CAPACITY; i++) {
            edgeMatrix[i][index] = null;
            edgeMatrix[index][i] = null;
        }
        
        vertices.remove(vertex);
        return true;
    }

    // removes the specified undirected edge from the graph
    // return true if edge is successfully removed
    @Override
    public <F> boolean removeEdge(Edge<F> edge) {
        
        Vertex[] targetsVertices = edge.endVertices();
        int indexI = vertices.get(targetsVertices[0]);
        int indexJ = vertices.get(targetsVertices[1]);
        edgeMatrix[indexI][indexJ] = null;
        edgeMatrix[indexJ][indexI] = null;
        
        return true;
    }

    // returns whether the specified vertex is in the graph
    @Override
    public boolean containsVertex(Vertex<?> vertex) {
        return vertices.containsKey(vertex);
    }

    // returns whether the specified edge is in the graph
    @Override
    public boolean containsEdge(Edge<?> edge) {
        Vertex[] adjacentVertecies = edge.endVertices();
        boolean contains = true;
        int i = vertices.get(adjacentVertecies[0]);
        int j = vertices.get(adjacentVertecies[1]);
        
        if (edgeMatrix[i][j] == null || edgeMatrix[j][i] == null) {
            contains = false;
        }
        
        return contains;
    }

    @Override
    public String toString() {
        String output = "Graph:\n";
        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix[i].length; j++) {
                if (edgeMatrix[i][j] == null) {
                    output += "[null - null]" + "\t";
                } else {
                    output += "[" + edgeMatrix[i][j] +"]" + "\t";
                }
            }
            output += "\n";
        }
        return output;
    }
    
    private class AdjacencyMatrixVertex<E> implements Vertex<E> {

        private E element;

        public AdjacencyMatrixVertex(E element) {
            this.element = element;
        }
        
        @Override
        public E getUserObject() {
            return element;
        }

        @Override
        public void setUserObject(E element) {
            this.element = element;
        }

        @Override
        public Set<Edge<E>> incidentEdges() {
            int index = vertices.get(this);
            Set<Edge<E>> edgeList = new LinkedHashSet<>();
            for (int i = 0; i < edgeMatrix.length; i++) {
                if (edgeMatrix[i][index] != null) {
                    edgeList.add(edgeMatrix[i][index]);
                }
                if (edgeMatrix[index][i] != null) {
                    edgeList.add(edgeMatrix[index][i]);
                }
            }
            
            return edgeList;
        }

        @Override
        public Set<Vertex<E>> adjacentVertices() {
            Set<Edge<E>> edgeList = incidentEdges();
            Set<Vertex<E>> vertexList = new LinkedHashSet<>();
            for (Edge<E> edge : edgeList) {
                vertexList.add(edge.oppositeVertex(this));
            }
            return vertexList;
        }

        @Override
        public boolean isAdjacent(Vertex<?> vertex) {
            return adjacentVertices().contains(vertex);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex) {
                if (((Vertex) obj).getUserObject().equals(element)) {
                    return true;
                }
            }   
            return false;
        }
        
        public int hashCode() {
            if (element == null) {
                return 0;
            } else {
                return element.hashCode();
            }
      }

        @Override
        public String toString() {
            return element.toString();
        }
    }
    
    private class AdjacencyMatrixEdge<E> implements Edge<E> {

        Vertex<E> vertex1;
        Vertex<E> vertex2;

        public AdjacencyMatrixEdge(Vertex<E> vertex1, Vertex<E> vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
        
        @Override
        public Vertex<E>[] endVertices() {
            Vertex<E>[] pairs = (Vertex<E>[])new Vertex[2];
            pairs[0] = vertex1;
            pairs[1] = vertex2;
            return pairs;
        }

        @Override
        public Vertex<E> oppositeVertex(Vertex<E> vertex) {
            if (vertex1.equals(vertex)) {
                return vertex2;
            } else {
                return vertex1;
            }
        }

        @Override
        public String toString() {
            return "(" + vertex1 + "-" + vertex2 + ")";
        }
    }
    
}
