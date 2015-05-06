package ar.edu.untref.industrial.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ar.edu.untref.industrial.grafico.AccionVideo;
import ar.edu.untref.industrial.grafico.EstadoTimer;
import ar.edu.untref.industrial.grafico.GraficoMapa;
import ar.edu.untref.industrial.model.Timer;
import ar.edu.untref.industrial.parser.Parser;

public class Form extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private GraficoMapa graficoMapa;
	private Timer timer;
	private JPanel contentPane;

	public Form() {
		graficoMapa = new GraficoMapa(new Point(250, 250));
		timer = new Timer(this.graficoMapa, Parser.parsear(new File("src/test/resources/gps1.txt")));
		
		initUI();
	}

	private void initUI() {
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane,  new GridBagConstraints());
		scrollPane.setViewportView(this.graficoMapa);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		
		JPanel subPanel = new JPanel();
		panel.add(subPanel, new GridBagConstraints());
		subPanel.setLayout(new GridBagLayout());

		JButton buttonPlay = new JButton();
		buttonPlay.setText("PLAY");
		buttonPlay.setPreferredSize(new Dimension(90, 30));
		buttonPlay.setFocusable(false);
		buttonPlay.setBackground(Color.white);
		buttonPlay.setActionCommand(AccionVideo.PLAY.toString());
		buttonPlay.addActionListener(this);
		subPanel.add(buttonPlay, new GridBagConstraints());

		JButton buttonPause = new JButton();
		buttonPause.setText("PAUSA");
		buttonPause.setPreferredSize(new Dimension(90, 30));
		buttonPause.setIcon(new ImageIcon("imageIcon/pause.png"));
		buttonPause.setFocusable(false);
		buttonPause.setBackground(Color.white);
		buttonPause.setActionCommand(AccionVideo.PAUSA.toString());
		buttonPause.addActionListener(this);
		subPanel.add(buttonPause, new GridBagConstraints());

		timer.run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();

		if (name.equals(AccionVideo.PLAY.toString())) {
			this.timer.setEstado(EstadoTimer.CORRIENDO);
		} else if (name.equals(AccionVideo.PAUSA.toString())) {
			this.timer.setEstado(EstadoTimer.PAUSA);
		}
	}

}
