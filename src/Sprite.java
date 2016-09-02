import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

import javafx.geometry.Rectangle2D;

enum Direction {
	LEFT, RIGHT, UP, DOWN, NONE;
}

public class Sprite {
    private Image image;
    protected double positionX;
    protected double positionY;  
    protected double lastX;
    protected double lastY;
    protected double velocityX;
    protected double velocityY;
    protected double width;
    protected double height;
    
    protected int BITMASK;
    protected boolean alive = true;
    protected int health = 1;

    public Sprite() {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
        lastX = 0;
        lastY = 0;
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(getClass().getClassLoader().getResourceAsStream(filename));
        setImage(i);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
    	lastX = positionX;
        lastY = positionY;
        positionX += velocityX * time;
        positionY += velocityY * time;
    }
    
    public void lastPosition() {
    	positionX = lastX;
    	positionY = lastY;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
    
    public void handleCollision(Sprite s) {
    	if (health <= 0) {
    		alive = false;
    	}
    }
}