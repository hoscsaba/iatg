import java.awt.*;
import java.awt.geom.Line2D;
import java.applet.Applet;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.Hashtable;

@SuppressWarnings("serial")
public class AtgEloperdulet extends JFrame implements ChangeListener {
	private JSlider slider;
	private JPanel controlpanel;
	private drawfig drawing;
	private JLabel message;

	public static void main(String[] args) {
		AtgEloperdulet app = new AtgEloperdulet();
        app.init();
    }
	
	public void init() {
		slider = new JSlider(JSlider.HORIZONTAL, 30, 120, 75);
		// Create the label table.
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		// PENDING: could use images, but we don't have any good ones.
		labelTable.put(new Integer(30), new JLabel("30 fok"));
		// new JLabel(createImageIcon("images/stop.gif")) );
		labelTable.put(new Integer(120), new JLabel("120 fok"));
		// new JLabel(createImageIcon("images/fast.gif")) );
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		slider.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		slider.addChangeListener(this);

		message = new JLabel("Elõperdület lapátrács szög: 75 fok",
				JLabel.CENTER);

		controlpanel = new JPanel();
		controlpanel.setLayout(new GridLayout(0, 1));

		controlpanel.add(message);
		controlpanel.add(slider);

		setLayout(new BorderLayout());
		add("North", controlpanel);

		drawing = new drawfig();
		drawing.setxMin(0);
		drawing.setxMax(1.8);
		drawing.setyMin(0);
		drawing.setyMax(2.3);
		drawing.setWidth(300);
		drawing.setHeight(200);
		drawing.setXlabel("Q");
		drawing.setYlabel("H");
		drawing.setShowTicks(false);
		drawing.setShowNumbers(false);
		drawing.setFi(slider.getValue());
		drawing.repaint();
		add("Center", drawing);

		setSize(new Dimension(400, 350));
		setVisible(true);

	}

	public void stateChanged(ChangeEvent ce) {
		double value = slider.getValue();
		message.setText("Elõperdület lapátrács szög: " + value + " fok");
		drawing.setFi(value);
		drawing.repaint();
	}
}