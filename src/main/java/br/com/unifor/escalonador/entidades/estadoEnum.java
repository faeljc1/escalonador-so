package br.com.unifor.escalonador.entidades;

public enum estadoEnum {
	PRONTO(1), ESPERANDO(2), EXECUTANDO(3);

	private final int estado;

	private estadoEnum(int valor) {
		estado = valor;
	}

	public int getEstado() {
		return estado;
	}

}
