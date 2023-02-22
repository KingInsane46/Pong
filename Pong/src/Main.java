import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static final int WORLD_SCALE = 50, SCREEN_SIZE_WIDTH = 20, SCREEN_SIZE_HEIGHT = 10;
    public static void main(String[] args) throws InterruptedException
    {
        GameObject
                leftBlock = new GameObject(WORLD_SCALE,WORLD_SCALE*5, Color.white, true),
                rightBlock = new GameObject(WORLD_SCALE,WORLD_SCALE*5, Color.white, true),
                ball = new GameObject(WORLD_SCALE, WORLD_SCALE, Color.white, false);

        leftBlock.setPosition(leftBlock.width/2, WORLD_SCALE*SCREEN_SIZE_HEIGHT/2);
        rightBlock.setPosition( WORLD_SCALE*SCREEN_SIZE_WIDTH- rightBlock.width, WORLD_SCALE*SCREEN_SIZE_HEIGHT/2);
        ball.setPosition(WORLD_SCALE*SCREEN_SIZE_WIDTH/2, WORLD_SCALE*SCREEN_SIZE_HEIGHT/2);
        JPanel panel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                setBackground(Color.black);

                leftBlock.draw(g);
                rightBlock.draw(g);
                ball.draw(g);
            }
        };

        JFrame frame = new JFrame("Pong");
        frame.add(panel);
        frame.setSize(WORLD_SCALE*SCREEN_SIZE_WIDTH, WORLD_SCALE*SCREEN_SIZE_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        int moveRightSpeed = 1, moveDownSpeed = 1, collision;
        while(true)
        {
            TimeUnit.MILLISECONDS.sleep(10);
            frame.repaint();
            ball.setPosition(ball.x + moveRightSpeed, ball.y + moveDownSpeed);

            collision = objectTouchingScreen(ball);
            if(collision == 1 || collision == 3)
                moveRightSpeed *= -1;
            if(collision == 2 || collision == 4)
                moveDownSpeed *= -1;

            collision = rightBlock.checkCollision(ball);
            if(collision == 1 || collision == 3)
                moveRightSpeed *= -1;
            if(collision == 2 || collision == 4)
                moveDownSpeed *= -1;

            collision = leftBlock.checkCollision(ball);
            if(collision == 1 || collision == 3)
                moveRightSpeed *= -1;
            if(collision == 2 || collision == 4)
                moveDownSpeed *= -1;
        }
    }

    static public int objectTouchingScreen(GameObject obj)
    {
        int     left = 0,
                top = 0,
                right = WORLD_SCALE*SCREEN_SIZE_WIDTH,
                bottom = WORLD_SCALE*SCREEN_SIZE_HEIGHT,

                objLeft = obj.x,
                objTop = obj.y,
                objRight = obj.x + obj.width,
                objBottom = obj.y + obj.height;

        if(left > objLeft)
            return 1;
        if(right < objRight)
            return 3;
        if(bottom < objBottom)
            return 2;
        if(top > objTop)
            return 4;
        return 0;
    }
}

class GameObject
{
    Color color;
    public int x, y, width, height;
    boolean rectangle;
    public GameObject(int width, int height, Color color, boolean rectangle)
    {
        this.width = width;
        this.height = height;
        this.color = color;
        this.rectangle = rectangle;
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        if(rectangle)
            g.fillRect(x-width/2, y-height/2, width, height);
        else
            g.fillOval(x-width/2, y-height/2, width, height);
    }
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int checkCollision(GameObject obj)
    {
        int     left = x,
                top = y,
                right = x + width,
                bottom = y + height,

                objLeft = obj.x,
                objTop = obj.y,
                objRight = obj.x + obj.width,
                objBottom = obj.y + obj.height;

        if(left < objLeft && right > objLeft && top < objTop && bottom > objTop)
            return 1;
        if(left < objRight && right > objRight && top < objTop && bottom > objTop)
            return 3;
        if(left < objLeft && right > objLeft && top < objBottom && bottom > objBottom)
            return 2;
        if(left < objRight && right > objRight && top < objBottom && bottom > objBottom)
            return 4;
        return 0;
    }
}