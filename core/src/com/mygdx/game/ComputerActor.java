package com.mygdx.game;

import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ComputerActor extends Actor {
	private boolean connect = false;
	private Sprite sprite;
	private boolean top = false;
	private boolean left = false;
	private boolean bottom = false;
	private boolean right = false;
	
	private ComputerActor CellU;
	private ComputerActor CellL;
	private ComputerActor CellD;
	private ComputerActor CellR;
	
	private String type;
	private String side;
	
	public void setNeighbours(ComputerActor u, ComputerActor l, ComputerActor d, ComputerActor r) {
		CellU = u;
		CellL = l;
		CellD = d;
		CellR = r;
	}
	
	public void setSide(String sideConnect) {
		side = sideConnect;
	}
	
	public String getSide() {
		if (side != null)
			return side;
		else return "error";
	}
	
	private void SwapPointsCollision() {
		boolean swap = right;
		right = bottom;
		bottom = left;
		left = top;
		top = swap;
	} 
	
	private void PrintPoints() {
		System.out.println(top);
		System.out.println(left);
		System.out.println(bottom);
		System.out.println(right);
	}
	
	
	public boolean getBoolTop() {
		return top;
	}
	public boolean getBoolLeft() {
		return left;
	}
	public boolean getBoolBottom() {
		return bottom;
	}
	public boolean getBoolRight() {
		return right;
	}
	
	public void SetActive() {
		connect = true;
		if (type.equals("computer"))
			sprite.setTexture(new Texture(Gdx.files.internal("data/network/computerActive.png")));
	}
	
	public void SetUnActive() {
		connect = false;
		if (type.equals("computer"))
			sprite.setTexture(new Texture(Gdx.files.internal("data/network/computer.png")));
	}
	
	public boolean GetActive() {
		return connect;
	}
	
	public String getType() {
		return type;
	}
	
	public ComputerActor(float x, float y, String name) {
	//	if (!connect) {
	//		texture = new Texture(Gdx.files.internal("data/network/computer.png"));
	//		sprite = new Sprite(texture);
	//	}
	//	else {
	//		texture = new Texture(Gdx.files.internal("data/network/computerActive.png"));
	//		sprite = new Sprite(texture);
	//	}
		side = new String("error");
		type = new String(name);
		if (type.equals("server"))
			connect = true;
		
		String path = "data/network/" + name + ".png";
		Texture texture = new Texture(Gdx.files.internal(path));
		sprite = new Sprite(texture);
		
		sprite.setX(x);
		sprite.setY(y);
		this.setX(x);
		this.setY(y);
		setBounds(this.getX(), this.getY(), sprite.getWidth(), sprite.getHeight());
		sprite.setOriginCenter();
		if (!name.equals("computer") && !name.equals("server") && !name.equals("cableFour")) {
			addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					sprite.rotate(90);
					SwapPointsCollision();
					SetUnActive();
					setSide("error");
					//PrintPoints();
					if (sprite.getRotation() == 360)
						sprite.setRotation(0);
					//System.out.println(sprite.getRotation());
					return true;
				}
			}); 
			if (name.equals("cable")) {
				top = true;
				left = false;
				bottom = true;
				right = false;
			}
			if (name.equals("cableLeft")) {
				top = false;
				left = true;
				bottom = true;
				right = false;
			}
			if (name.equals("cableTriod")) {
				top = true;
				left = true;
				bottom = false;
				right = true;
			}
			
			int rand = new Random(new Date().getTime()).nextInt(5);
			for (int i = 0; i < rand; i++) {
				sprite.rotate(90);
				SwapPointsCollision();
			}
		}
		else {
			top = true;
			left = true;
			bottom = true;
			right = true;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sprite, 
				   sprite.getX(),
				   sprite.getY(),
				   sprite.getOriginX(),
				   sprite.getOriginY(),
				   sprite.getWidth(),
				   sprite.getHeight(),
				   sprite.getScaleX(),
				   sprite.getScaleY(),
				   sprite.getRotation());
	}
}