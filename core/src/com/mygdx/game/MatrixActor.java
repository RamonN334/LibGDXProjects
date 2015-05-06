package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MatrixActor extends Group {
	private final int ROW = 7;
	private final int COLUMN = 6;
	private ComputerActor server;
	private LinkedList<ComputerActor> connectedCells;
	private boolean [][] isConnected;
	
	private ComputerActor [][] matrix;
	
	public MatrixActor() {
		matrix = new ComputerActor[ROW][COLUMN];
		connectedCells = new LinkedList<ComputerActor>();
		isConnected = new boolean[ROW][COLUMN];
	}
	
	public void addActor(int row, int column, ComputerActor obj) {
		if (row < ROW && column < COLUMN) {
			matrix[row][column] = obj;
			addActor(obj);
			if (obj.getType().equals("server")) {
				connectedCells.add(obj);
				server = obj;
			}
		}
	}
	
	public ComputerActor getActor(int row, int column) {
		if (row < ROW && column < COLUMN)
			return matrix[row][column];
		else
			return null;
	}
	
	public void Initilize() {
		ComputerActor u, l, d, r;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				u = l = d = r = null;
				if ((i + 1) < ROW)
					u = matrix[i + 1][j];
				if ((j - 1) >= 0)
					l = matrix[i][j - 1];
				if (i - 1 >= 0)
					d = matrix[i - 1][j];
				if ((j + 1) < COLUMN)
					r = matrix[i][j + 1];
				
				matrix[i][j].setNeighbours(u, l, d, r);
				
				
			}	
		}
		//updateConnection();
	}
	
	public void updateConnection() {
		for (int i = 0; i < ROW; i++)
			for (int j = 0; j < COLUMN; j++) {
				isConnected[i][j] = false;
				matrix[i][j].SetUnActive();
			}
		
		connectedCells.clear();
		isConnected[server.x()][server.y()] = true;
		connectedCells.add(server);
		
		while (!connectedCells.isEmpty()) {
			ComputerActor item = connectedCells.remove();
			
			for (ComputerActor.Dir d : ComputerActor.Dir.sides) {
				if (hasNewConnection(item, d, isConnected))
					connectedCells.add(item.next(d));
			}
		}
		
		for (int i = 0; i < ROW; i++)
			for (int j = 0; j < COLUMN; j++)
				matrix[i][j].setActive(isConnected[i][j]);
		
		
		
	}
	
	public boolean hasNewConnection(ComputerActor item, ComputerActor.Dir d, boolean got[][]) {
		ComputerActor other = item.next(d);
		ComputerActor.Dir otherDir = d.reverse;
		
		if (other == null || got[other.x()][other.y()])
			return false;
		
		if (!item.getSide(d) || !other.getSide(otherDir))
			return false;
		
		got[other.x()][other.y()] = true;
		return true;
	}
	
	public void checkConnections() {
//		for (int i = 0; i < ROW; i++) {
//			for (int j = 0; j < COLUMN; j++) {
//				//System.out.println("[" + i + ", " + j + "]" + " " + matrix[i][j].getType() + " " + matrix[i][j].GetActive() + " " + matrix[i][j].getSide());
//				//System.out.println("[" + 4 + ", " + 1 + "]" + " " + matrix[4][1].getType() + " " + matrix[4][1].GetActive() + " " + matrix[4][1].getSide());
//				//System.out.println("[" + 4 + ", " + 0 + "]" + " " + matrix[4][0].getType() + " " + matrix[4][0].GetActive() + " " + matrix[4][0].getSide());
//				//System.out.println("[" + 5 + ", " + 0 + "]" + " " + matrix[5][0].getType() + " " + matrix[5][0].GetActive() + " " + matrix[5][0].getSide());
//				if (i == 5 && j == 0 && matrix[5][0].getBoolBottom()) {
//					System.out.println("op");
//				}
//				if (!(matrix[i][j].getSide().equals("error"))) {
//					if (matrix[i][j].getSide().equals("top")) {
//						if (matrix[i][j].getBoolTop() && matrix[i][j].getBoolBottom())
//							continue;
//						else
//							matrix[i][j].SetUnActive();
//							matrix[i][j].setSide("error");
//					}
//					
//					if (matrix[i][j].getSide().equals("left")) {
//						if (matrix[i][j].getBoolLeft() && matrix[i][j].getBoolRight())
//							continue;
//						else
//							matrix[i][j].SetUnActive();
//							matrix[i][j].setSide("error");
//					}
//					
//					if (matrix[i][j].getSide().equals("bottom")) {
//						if (matrix[i][j].getBoolBottom() && matrix[i][j].getBoolTop())
//							continue;
//						else
//							matrix[i][j].SetUnActive();
//							matrix[i][j].setSide("error");
//					}
//					
//					if (matrix[i][j].getSide().equals("right")) {
//						if (matrix[i][j].getBoolRight() && matrix[i][j].getBoolLeft())
//							continue;
//						else
//							matrix[i][j].SetUnActive();
//							matrix[i][j].setSide("error");
//					}
//					
//				}
//				else {
//					if (!(matrix[i][j].getType().equals("server"))) {
//						if ((i + 1) < ROW) {
//							if (matrix[i][j].getBoolTop() && matrix[i + 1][j].getBoolBottom())
//								//if (matrix[i + 1][j].GetActive() && (!(matrix[i + 1][j].getSide().equals("error")))) {
//									//matrix[i][j].SetActive();
//									matrix[i][j].setSide("top");
//									continue;
//								}
//								//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i + 1][j].getType() + "[" + (i + 1) + ", " + j + "]" +" connected");
//								
//						}
//						
//						if ((j + 1) < COLUMN) {
//							if (matrix[i][j].getBoolRight() && matrix[i][j + 1].getBoolLeft())
//								//if (matrix[i][j + 1].GetActive() && (!(matrix[i][j + 1].getSide().equals("error")))) {
//									//matrix[i][j].SetActive();
//									matrix[i][j].setSide("right");
//									continue;
//								}
//								//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i][j + 1].getType() + "[" + i + ", " + (j + 1) + "]" +" connected");
//						}
//						if ((i - 1) > 0) {
//							if (matrix[i][j].getBoolBottom() && matrix[i - 1][j].getBoolTop())
//								//if (matrix[i - 1][j].GetActive() && (!(matrix[i - 1][j].getSide().equals("error")))) {
//									//matrix[i][j].SetActive();
//									matrix[i][j].setSide("bottom");
//									continue;
//								}
//								//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i - 1][j].getType() + "[" + (i - 1) + ", " + j + "]" +" connected");
//						}
//						if ((j - 1) > 0) {
//							if (matrix[i][j].getBoolLeft() && matrix[i][j - 1].getBoolRight())
//								if (matrix[i][j - 1].GetActive() && (!(matrix[i][j - 1].getSide().equals("error")))) {
//									//matrix[i][j].SetActive();
//									matrix[i][j].setSide("left");
//									continue;
//								}
//						}
//					}
//				}
//				//System.out.println("[" + i + ", " + j + "]" + " " + matrix[i][j].getType() + " " + matrix[i][j].GetActive() + " " + matrix[i][j].getSide());
//			}
//		}
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				if (matrix[i][j].getRotate()) {
					updateConnection();
					matrix[i][j].setRotate();
				}
			}
		}
	}
}
