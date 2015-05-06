package ar.edu.untref.industrial.model;

import java.util.List;
import java.util.TimerTask;

import ar.edu.untref.industrial.grafico.EstadoTimer;
import ar.edu.untref.industrial.grafico.GraficoMapa;

public class Timer extends TimerTask {

	private java.util.Timer timer;
	private GraficoMapa mapa;
	private EstadoTimer estado;
	private List<RowFileGps> rowsFile;
	private int rowRecorrida;

	public Timer(GraficoMapa mapa, List<RowFileGps> rowsFile) {
		this.timer = new java.util.Timer();
		this.mapa = mapa;
		this.setEstado(EstadoTimer.STOP);
		double periodo = (double) 1 / 50 * 1000;
		this.timer.schedule(this, 0, (long) periodo);
		this.rowsFile = rowsFile;
	}

	@Override
	public void run() {
		if (this.estado.equals(EstadoTimer.CORRIENDO) && (rowRecorrida < rowsFile.size() - 1)) {
			rowRecorrida = rowRecorrida + 1;
			this.mapa.setSatelites(rowsFile.get(rowRecorrida).getSatelites());
			this.mapa.repaint();
		}
	}

	public void setEstado(EstadoTimer estado) {
		this.estado = estado;
	}
}