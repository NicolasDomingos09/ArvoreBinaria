package controller;
import model.estrutura.arvore.Arvore;

public class ArvoreController {
	public static void main(String[] args) {
		Arvore<Integer> arvore = new Arvore<Integer>();
		
		arvore.adicionar(20);
		arvore.adicionar(15);
		arvore.adicionar(13);
		arvore.adicionar(18);
		arvore.adicionar(31);
		arvore.adicionar(17);
		arvore.adicionar(22);
		arvore.adicionar(21);
		arvore.adicionar(30);
		arvore.adicionar(1);
		
		System.out.println(arvore.toString()+ "\n");
		//arvore.exibirEmOrdem();
		//arvore.exibirPosOrdem();
		//arvore.exibirPreOrdem();
		
		try {
			arvore.remover(30);
			arvore.exibirPreOrdem();
			
			arvore.remover(21);
			arvore.exibirEmOrdem();
			
			arvore.remover(31);
			arvore.exibirPreOrdem();
			
			arvore.remover(20);
			arvore.exibirPreOrdem();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
