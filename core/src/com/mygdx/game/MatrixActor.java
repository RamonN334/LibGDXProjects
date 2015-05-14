package com.mygdx.game;

import java.util.LinkedList;
import java.util.Vector;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MatrixActor extends Group {
	private final int ROW = 4;
	private final int COLUMN = 4;
	private LinkedList<CellActor> connectedCells;
	private boolean [][] isConnected;
	private Vector<CellActor> elements;
	
	private CellActor [][] matrix;
	
	public MatrixActor() {
		matrix = new CellActor[ROW][COLUMN];
		elements = new Vector<CellActor>();
		connectedCells = new LinkedList<CellActor>();
	}
	
	// добавление актера в матрицу
	public void addActor(int row, int column, CellActor obj) {
		if (row < ROW && column < COLUMN) {
			matrix[row][column] = obj;
			elements.add(obj);
		}
	}
	
	public void addActors() {
		for (int i = (ROW - 1); i >= 0; i--) {
			for (int j = (COLUMN - 1); j >= 0; j--)
				addActor(matrix[i][j]);
		}
	}
	
	// получение элемента
	public CellActor getActor(int row, int column) {
		if (row < ROW && column < COLUMN)
			return matrix[row][column];
		else
			return null;
	}
}
