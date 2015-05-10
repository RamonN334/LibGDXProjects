package com.mygdx.game;

import java.util.Date;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

// Класс для элментов поля
public class CellActor extends Actor {
	private boolean connect = false;
	private Sprite sprite;
	private int xindex;
	private int yindex;
	
	// соседи элемента 
	private CellActor CellU;
	private CellActor CellL;
	private CellActor CellD;
	private CellActor CellR;
	
	private String type;
	
	enum Dir {
		U___, _R__, __D_, ___L;
		static final Dir[] sides = { U___, _R__, __D_, ___L };
		Dir reverse = null;
		static {
			U___.reverse = __D_;
			_R__.reverse = ___L;
			__D_.reverse = U___;
			___L.reverse = _R__;
		};
	};
	
	// получить соседний элемент
	CellActor next(Dir d) {
		switch (d) {
		case U___:
			return CellU;
		case _R__:
			return CellR;
		case __D_:
			return CellD;
		case ___L:
			return CellL;
		default:
			throw new RuntimeException("Method CellActor.next() got bad argument Dir d");	
		}
	}
	
	// установить ссылки на соседние элементы
	public void setNeighbours(CellActor u, CellActor l, CellActor d, CellActor r) {
		CellU = u;
		CellL = l;
		CellD = d;
		CellR = r;
	}
	
	// если элемент соединяется с соседним
	public void setActive(boolean b) {
		if (connect == b || type.equals("server")) return;
		else connect = b;
		if (type.equals("computer"))
			sprite.setTexture(new Texture(Gdx.files.internal("data/network/computerActive.png")));
	}
	
	// если не соединяется
	public void SetUnActive() {
		if (!type.equals("server")) {
			connect = false;
		}
		if (type.equals("computer"))
			sprite.setTexture(new Texture(Gdx.files.internal("data/network/computer.png")));
	}
	
	public boolean getActive() {
		return connect;
	}
	
	// получаем название элемента
	public String getType() {
		return type;
	}
	
	public int x() {
		return xindex;
	}
	
	public int y() {
		return yindex;
	}
	
	// если все компьютеры подключены к серверу, делаем его активным
	public void setActvieServer() {
		sprite.setTexture(new Texture(Gdx.files.internal("data/network/serverActive.png")));
	}
	
	
	public CellActor(int iindex, int jindex, float x, float y, TextureRegion image) {
		xindex = iindex;
		yindex = jindex;
		
		sprite = new Sprite(image);
	}
	
	public void changeSize() {
		setBounds(this.getX(), this.getY(), sprite.getWidth(), sprite.getHeight());
	}
	
	// отрисовка элемента
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