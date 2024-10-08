package model.estrutura.arvore;

public class No<T extends Comparable<T>> {
	
	private T elemento;
	private No<T> noDir;
	private No<T> noEsq;
	
	public No(T elemento) {
		this.elemento = elemento;
		this.noDir = this.noEsq = null;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public No<T> getNoDir() {
		return noDir;
	}

	public void setNoDir(No<T> noDir) {
		this.noDir = noDir;
	}

	public No<T> getNoEsq() {
		return noEsq;
	}

	public void setNoEsq(No<T> noEsq) {
		this.noEsq = noEsq;
	}

	@Override
	public String toString() {
		return elemento.toString();
	}
}
