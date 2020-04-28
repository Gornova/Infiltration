package it.randomtower.infiltration.ui.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;

import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.model.poi.StatusEnum;
import it.randomtower.infiltration.ui.ImageButton;
import it.randomtower.infiltration.ui.poi.PoiPanel;

@SuppressWarnings("serial")
public class CityButton extends ImageButton {

	private PoiPanel poiPanel;
	private BasePoi poi;
	private BufferedImage cityRebellion;
	private BufferedImage cityImage;

	public CityButton(BasePoi p, BufferedImage cityImage, BufferedImage cityRollImage, BufferedImage cityRebellion,
			PoiPanel poiPanel) throws IOException {
		super(cityImage, cityRollImage, p.getName() + ", " + p.getNickname(), (int) p.getX() - cityImage.getWidth() / 2,
				(int) p.getY() + cityImage.getHeight() / 2, cityImage.getWidth(), cityImage.getHeight());
		this.poiPanel = poiPanel;
		this.poi = p;
		this.cityImage = cityImage;
		this.cityRebellion = cityRebellion;

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poiPanel.setPoi(poi);
				poiPanel.setVisible(true);
				poiPanel.getActorPanel().setVisible(false);
			}

		});
	}

	public void refresh() {
		if (poi.getStatus() == StatusEnum.REBELLION) {
			setIcon(new ImageIcon(cityRebellion));
		} else {
			setIcon(new ImageIcon(cityImage));
		}
		if (!poi.isHidden()) {
			setVisible(true);
		}
	}

}
