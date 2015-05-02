package ar.edu.untref.industrial.formularios;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.untref.industrial.grafico.GraficoMapa;

public class Form extends JFrame {

	private static final long serialVersionUID = 1L;

	private GraficoMapa graficoMapa;
	private JPanel contentPane;

	public Form() {
		graficoMapa = new GraficoMapa(new Point(250, 250));
		
		initUI();
	}

	private void initUI() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(this.graficoMapa);
		
		setContentPane(contentPane);
	}

}
