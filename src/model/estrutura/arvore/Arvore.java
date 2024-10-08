package model.estrutura.arvore;

public class Arvore<T extends Comparable<T>> {
	/*
	 * Uso do Comparable
	 * compareTo() pode retornar 3 valores
	 * retorna -1 se o valor comparado é menor
	 * retorna 0 se o valor comparado é igual
	 * retorna 1 se o valor comparado é maior
	 */
	
	private No<T> raiz;
	
	public Arvore() {
		this.raiz = null;
	}
	
	public void adicionar(T elemento) {
		No<T> novo = new No<>(elemento);
		
		if(this.raiz == null) {
			this.raiz = novo; //Adiciona nova raiz caso a arvore esteja vazia
		}else {
			No<T> buffer = this.raiz;
			
			while(buffer != null) { //Busca onde o novo node será colocado e adiciona
				if(novo.getElemento().compareTo(buffer.getElemento()) < 0 ) { //Testa se o novo elemento é menor que o atual
					if(buffer.getNoEsq() != null) {
						buffer = buffer.getNoEsq();
					}else {
						buffer.setNoEsq(novo); //Atualiza ponteiro do node atual (buffer) para o novo node à esquerda
						break;
					}
				}else {
					if(buffer.getNoDir() != null) {
						buffer = buffer.getNoDir();
					}else {
						buffer.setNoDir(novo); //Atualiza ponteiro do node atual (buffer) para o novo node à direita
						break;
					}
				}
			}
		}
	}
	
	public void exibirEmOrdem() { 
		//A exibição em ordem mostra a arvore completa em ordem crescente
		ordem(this.raiz);
		System.out.println();
	}
	
	private void ordem(No<T> atual) {
		if(atual != null) { //Se o atual não é null, método continua
			ordem(atual.getNoEsq()); //Chamada recursiva do método para buscar o elemento da esquerda
			System.out.print(atual.getElemento() + " "); //Print elemento
			ordem(atual.getNoDir()); //Chamada recursiva do método para buscar o elemento da direita
		}
	}
	
	public void exibirPreOrdem() {
		/*
		 * A exibição em pre-ordem mostra a arvore organizada por subarvores
		 * Printa primeiro o pai, depois o menor e por fim o maior
		 */
		preOrdem(this.raiz);
		System.out.println();
	}
	
	private void preOrdem(No<T> atual) {
		if(atual != null) { //Se o atual não é null, método continua
			System.out.print(atual.getElemento() + " "); //Print elemento pré-busca
			preOrdem(atual.getNoEsq()); //Chamada recursiva do método para buscar o elemento da esquerda
			preOrdem(atual.getNoDir()); //Chamada recursiva do método para buscar o elemento da direita
		}
	}
	
	public void exibirPosOrdem() {
		/*
		 * A exibição em pos-ordem mostra a arvore do nível mais profundo até o topo
		 * Printa primeiro o filho menor, depois o maior e por fim o pai
		 */
		posOrdem(this.raiz);
		System.out.println();
	}
	
	private void posOrdem(No<T> atual) {
		if(atual != null) { //Se o atual não é null, método continua
			posOrdem(atual.getNoEsq()); //Chamada recursiva do método para buscar o elemento da esquerda
			posOrdem(atual.getNoDir()); //Chamada recursiva do método para buscar o elemento da direita
			System.out.print(atual.getElemento() + " "); //Print elemento pós-busca
		}
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringHelper(raiz, sb);
        return sb.toString();
    }

    private void toStringHelper(No<T> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.getElemento()).append(" ");
            toStringHelper(node.getNoEsq(), sb);
            toStringHelper(node.getNoDir(), sb);
        }
    }
}
