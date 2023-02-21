import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)
    {
        final int WORLD_SCALE = 50, SCREEN_SIZE_WIDTH = 20, SCREEN_SIZE_HEIGHT = 10;

        GameObject
                leftBlock = new GameObject(WORLD_SCALE,WORLD_SCALE*5, Color.white, true),
                rightBlock = new GameObject(WORLD_SCALE,WORLD_SCALE*5, Color.white, true),
                centerBlockBottom = new GameObject(WORLD_SCALE/2,WORLD_SCALE*2, Color.white, true),
                centerBlockTop = new GameObject(WORLD_SCALE/2,WORLD_SCALE*2, Color.white, true),
                ball = new GameObject(WORLD_SCALE, WORLD_SCALE, Color.gray, false);

        leftBlock.setPosition(leftBlock.width/2, WORLD_SCALE*SCREEN_SIZE_HEIGHT/2);
        rightBlock.setPosition( WORLD_SCALE*SCREEN_SIZE_WIDTH, WORLD_SCALE*SCREEN_SIZE_HEIGHT/2);
        ball.setPosition(WORLD_SCALE*SCREEN_SIZE_WIDTH/2, WORLD_SCALE*SCREEN_SIZE_HEIGHT/2);
        JPanel panel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                setBackground(Color.black);

                leftBlock.draw(g);
                rightBlock.draw(g);
                centerBlockBottom.draw(g);
                centerBlockTop.draw(g);
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
        g.setColor(Color.red);
        g.drawRect(x, y, 1, 1);
    }
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}