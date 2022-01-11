package ui;

import javax.swing.*;
import java.awt.*;
import java.lang.Math.*;

public class CaravanBar extends JPanel {
    CaravanBarPanel caravanBarPanel;
    int factor;

    // EFFECTS: constructs the caravanBar
    public CaravanBar(CaravanBarPanel caravanBarPanel) {
        this.caravanBarPanel = caravanBarPanel;
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(caravanBarPanel.getWidth(), caravanBarPanel.getHeight()));
        factor = 4;
        repaint();
    }

    // EFFECTS: paints an appropriately sized bar with colour depending on factor
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        if (factor == 1) {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, this.getWidth(), this.getHeight() - 18);
        } else if (factor == 3) {
            g.setColor(Color.RED);
            g.fillRect(0, this.getHeight() - this.getHeight() / 3, this.getWidth(),
                    this.getHeight() / 3 - 18);
        }  else if (factor == 2) {

            g.setColor(Color.RED);
            g.fillRect(0, this.getHeight() - (int)Math.round(this.getHeight() / 1.5), this.getWidth(),
                    (int)Math.round(this.getHeight() / 1.5) - 18);
        }


    }

    // EFFECTS: changes factor to given factor and calls paint()
    public void paintRectangle(int factor) {
        this.factor = factor;
        repaint();
    }
}
