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
						buffer.setNoEsq(novo); //atualiza ponteiro do node atual (buffer) para o novo node à esquerda
						break;
					}
				}else {
					if(buffer.getNoDir() != null) {
						buffer = buffer.getNoDir();
					}else {
						buffer.setNoDir(novo); //atualiza ponteiro do node atual (buffer) para o novo node à direita
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
	
	public void remover(T elemento) throws Exception {
		if(this.raiz == null)
			throw new Exception("A árvore está vazia");

		No<T> atual = this.raiz; //Buffer de atual começando em raiz
		No<T> pai = null; //Pai do atual. Começa em null pois a raiz não tem pai
		
		//Enquanto não achar o elemento e não terminar a árvore segue while
		while(atual != null && atual.getElemento() != elemento) { 
			pai = atual; //pai recebe o elemento atual, já que o atual receberá um novo node
			if(elemento.compareTo(atual.getElemento()) < 0) { //Se o compareTo retornar um número negativo, o elemento é menor do que o node
				atual = atual.getNoEsq(); //atual recebe esquerda pois elemento é menor
			}else {
				atual = atual.getNoDir(); //atual recebe direita pois elemento é maior
			}
		}
		
		if(atual == null) { //Se atual for null, a árvore foi percorrida e o elemento não existe
			throw new Exception("Elemento não encontrado");
		}else {
			removeNode(pai, atual); //se não, chama remoção de node
		}
	}
	
	private void removeNode(No<T> pai, No<T> atual) {
		/*
		 * Três condições de remoção
		 * 1 - Nó com dois filhos
		 * 2 - Nó com um filho
		 * 3 - Nó folha
		 */
		
		if(atual.getNoDir() != null && atual.getNoEsq() != null) { //Node possui dois filhos
			/*
			 * Para fazer a substituição de um nó que possui dois filhos
			 * uma das abordagens é usar o menor valor disponível à esquerda da subárvore à direita do node que será removido
			 */
			No<T> substituto = atual.getNoDir();
			No<T> paiDoSubsTituto = atual;
			while(substituto.getNoEsq() != null) {
				paiDoSubsTituto = substituto;
				substituto = substituto.getNoEsq(); //Busco o menor valor disponível para a substituição
			}
			
			//Redefinição de ponteiros. Referência para os filhos do antigo node
			substituto.setNoEsq(atual.getNoEsq());
			
			if(pai == null) { //Se não possuir pai, node é raiz da árvore
				this.raiz = substituto;
			}else {//Não é raiz
				if(substituto.getElemento().compareTo(pai.getElemento()) < 0) { //Substituto é menor que pai
					pai.setNoEsq(substituto);
					paiDoSubsTituto.setNoEsq(null);
				}else { //É maior que pai
					pai.setNoDir(substituto);
					paiDoSubsTituto.setNoDir(null);
				}
			}
		}else if(atual.getNoDir() == null && atual.getNoEsq() == null) { 
			/*
			 * Não possui filhos, é um nó folha
			 * Para remover um nó folha basta remover a referência do pai ao filho
			 */
			
			if(pai == null) { //É raiz
				this.raiz = null;
			}else { //Não é raiz
				if(atual.getElemento().compareTo(pai.getElemento()) < 0) { //Node a ser removido é menor que pai
					pai.setNoEsq(null);
					atual = null;
				}else { //é maior que o pai
					pai.setNoDir(null);
					atual = null;
				}
			}
		}else { 
			/*
			 * Possui apenas um filho
			 * Para remover um node que possui apenas um filho, deve-se mover o filho para a posição do atual.
			 */
			No<T> filho = null;
			if(pai != null) { //Não é raiz
				if (atual.getNoDir() != null) { // Possui filho à direita
					filho = atual.getNoDir();
					atual = null;
					if(filho.getElemento().compareTo(pai.getElemento()) < 0) {
						pai.setNoEsq(filho);
					}else {
						pai.setNoDir(filho);
					}
				} else { // Possui filho à esquerda
					filho = atual.getNoEsq();
					atual = null;
					if(filho.getElemento().compareTo(pai.getElemento()) < 0) {
						pai.setNoEsq(filho);
					}else {
						pai.setNoDir(filho);
					}
				}
			}else {//É raiz
				if (atual.getNoDir() != null) { // Possui filho à direita
					filho = atual.getNoDir();
					atual = null;
					this.raiz = filho;
				} else { // Possui filho à esquerda
					filho = atual.getNoEsq();
					atual = null;
					this.raiz = filho;
				}
			}
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
