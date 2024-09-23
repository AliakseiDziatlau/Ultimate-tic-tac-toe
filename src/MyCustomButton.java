import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyCustomButton extends JButton {
    public int column;
    public int row;
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius=20;

    public MyCustomButton(String text) {
        super(text);

        setColor(new Color(247, 218, 5));
        colorOver = new Color(228, 181, 2);
        colorClick = new Color(206, 164, 1);
        borderColor = new Color(8, 8, 8);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 4;

        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);
    }
}
