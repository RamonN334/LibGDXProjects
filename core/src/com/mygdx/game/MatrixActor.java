package com.mygdx.game;

import java.util.LinkedList;
import java.util.Vector;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MatrixActor extends Group {
	private final int ROW = 7;
	private final int COLUMN = 6;
	private CellActor server;
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
			addActor(obj);
			elements.add(obj);
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
