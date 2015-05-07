package ar.edu.untref.industrial.grafico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JComponent;

import ar.edu.untref.industrial.model.Satelite;

public class GraficoMapa extends JComponent {

	private static final long serialVersionUID = 1L;
	private Point center;
	private double size = 2;
	private Graphics2D graphics2D;
	private Boolean grabando;
	private List<Satelite>satelites;

	/**
	 * Recibe el punto del centro del grafico
	 * 
	 * @param center
	 */
	public GraficoMapa(Point center) {
		this.center = center;
	}

	public void doDrawing() {
		Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
		
		graphics2D.setColor(Color.white);
		graphics2D.fill(rectangle);
		
		graphics2D.setColor(Color.black);
		
		int j = 10;
		while (j < 100) {
			double result = j * size;

			drawEllipse(result);

			j = j + 10;
		}
		
		this.drawingSatellites();
	}

	private void drawingSatellites() {
		Image imgSatelite = Toolkit.getDefaultToolkit().getImage("src/test/resources/icono.gif");
		
		if (satelites != null) {
			for (Satelite unSatelite: satelites) {
				double elevacion = 90 - unSatelite.getElev();
				double azimuth = -unSatelite.getAz() + 90;
				
				double x = center.x + Math.cos(Math.toRadians(azimuth))* elevacion * size;
				double y = center.y - Math.sin(Math.toRadians(azimuth))* elevacion * size;
				
				Point2D.Double pointSat = new Point2D.Double(x, y);
				
				graphics2D.drawImage(imgSatelite, (int)pointSat.x, (int)pointSat.y, null);
			}
			
			if (grabando) {
//				 VideoImageHolder.getImages().add(bufferedImage);
			}
		}
	}

	private void drawEllipse(double result) {
		Ellipse2D.Double circle = new Ellipse2D.Double(center.x - result,
				center.y - result, 2 * result, 2 * result);

		graphics2D.draw(circle);
	}

	@Override
	public void paintComponent(Graphics g) {
		this.graphics2D = (Graphics2D) g;
		
		super.paintComponent(g);
		
		doDrawing();
	}

	public List<Satelite> getSatelites() {
		return satelites;
	}

	public void setSatelites(List<Satelite> satelites) {
		this.satelites = satelites;
	}
	
	public void update(List<Satelite> satelites, Boolean grabando) {
		this.satelites = satelites;
		this.grabando = grabando;
	}

}