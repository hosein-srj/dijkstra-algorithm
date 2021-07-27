# dijkstra-algorithm
 
Dijkstra's algorithm  is an algorithm for finding the shortest paths between nodes in a graph, which may represent, for example, road networks. It was conceived by computer scientist Edsger W. Dijkstra in 1956 and published three years later.

The algorithm exists in many variants. Dijkstra's original algorithm found the shortest path between two given nodes, but a more common variant fixes a single node as the "source" node and finds shortest paths from the source to all other nodes in the graph, producing a shortest-path tree.

![image](https://user-images.githubusercontent.com/54143711/127174998-6412e9a4-6793-458a-852a-1607ee2fec77.png)

The programming language of this algorithm is JAVA and JavaFX for graphical visualization

The DijkstraApp is the Graph main class to start the application.

The class Edge is used to create a EdgeObject for the Graph.

The class Node is used to create a NodeObject for the Graph.

The Graph Controller initializes random vertices and edges including their name and weight representation and displays 
the whole as a graph and controls the user input (number of vertices, edges and their weight can be manipulated here).
If the Input was successful, the shortest path will be calculated via the Dijkstra class and displayed as text.

The class Dijkstra is the actual Dijkstra implementation in which a adjacency-list of predecessors with the smallest 
edge-weight starting with a given start node gets created and the shortest path according to a given destination node 
will be created. 

watch this video to understand how it works

![ezgif com-gif-maker (4)](https://user-images.githubusercontent.com/54143711/127209812-09cce084-00c9-4add-aa18-3b5261e9b556.gif)
