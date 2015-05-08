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

// Класс для элментов поля
public class CellActor extends Actor {
	private boolean connect = false;
	private Sprite sprite;
	private int xindex;
	private int yindex;
	private boolean top = false;
	private boolean left = false;
	private boolean bottom = false;
	private boolean right = false;
	private boolean isRotated = false;
	
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
	
	// получить true если элемент имеет выход в заданном направлении
	public boolean getSide(Dir d) {
		switch (d) {
		case U___:
			return getU();
		case _R__:
			return getR();
		case __D_:
			return getD();
		case ___L:
			return getL();
		default:
			throw new RuntimeException("Method CellActor.getSide() got bad argument Dir d");
		}
	}
	
	// установить ссылки на соседние элементы
	public void setNeighbours(CellActor u, CellActor l, CellActor d, CellActor r) {
		CellU = u;
		CellL = l;
		CellD = d;
		CellR = r;
	}
	
	// сбрасываем состояние того, что элемент повернут
	public void setRotate() {
		isRotated = false;
	}
	
	// возвращаем true, если элмент был повернут
	public boolean getRotate() {
		return isRotated;
	}
	
	// при поворачивании элемента меняем его выходы
	private void SwapPointsCollision() {
		boolean swap = right;
		right = bottom;
		bottom = left;
		left = top;
		top = swap;
	} 
	
	// если элемент имеет выход вверх, остальные по аналогии
	public boolean getU() {
		return top;
	}
	public boolean getL() {
		return left;
	}
	public boolean getD() {
		return bottom;
	}
	public boolean getR() {
		return right;
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
	
	
	public CellActor(int iindex, int jindex, float x, float y, String name) {
		xindex = iindex;
		yindex = jindex;
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
		if (!name.equals("computer") && !name.equals("server") && !name.equals("cableFour")) { // если элемент является не сервером и не компьютером, устанавлиаем на него обработки поворота
			addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					sprite.rotate(90);
					SwapPointsCollision();
					connect = false;
					isRotated = true;
					if (sprite.getRotation() == 360)
						sprite.setRotation(0);
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