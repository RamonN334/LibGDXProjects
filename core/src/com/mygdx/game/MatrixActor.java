package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MatrixActor extends Group {
	private final int ROW = 7;
	private final int COLUMN = 6;
	private String coordServer;
	private LinkedList<ComputerActor> connectedCells;
	
	private ComputerActor [][] matrix;
	
	public MatrixActor() {
		matrix = new ComputerActor[ROW][COLUMN];
		coordServer = new String();
		connectedCells = new LinkedList<ComputerActor>();
	}
	
	public void addActor(int row, int column, ComputerActor obj) {
		if (row < ROW && column < COLUMN) {
			matrix[row][column] = obj;
			addActor(obj);
			if (obj.getType().equals("server")) {
				connectedCells.add(obj);
				coordServer += row;
				coordServer += column;
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
		u = l = d = r = null;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
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
	}
	
	public void checkConnections() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				//System.out.println("[" + i + ", " + j + "]" + " " + matrix[i][j].getType() + " " + matrix[i][j].GetActive() + " " + matrix[i][j].getSide());
				System.out.println("[" + 4 + ", " + 1 + "]" + " " + matrix[4][1].getType() + " " + matrix[4][1].GetActive() + " " + matrix[4][1].getSide());
				System.out.println("[" + 4 + ", " + 0 + "]" + " " + matrix[4][0].getType() + " " + matrix[4][0].GetActive() + " " + matrix[4][0].getSide());
				System.out.println("[" + 5 + ", " + 0 + "]" + " " + matrix[5][0].getType() + " " + matrix[5][0].GetActive() + " " + matrix[5][0].getSide());
				if (i == 5 && j == 0 && matrix[5][0].getBoolBottom()) {
					System.out.println("op");
				}
				if (!(matrix[i][j].getSide().equals("error"))) {
					if (matrix[i][j].getSide().equals("top")) {
						if (matrix[i][j].getBoolTop() && matrix[i][j].getBoolBottom())
							continue;
						else
							matrix[i][j].SetUnActive();
							matrix[i][j].setSide("error");
					}
					
					if (matrix[i][j].getSide().equals("left")) {
						if (matrix[i][j].getBoolLeft() && matrix[i][j].getBoolRight())
							continue;
						else
							matrix[i][j].SetUnActive();
							matrix[i][j].setSide("error");
					}
					
					if (matrix[i][j].getSide().equals("bottom")) {
						if (matrix[i][j].getBoolBottom() && matrix[i][j].getBoolTop())
							continue;
						else
							matrix[i][j].SetUnActive();
							matrix[i][j].setSide("error");
					}
					
					if (matrix[i][j].getSide().equals("right")) {
						if (matrix[i][j].getBoolRight() && matrix[i][j].getBoolLeft())
							continue;
						else
							matrix[i][j].SetUnActive();
							matrix[i][j].setSide("error");
					}
					
				}
				else {
					if (!(matrix[i][j].getType().equals("server"))) {
						if ((i + 1) < ROW) {
							if (matrix[i][j].getBoolTop() && matrix[i + 1][j].getBoolBottom())
								if (matrix[i + 1][j].GetActive() && (!(matrix[i + 1][j].getSide().equals("error")))) {
									matrix[i][j].SetActive();
									matrix[i][j].setSide("top");
									continue;
								}
								//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i + 1][j].getType() + "[" + (i + 1) + ", " + j + "]" +" connected");
								
						}
						
						if ((j + 1) < COLUMN) {
							if (matrix[i][j].getBoolRight() && matrix[i][j + 1].getBoolLeft())
								if (matrix[i][j + 1].GetActive() && (!(matrix[i][j + 1].getSide().equals("error")))) {
									matrix[i][j].SetActive();
									matrix[i][j].setSide("right");
									continue;
								}
								//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i][j + 1].getType() + "[" + i + ", " + (j + 1) + "]" +" connected");
						}
						if ((i - 1) > 0) {
							if (matrix[i][j].getBoolBottom() && matrix[i - 1][j].getBoolTop())
								if (matrix[i - 1][j].GetActive() && (!(matrix[i - 1][j].getSide().equals("error")))) {
									matrix[i][j].SetActive();
									matrix[i][j].setSide("bottom");
									continue;
								}
								//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i - 1][j].getType() + "[" + (i - 1) + ", " + j + "]" +" connected");
						}
						if ((j - 1) > 0) {
							if (matrix[i][j].getBoolLeft() && matrix[i][j - 1].getBoolRight())
								if (matrix[i][j - 1].GetActive() && (!(matrix[i][j - 1].getSide().equals("error")))) {
									matrix[i][j].SetActive();
									matrix[i][j].setSide("left");
									continue;
								}
						}
					}
				}
				//System.out.println("[" + i + ", " + j + "]" + " " + matrix[i][j].getType() + " " + matrix[i][j].GetActive() + " " + matrix[i][j].getSide());
			}
		}
	}
}
