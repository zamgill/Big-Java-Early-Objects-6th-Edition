package CH22;

import javax.swing.JComponent;
import CH14.ArrayUtil;
import java.awt.Graphics;

public class SelectionSortComponent extends JComponent {

    private SelectionSorter sorter;

    public SelectionSortComponent() {
        int[] values = ArrayUtil.randomIntArray(30, 300);
        sorter = new SelectionSorter(values, this);
    }

    public void paintComponent(Graphics g) {
        sorter.draw(g);
    }

    public void startAnimation() {
        class AnimationRunnable implements Runnable {
            public void run() {
                try {
                    sorter.sort();
                } catch (InterruptedException exception) {
                    System.out.println("Error: " + exception);
                }
            }
        }

        Runnable r = new AnimationRunnable();
        Thread t = new Thread(r);
        t.start();
    }
}