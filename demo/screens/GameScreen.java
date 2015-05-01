package com.mert.collision.screens;

import java.util.ArrayList;

import net.dermetfan.utils.libgdx.graphics.Box2DSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mert.collision.Assets;
import com.mert.framework.AbstractGame;
import com.mert.framework.models.TextActor;
import com.mert.framework.screens.AbstractGameScreen;
import com.mert.framework.utils.Meter;
import com.mert.framework.utils.Pixel;
import com.mert.framework.utils.Utils;

public class GameScreen extends AbstractGameScreen implements InputProcessor {

	private Sprite ball_blue;
	private Sprite ball_reds[];
	
	private Preferences prefs;
	
	private TextActor scoreActor;
	private float score;
	private float ballSize;
	private boolean gameStarted;
	private boolean redsActive;

	//Box2D Part
	private float width2d = Pixel.toMeter(getWidth()), height2d= Pixel.toMeter(getHeight());
	private float FPS = 1 / 60f;
	private Vector3 tmp = new Vector3();
	private Vector2 tmp2 = new Vector2();
	private int RED_SIZE;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private World world;
	private Body bodyBlue;
	private ArrayList<Body> reds;
	
	private MouseJointDef jointDef;
	private MouseJoint joint;
		
	public GameScreen(AbstractGame game) {
		super(game);
		prefs = Gdx.app.getPreferences("gamePrefs");
		gameStarted = false;
		redsActive = false;
		score = 0;
		ballSize = 72;
		RED_SIZE = 4;
		batch = (SpriteBatch) getStage().getSpriteBatch();
		camera = new OrthographicCamera();
		reds = new ArrayList<Body>();
		TextureRegion regionRed = getAtlas().findRegion(Assets.atlas_ball_red);
		ball_reds = new Box2DSprite[RED_SIZE];
		
		for(int i = 0; i < RED_SIZE;i++) {
			ball_reds[i] = new Box2DSprite(regionRed);
		}
		
		createWorld();
		createFrame();
		createBlueBall();
		createRedBalls();
		
		
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render() {
		if(gameStarted) {
			score += Gdx.graphics.getDeltaTime();
			scoreActor.setStr(Integer.toString((int)score));
		}
		
		if(gameStarted && !redsActive) {
			for(Body body: reds)
				body.applyLinearImpulse(generateRandomImpulse(), body.getPosition() ,true);
			redsActive = true;
		}
		world.step(FPS, 8, 3);
		//renderer.render(world, camera.combined);
		
		batch.begin();
		
		batch.draw(ball_blue, Meter.toPixel(bodyBlue.getPosition().x) + getWidth() / 2 - ballSize/2, Meter.toPixel(bodyBlue.getPosition().y) + getHeight() / 2 - ballSize/2,
				ballSize/2, ballSize/2, ballSize, ballSize, 1, 1, 0, false);
		
		for(int i = 0; i < RED_SIZE; i++) {
			batch.draw(ball_reds[i], Meter.toPixel(reds.get(i).getPosition().x) + getWidth() / 2 - ballSize /2,
					Meter.toPixel(reds.get(i).getPosition().y) + getHeight() / 2 - ballSize / 2, ballSize / 2, ballSize /2, ballSize, ballSize, 1, 1, 0, false);
			//ball_reds[i].draw(batch, reds.get(i));
		}
		batch.end();
	//	Utils.showFPS();
	}
	
	
	private Vector2 generateRandomImpulse() {
		Vector2 impulse = new Vector2();
		impulse.x = Utils.randomIntN(-30, 30);
		impulse.y = Utils.randomIntN(-30, 30);
		return impulse;
	}

	@Override
	public void setUp() {
		setBackground(Assets.atlas_background);
		
		//Ball Blue Settings
		TextureRegion regionBlue = getAtlas().findRegion(Assets.atlas_ball_blue);
		ball_blue = new Sprite(regionBlue);
		//Score Settings
		scoreActor = new TextActor(getAssetManager().get(Assets.font, BitmapFont.class), Color.CYAN, "0", -20, getHeight() * 0.95f, 64, 64, 1, 1);
		scoreActor.addAction(Actions.moveBy(40, 0, 0.3f));
		getStage().addActor(scoreActor);
	}

	private void createWorld() {
		
		world = new World(new Vector2(0,0), true);
		world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}
			
			@Override
			public void endContact(Contact contact) {
			}
			
			@Override
			public void beginContact(Contact contact) {
				Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if(fixtureA.isSensor() || fixtureB.isSensor()) {
                	if(fixtureA.getBody().getType() == BodyType.DynamicBody || fixtureB.getBody().getType() == BodyType.DynamicBody) {
	                	FPS = 0;
	                	gameStarted = false;
	                	Gdx.input.setInputProcessor(null);
	                	
	                	for(int i = 0; i < RED_SIZE; i++) {
	                		
	                		world.setGravity(new Vector2(0, -30f));
	            			scoreActor.addAction(Actions.sequence(Actions.delay(1f), Actions.run(new Runnable() {
								
								@Override
								public void run() {
									prefs.putInteger("score", (int) score);
									prefs.flush();
									if(score > prefs.getInteger("best", 0)) {
										prefs.putInteger("best", (int)score);
										prefs.flush();
									}
										
									getGame().setScreen(new GameoverScreen(getGame()));
								}
							})));
	            		}
                	}
                	
                }
                	
			}
		});
		
		jointDef = new MouseJointDef();
		jointDef.bodyA = world.createBody(new BodyDef());
		jointDef.collideConnected = true;
		jointDef.maxForce = 0;
		
	}

	private void createRedBalls() {
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(Pixel.toMeter(ballSize/2));
		
		for (int i = 0; i < 4; i++) {
			// Create the BodyDef, set a random position above the
			// ground and create a new body
			BodyDef ballBodyDef = new BodyDef();
			ballBodyDef.type = BodyType.DynamicBody;
			
			float posX = 0;
			float posY = 0;
			
			switch (i) {
			case 0:
				posX = -width2d / 2 + circleShape.getRadius() * 3;
				posY =  height2d / 2 - circleShape.getRadius() * 3;
				break;
			case 1:
				posX = width2d / 2 - circleShape.getRadius() * 3;
				posY = height2d / 2 - circleShape.getRadius() * 3;
				break;
			case 2:
				posX = -width2d / 2 + circleShape.getRadius() * 3;
				posY = -height2d / 2 + circleShape.getRadius() * 3;
				break;
			case 3:
				posX = width2d / 2 - circleShape.getRadius() * 3;
				posY = -height2d / 2 + circleShape.getRadius() * 3;
				break;
			default:
				break;
			}
			
			ballBodyDef.position.x = posX;
			ballBodyDef.position.y = posY;
			Body bodyRed = world.createBody(ballBodyDef);
			
			FixtureDef fixDef = new FixtureDef();
			fixDef.shape = circleShape;
			fixDef.density = 1f;
			fixDef.restitution = 1;
			fixDef.friction = 0;
			
			Fixture fixture =bodyRed.createFixture(fixDef);
			
			bodyRed.setUserData(ball_reds[i]);
			fixture.setUserData(ball_reds[i]);
		
			// add the box to our list of boxes
			reds.add(bodyRed);
		}
		
		circleShape.dispose();
	}

	private void createBlueBall() {
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(Pixel.toMeter(ballSize / 2));
		
		BodyDef ballBlueBodyDef = new BodyDef();
		ballBlueBodyDef.type = BodyType.DynamicBody;
		ballBlueBodyDef.position.x = 0;
		ballBlueBodyDef.position.y = 0;
		
		bodyBlue = world.createBody(ballBlueBodyDef);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = circleShape;
		fixDef.density = 1f;
		fixDef.restitution = 1;
		fixDef.friction = 0;
		fixDef.isSensor = true;
		
		Fixture fixture = bodyBlue.createFixture(fixDef);
		fixture.setUserData(ball_blue);
		bodyBlue.setUserData(ball_blue);
		circleShape.dispose();
	}

	private void createFrame() {
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		
		EdgeShape botShape = new EdgeShape();
		botShape.set(-width2d / 2, -height2d / 2, width2d /2, -height2d / 2);

		fixtureDef.shape = botShape;
		fixtureDef.density = 0.1f;
		fixtureDef.restitution = 1;
		fixtureDef.friction = 0;
		
		world.createBody(bodyDef).createFixture(fixtureDef);

		EdgeShape leftShape = new EdgeShape();
		leftShape.set(-width2d / 2, -height2d / 2, -width2d /2, height2d / 2);
		
		fixtureDef.shape = leftShape;
		
		world.createBody(bodyDef).createFixture(fixtureDef);
		
		EdgeShape topShape = new EdgeShape();
		topShape.set(-width2d / 2, height2d / 2, width2d /2, height2d / 2);
		
		fixtureDef.shape = topShape;
		
		world.createBody(bodyDef).createFixture(fixtureDef);
		
		EdgeShape rightShape = new EdgeShape();
		rightShape.set(width2d / 2, -height2d / 2, width2d /2, height2d / 2);
		
		fixtureDef.shape = rightShape;
		
		world.createBody(bodyDef).createFixture(fixtureDef);
	}

	
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = Pixel.toMeter(width);
		camera.viewportHeight = Pixel.toMeter(height);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
	}

	

	private QueryCallback queryCallback = new QueryCallback() {

		@Override
		public boolean reportFixture(Fixture fixture) {
			if(!fixture.testPoint(tmp.x, tmp.y))
				return true;

			jointDef.bodyB = fixture.getBody();
			jointDef.target.set(tmp.x, tmp.y);
			joint = (MouseJoint) world.createJoint(jointDef);
			return false;
		}

	};

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(tmp.set(screenX, screenY, 0));
		
		world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(joint == null)
			return false;
		gameStarted = true;
		
		camera.unproject(tmp.set(screenX, screenY, 0));
		bodyBlue.setTransform(tmp2.set(tmp.x, tmp.y),0);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(joint == null)
			return false;

		world.destroyJoint(joint);
		joint = null;
		return true;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}
}
