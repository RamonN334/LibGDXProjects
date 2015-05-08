package com.mygdx.game;

import java.util.LinkedList;
import java.util.Vector;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MatrixActor extends Group {
	private final int ROW = 7;
	private final int COLUMN = 6;
	private ComputerActor server;
	private LinkedList<ComputerActor> connectedCells;
	private boolean [][] isConnected;
	private Vector<ComputerActor> computers;
	
	private ComputerActor [][] matrix;
	
	public MatrixActor() {
		matrix = new ComputerActor[ROW][COLUMN];
		connectedCells = new LinkedList<ComputerActor>();
		isConnected = new boolean[ROW][COLUMN];
		computers = new Vector<ComputerActor>();
	}
	
	// добавление актера в матрицу
	public void addActor(int row, int column, ComputerActor obj) {
		if (row < ROW && column < COLUMN) {
			matrix[row][column] = obj;
			addActor(obj);
			if (obj.getType().equals("server")) {
				connectedCells.add(obj);
				server = obj;
			}
			
			if (obj.getType().equals("computer"))
				computers.add(obj);
		}
	}
	
	// получение элемента
	public ComputerActor getActor(int row, int column) {
		if (row < ROW && column < COLUMN)
			return matrix[row][column];
		else
			return null;
	}
	
	// устанвливаем соседей и обновл€ем состо€ни€ элементов
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
		updateConnection();
	}
	
	// ищем соединени€ элементов
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
	
	// если соединение есть
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
	
	// провер€ем, если какой-либо элемент повернулс€, то заново провер€ем все соедени€
	public boolean checkConnections() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				if (matrix[i][j].getRotate()) {
					updateConnection();
					matrix[i][j].setRotate();
				}
			}
		}
		for (ComputerActor it : computers) {
			if (it.getActive())
				continue;
			else return false;
		}
		
		server.setActvieServer();
		return true;
	}
	
}
