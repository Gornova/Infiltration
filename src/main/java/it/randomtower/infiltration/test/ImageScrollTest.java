package it.randomtower.infiltration.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;

@SuppressWarnings("serial")
public class ImageScrollTest extends JPanel implements Scrollable {

	private static final int N = 8;
	private static final int W = 60;
	private static final int H = 100;
	private final FlowLayout layout = new FlowLayout();
	private final int hGap = layout.getHgap();
	private final int vGap = layout.getVgap();
	private final Dimension size;

	// Show n of N images in a Scrollable FlowLayout
	public ImageScrollTest(int n) {
		setLayout(layout);
		for (int i = 0; i < N; i++) {
			this.add(new ImagePanel());
		}
		size = new Dimension(n * W + (n + 1) * hGap, H + 2 * vGap);
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return size;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return getIncrement(orientation);
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return getIncrement(orientation);
	}

	private int getIncrement(int orientation) {
		if (orientation == JScrollBar.HORIZONTAL) {
			return W + hGap;
		} else {
			return H + vGap;
		}
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	private static class ImagePanel extends JPanel {

		private static final Random rnd = new Random();
		private Color color = new Color(rnd.nextInt());

		public ImagePanel() {
			this.setBackground(color);
			this.setBorder(BorderFactory.createLineBorder(Color.blue));
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(W, H);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageScrollTest ist = new ImageScrollTest(N / 2);
		JScrollPane sp = new JScrollPane(ist);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		f.add(sp);
		f.pack();
		f.setVisible(true);
	}
}