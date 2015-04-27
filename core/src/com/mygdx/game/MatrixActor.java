package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MatrixActor extends Group {
	private final int ROW = 7;
	private final int COLUMN = 6;
	private String coordServer;
	
	private ComputerActor [][] matrix;
	
	public MatrixActor() {
		matrix = new ComputerActor[ROW][COLUMN];
		coordServer = new String();
	}
	
	public void addActor(int row, int column, ComputerActor obj) {
		if (row < ROW && column < COLUMN) {
			matrix[row][column] = obj;
			addActor(obj);
			if (obj.getType().equals("server")) {
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
	
	public void checkConnections() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {	
				if (!(matrix[i][j].getType().equals("server"))) {
					//System.out.println("[" + i + ", " + j + "]" + " " + matrix[i][j].getType() + " " + matrix[i][j].GetActive());
					if ((i + 1) < ROW) {
						if (matrix[i][j].getBoolTop() && matrix[i + 1][j].getBoolBottom())
							if (matrix[i + 1][j].GetActive()) {
								matrix[i][j].SetActive();
								continue;
							}
							else
								matrix[i][j].SetUnActive();
							//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i + 1][j].getType() + "[" + (i + 1) + ", " + j + "]" +" connected");
							
					}
					if ((j + 1) < COLUMN) {
						if (matrix[i][j].getBoolRight() && matrix[i][j + 1].getBoolLeft())
							if (matrix[i][j + 1].GetActive()) {
								matrix[i][j].SetActive();
								continue;
							}
							else
								matrix[i][j].SetUnActive();
							//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i][j + 1].getType() + "[" + i + ", " + (j + 1) + "]" +" connected");
					}
					if ((i - 1) > 0) {
						if (matrix[i][j].getBoolBottom() && matrix[i - 1][j].getBoolTop())
							if (matrix[i - 1][j].GetActive()) {
								matrix[i][j].SetActive();
								continue;
							}
							else
								matrix[i][j].SetUnActive();
							//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i - 1][j].getType() + "[" + (i - 1) + ", " + j + "]" +" connected");
					}
					if ((j - 1) > 0) {
						if (matrix[i][j].getBoolLeft() && matrix[i][j - 1].getBoolRight())
							if (matrix[i][j - 1].GetActive()) {
								matrix[i][j].SetActive();
								continue;
							}
							else
								matrix[i][j].SetUnActive();
							//System.out.println(matrix[i][j].getType() + "[" + i + ", " + j + "]" + " and " + matrix[i][j - 1].getType() + "[" + i + ", " + (j - 1) + "]" +" connected");
					}
				}
			}
		}	
	}
}
