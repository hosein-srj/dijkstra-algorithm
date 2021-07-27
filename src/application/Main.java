package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		Graph graph = new Graph();
		Group root = new Group();
		Scene scene = new Scene(root,1000,600);

		ImageView imageViewBG=new ImageView();
		Image imageBackground = new Image("file:Background2.jpg");
		imageViewBG.setImage(imageBackground);
		imageViewBG.setPreserveRatio(true);
		imageViewBG.setFitHeight(600);
		root.getChildren().add(imageViewBG);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		ArrayList<Circle> circles=new ArrayList<Circle>();
		ArrayList<Line> lines=new ArrayList<Line>();
		ArrayList<Text> texts = new ArrayList<Text>();
		Map< Circle, Node> circleToNode= new HashMap<Circle ,Node >();
		Map< Node, Circle> nodeToCircle= new HashMap<Node ,Circle >();
		ArrayList<Node> nodes=new ArrayList<Node>();
		ArrayList<Line> linesWays=new ArrayList<Line>();


		Button btn = new Button();
		btn.setText("Dijestera");
		btn.setTranslateX(350);
		btn.setTranslateY(20);
		root.getChildren().add(btn);

		btn.setOnAction((ActionEvent event) -> {

			Graph graph2 = calculateShortestPathFromSource(graph,nodes.get(0) );

			for(Line line:linesWays){
				root.getChildren().remove(line);
				lines.remove(line);
			}
			for(Node node : graph2.getNodes()){
				double x1,x2,y1,y2;
				ArrayList<Node> shortest = new ArrayList<>();
				for(Node node1 : node.getShortestPath()){

					shortest.add(node1);
				}
				shortest.add(node);

				if(shortest.size()==0)
					continue;
				for(int i=0;i<shortest.size()-1;i++){
					Circle circle1=nodeToCircle.get(shortest.get(i));
					x1=circle1.getTranslateX();
					y1=circle1.getTranslateY();
					Circle circle2=nodeToCircle.get(shortest.get(i+1));
					x2=circle2.getTranslateX();
					y2=circle2.getTranslateY();


					Line line=new Line(x1,y1,x2,y2);
					line.setStrokeWidth(5);
					line.setFill(Color.RED);
					line.setStroke(Color.RED);
					linesWays.add(line);
					root.getChildren().add(line);
					for(Circle circle : circles){
						root.getChildren().remove(circle);
						root.getChildren().add(circle);
					}
					for(Text text : texts){
						root.getChildren().remove(text);
						root.getChildren().add(text);
					}

				}


			}
        });

		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			double x,y;
	        @Override
	        public void handle(MouseEvent event1) {
	        	String string = "A";
	        	string=changeString(string,circles.size());

	        	boolean bool=true;
	        	for(Circle circle : circles){
	        		if(Math.abs(circle.getTranslateX()-event1.getSceneX())<=20 && Math.abs(circle.getTranslateY()-event1.getSceneY())<=20){
	        			bool=false;
	        			break;
	        		}
	        	}
	        	x=event1.getSceneX();
	            y=event1.getSceneY();
	        	if(bool==true){
		            Circle circle = new Circle(12);
		            circle.setTranslateX(event1.getSceneX());
		            circle.setTranslateY(event1.getSceneY());
		            circle.setFill(Color.WHITE);
		            circles.add(circle);
		            root.getChildren().add(circle);
		            Node node=new Node(string);
		            graph.addNode(node);
		            Text text = new Text(x-5, y+4, string);
		            //text.setFill(Color.WHITE);
		            texts.add(text);
		            root.getChildren().add(text);
		            circleToNode.put(circle, node);
		            nodeToCircle.put(node, circle);
		            nodes.add(node);
	        	}

	            scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    			@Override
	    			public void handle(MouseEvent event) {
	    				//if( Math.abs(event.getSceneX()-x)>12 && Math.abs(event.getSceneY()-y)>12){
		    				Circle sourceCircle=new Circle();

		    				sourceCircle=getCircle(circles,x,y);
							/*for(Circle circle  : circles){
								if(Math.abs(x-circle.getTranslateX())<=12  && Math.abs(y-circle.getTranslateY())<=12 ){
									sourceCircle=circle;
									break;
								}
							}*/
		    				for(Circle circle : circles){
		    					if(Math.abs(event.getSceneX()-circle.getTranslateX())<=12  && Math.abs(event.getSceneY()-circle.getTranslateY())<=12 &&
		    							!equalCircle(circle, sourceCircle)){
		    						Line line = new Line(sourceCircle.getTranslateX(),sourceCircle.getTranslateY(),circle.getTranslateX(),circle.getTranslateY());
		    						//line.setFill(Color.BLUE);
		    						line.setStrokeWidth(5);
		    						line.setStroke(Color.WHITE);
		    						root.getChildren().add(line);

		    						for(Text text : texts){
		    							root.getChildren().remove(text);
		    							root.getChildren().add(text);
		    						}
		    						lines.add(line);

		    						TextField value = new TextField();
		    						value.setText("");
		    						GridPane grid = new GridPane();
		    					    //grid.setVgap(5);
		    					    //grid.setHgap(10);
		    					    grid.setPadding(new Insets(30, 100, 20, 500));
		    					    Text text = new Text("Enter Weight : ");
		    					    text.setFill(Color.WHITE);
		    					    grid.add(text, 0, 0);
		    					    grid.add(value, 1, 0);
		    					    root.getChildren().add(grid);

		    					    //System.out.println(lines.size());
		    					    value.setOnKeyPressed(new EventHandler<KeyEvent>()
		    					    {
		    					        @Override
		    					        public void handle(KeyEvent ke)
		    					        {
		    					            if (ke.getCode().equals(KeyCode.ENTER))
		    					            {
		    					            	Circle sourceCircle = getCircle(circles,x,y);

		    					                int weigh =Integer.parseInt(value.getText());
		    					                root.getChildren().remove(grid);
		    					                Node sourceNode = circleToNode.get(sourceCircle);
		    					                Node targetNode = circleToNode.get(circle);
		    					                sourceNode.addDestination(targetNode, weigh);
		    					                //targetNode.addDestination(sourceNode,weigh);

		    					                double deltaY=sourceCircle.getTranslateY()-circle.getTranslateY();
		    					                double deltaX=sourceCircle.getTranslateX()-circle.getTranslateX();
		    					                double avgY=(sourceCircle.getTranslateY()+circle.getTranslateY())/2;
		    					                double avgX=(sourceCircle.getTranslateX()+circle.getTranslateX())/2;
		    					                double m=deltaY/deltaX;
		    					                double finalY= (-13 / Math.sqrt(1+m*m))+avgY;
		    					                double finalX= ((13*m)/ Math.sqrt(1+m*m))+avgX;
		    					                //System.out.println(finalX+ " " +finalY);
		    					                Text text = new Text( finalX-5 ,finalY+5,Integer.toString(weigh) );
		    					                text.setStyle("-fx-font: 20 arial;");
		    					                text.setFill(Color.YELLOW);

		    					                text.setRotate(Math.atan(m)*180/Math.PI);
		    					                root.getChildren().add(text);



		    					            }
		    					        }
		    					    });
		    					}
		    				}
	    				//}
	    			}


	    		});
	        }
	    });


	}

	private Circle getCircle(ArrayList<Circle> circles, double x, double y) {
		for(Circle circle  : circles){
			if(Math.abs(x-circle.getTranslateX())<=12  && Math.abs(y-circle.getTranslateY())<=12 ){
				return circle;
			}
		}
		return null;
	}

	protected String changeString(String string, int size) {
		char help;
		for(int i=0;i<size;i++){
    		help=string.charAt(0);
    		help++;
    		string="";
    		string+=help;
    	}
		return string;
	}


	public static void main(String[] args) {
		launch(args);
	}

	public static boolean equalCircle(Circle circle1,Circle circle2){
		if(circle1.getTranslateX()==circle2.getTranslateX() && circle1.getTranslateY()==circle2.getTranslateY() )
			return true;
		else
			return false;
	}
	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
	    source.setDistance(0);

	    Set<Node> settledNodes = new HashSet<>();
	    Set<Node> unsettledNodes = new HashSet<>();

	    unsettledNodes.add(source);

	    while (unsettledNodes.size() != 0) {
	        Node currentNode = getLowestDistanceNode(unsettledNodes);
	        unsettledNodes.remove(currentNode);
	        for (Entry < Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
	        	Node adjacentNode = adjacencyPair.getKey();
	        	Integer edgeWeight = adjacencyPair.getValue();
	            if (!settledNodes.contains(adjacentNode)) {
	                calculateMinDist(adjacentNode, edgeWeight, currentNode);
	                unsettledNodes.add(adjacentNode);
	            }
	        }
	        settledNodes.add(currentNode);
	    }
	    return graph;
	}

	private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
	    Node minDistanceNode = null;
	    int minDistance = Integer.MAX_VALUE;
	    for (Node node: unsettledNodes) {
	        int nodeDistance = node.getDistance();
	        if (nodeDistance < minDistance) {
	        	minDistance = nodeDistance;
	        	minDistanceNode = node;
	        }
	    }
	    return minDistanceNode;
	}

	private static void calculateMinDist(Node targetNode,Integer weigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + weigh <  targetNode.getDistance()) {
			targetNode.setDistance(sourceDistance + weigh);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			targetNode.setShortestPath(shortestPath);
		}
	}
}
