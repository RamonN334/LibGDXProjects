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
	private Sprite sprite;
	private int xindex;
	private int yindex;
	private int x;
	private int y;
	
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
		
	public CellActor(int iindex, int jindex, float x, float y, TextureRegion image) {
		xindex = iindex;
		yindex = jindex;
		this.setX(x);
		this.setY(y);
		sprite = new Sprite(image);	
		sprite.setX(x);
		sprite.setY(y);
		setBounds(this.x, this.y, sprite.getWidth(), sprite.getHeight());
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