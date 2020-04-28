package it.randomtower.infiltration.ui;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ToolTipUI;

public final class MultipleLineTooltips extends ToolTipUI {

	private static final int INSET = 2;

	private static final Pattern LINE_SPLITTER = Pattern.compile("$", Pattern.MULTILINE);

	private static final MultipleLineTooltips SHARED_INSTANCE = new MultipleLineTooltips();

	/**
	 * Install the multi-line tooltip into the UI manager.
	 */
	public static void installUI() {
		String toolTipUI = MultipleLineTooltips.class.getName();
		UIManager.put("ToolTipUI", toolTipUI);
		UIManager.put(toolTipUI, MultipleLineTooltips.class);
	}

	public static ComponentUI createUI(JComponent c) {
		return SHARED_INSTANCE;
	}

	private MultipleLineTooltips() {
	}

	@Override
	public Dimension getMaximumSize(JComponent c) {
		return getPreferredSize(c);
	}

	@Override
	public Dimension getMinimumSize(JComponent c) {
		return getPreferredSize(c);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		if (c == null) {
			return new Dimension(0, 0);
		}
		String[] lines = LINE_SPLITTER.split(((JToolTip) c).getTipText());
		if (lines.length == 0) {
			return new Dimension(2 * INSET, 2 * INSET);
		}
		FontMetrics metrics = c.getFontMetrics(c.getFont());
		Graphics g = c.getGraphics();
		int w = 0;
		for (String line : lines) {
			w = Math.max(w, (int) metrics.getStringBounds(line, g).getWidth());
		}
		int h = lines.length * metrics.getHeight();
		return new Dimension(w + 2 * INSET, h + 2 * INSET);
	}

	@Override
	public void installUI(JComponent c) {
		LookAndFeel.installColorsAndFont(c, "ToolTip.background", "ToolTip.foreground", "ToolTip.font");
		LookAndFeel.installBorder(c, "ToolTip.border");
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		int w = c.getWidth(), h = c.getHeight();
		g.setColor(c.getBackground());
		g.fillRect(0, 0, w, h);
		g.setColor(c.getForeground());
		g.drawRect(0, 0, w, h);
		String[] lines = LINE_SPLITTER.split(((JToolTip) c).getTipText());
		if (lines.length != 0) {
			FontMetrics metrics = c.getFontMetrics(c.getFont());
			int height = metrics.getHeight();
			int y = INSET + metrics.getAscent();
			for (String line : lines) {
				g.drawString(line, INSET, y);
				y += height;
			}
		}
	}

	@Override
	public void uninstallUI(JComponent c) {
		LookAndFeel.uninstallBorder(c);
	}

}