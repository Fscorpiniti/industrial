package ar.edu.untref.industrial.grafico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class GraficoMapa extends JComponent {

	private static final long serialVersionUID = 1L;
	private Point center;
	private double size = 2;
	private Graphics2D graphics2D;

	/**
	 * Recibe el punto del centro del grafico
	 * 
	 * @param center
	 */
	public GraficoMapa(Point center) {
		this.center = center;
	}

	public void doDrawing() {
		Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, this.getWidth(), this
				.getHeight());
		
		graphics2D.setColor(Color.white);
		graphics2D.fill(rectangle);
		
		graphics2D.setColor(Color.black);
		
		int j = 10;
		while (j < 100) {
			double result = j * size;

			drawEllipse(result);

			j = j + 10;
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

}