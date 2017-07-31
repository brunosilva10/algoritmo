package br.com.desafio;
import java.util.ArrayList;
import java.util.List;

/* 
 * Ao ler o enunciado e o exemplo, tive uma dúvida, pois analisando a frase “No exemplo, "e" é o primeiro 
 * caracter Vogal da stream que não se repete após a primeira Consoante "f" o qual tem uma vogal "a" como 
 * antecessora” para o input aAbBABacafe, percebi que a Vogal "a" adequa-se corretamente na regra, veja:
 * 
 * aAbBABacafe
 * 
 * “No exemplo, "a" é o primeiro caracter Vogal da stream que não se repete após a primeira 
 * Consoante "c" o qual tem uma vogal "a" como antecessora” 
 * 
 * Tendo isso, vejo uma possibilidade para que o exemplo do desafio esteja correto, sendo:
 * 
 * A vogal não deve se repetir durante toda a stream, e não apenas não se repetir após o 
 * encontro da consoante. Neste caso, "e" é a resposta correta, pois no trecho "aca", o "a" já 
 * havia se repetido na primeira posição da stream em "aAbBABacafe".
 *
 * 
 * IMPORTANTE: Eu considerei como premissa que os inputs são compostos APENAS por letras do 
 * alfabeto (a-Z, conforme o exemplo do exercício). Desta forma, o algoritmo possui menos estruturas de testes 
 * e será mais performático. Caso a necessidade seja ler strings mais complexas contendo números, 
 * acentuações e caracteres especiais, será necessário realizar algumas alterações no algoritmo.
 * 
 * SEGUEM ABAIXO OS ALGORITMOS COM AS DUAS POSSIBILIDADES:
 * 
 * firstChar1: verificar se a vogal se repete APENAS APÓS a consoante
 * firstChar2: verificar se a vogal se repete DURANTE TODA a stream
 * 
*/

public class Main {

	public static void main(String[] args) {
		
		List<Stream> streams = getStreams();
		
		//TESTE DO ALGORITMO 1
		System.out.println("**** ALGORITMO 1 *****");
		for(int i = 0; i < streams.size();i++){
			Stream stream = streams.get(i);
			System.out.print("Stream ["+stream.toString()+"]: ");
			char r = firstChar1(stream);
			if(r == 0){
				System.out.println("nenhum registro encontrado.");
			}else{
				System.out.println("vogal ["+r+"] foi encontrada.");
			}
		}
		
		streams = getStreams();
		
		//TESTE DO ALGORITMO 2
		System.out.println("\n**** ALGORITMO 2 *****");
		for(int i = 0; i < streams.size();i++){
			Stream stream = streams.get(i);
			System.out.print("Stream ["+stream.toString()+"]: ");
			char r = firstChar2(stream);
			if(r == 0){
				System.out.println("nenhum registro encontrado.");
			}else{
				System.out.println("vogal ["+r+"] foi encontrada.");
			}
		}
		
	}
	
	private static char firstChar1(Stream stream){
		
		// Variáveis que guardam as 3 últimas letras
		char letraPosicaoMenosDois = 0;
		char letraPosicaoMenosUm = 0;
		char letraPosicaoAtual = 0;
		
		// Variável utilizada para apresentação no retorno
		List<Character> possiveisResultadosParaRetorno = new ArrayList<Character>();
		
		/* Variável utilizada para realizar comparações, desconsiderando case sensitive, 
		 * acentuações e etc (neste caso, apenas o case sensitive de acordo com as premissas que considerei) */
		List<String> possiveisResultadosParaComparacao = new ArrayList<String>();
		
		// Loop para leitura da stream
		while(stream.hasNext()){
			
			// Carrega as variáveis com os valores necessários
			letraPosicaoMenosDois = letraPosicaoMenosUm;
			letraPosicaoMenosUm = letraPosicaoAtual;
			letraPosicaoAtual = stream.getNext();
			
			// O objetivo é guardar em uma lista as possíveis variáveis que até o momento estão elegíveis para satisfazer a regra
			if(TextoUtils.isVogal(letraPosicaoAtual)){
				
				/* Como neste algoritmo a vogal nao pode se repetir após o encontro de um consoante, verifica-se se a variável está 
				 * elegível e a remove */
				String letraComoStringPreparada = String.valueOf(letraPosicaoAtual).toLowerCase();
				int posicaoEncontradaParaExclusao = possiveisResultadosParaComparacao.indexOf(letraComoStringPreparada);
				if(posicaoEncontradaParaExclusao >= 0){
					possiveisResultadosParaRetorno.remove(posicaoEncontradaParaExclusao);
					possiveisResultadosParaComparacao.remove(posicaoEncontradaParaExclusao);
				}
				
				/* Verifica-se se a letra anterior é uma consoante e se, antes da mesma, a letra é uma vogal,
				 * satisfazendo assim parte da regra e incluindo-a na lista de elegíveis.  
				 */
				if(TextoUtils.isVogal(letraPosicaoMenosDois) && !TextoUtils.isVogal(letraPosicaoMenosUm)){
					possiveisResultadosParaRetorno.add(letraPosicaoAtual);
					possiveisResultadosParaComparacao.add(letraComoStringPreparada);
				}
			}
			
		}
		
		// Se a lista estiver vazia, então não existem vogais elegíveis para retorno
		// Caso contrário, existem vogais elegíveis e a primeira deve ser retornada.
		if(possiveisResultadosParaRetorno.isEmpty()){
			return 0;
		}else{
			return possiveisResultadosParaRetorno.get(0);
		}
		
	}
	
	private static char firstChar2(Stream stream){
		
		// Variáveis que guardam as 3 últimas letras
		char letraPosicaoMenosDois = 0;
		char letraPosicaoMenosUm = 0;
		char letraPosicaoAtual = 0;
		
		// Variável utilizada para apresentação no retorno
		List<Character> possiveisResultadosParaRetorno = new ArrayList<Character>();
		
		/* Variável utilizada para realizar comparações, desconsiderando case sensitive, 
		 * acentuações e etc (neste caso, apenas o case sensitive de acordo com as premissas que considerei) */
		List<String> possiveisResultadosParaComparacao = new ArrayList<String>();
		
		/* Variável utilizada para guardar as vogais encontradas durante a leitura da stream que não podem mais se repetir */
		List<String> vogaisEncontradasAnteriormente = new ArrayList<String>();
		
		// Loop para leitura da stream
		while(stream.hasNext()){
			
			// Carrega as variáveis com os valores necessários
			letraPosicaoMenosDois = letraPosicaoMenosUm;
			letraPosicaoMenosUm = letraPosicaoAtual;
			letraPosicaoAtual = stream.getNext();
			
			// O objetivo é guardar em uma lista as possíveis variáveis que até o momento estão elegíveis para satisfazer a regra
			if(TextoUtils.isVogal(letraPosicaoAtual)){
				
				/* Como neste algoritmo a vogal não pode se repetir após o encontro de uma consoante, verifica-se se a variável está 
				 * elegível e a remove */
				String letraComoStringPreparada = String.valueOf(letraPosicaoAtual).toLowerCase();
				int posicaoEncontradaParaExclusao = possiveisResultadosParaComparacao.indexOf(letraComoStringPreparada);
				if(posicaoEncontradaParaExclusao >= 0){
					possiveisResultadosParaRetorno.remove(posicaoEncontradaParaExclusao);
					possiveisResultadosParaComparacao.remove(posicaoEncontradaParaExclusao);
				}
				
				/* Verifica-se se a letra anterior é uma consoante e se, antes da mesma, a letra é uma vogal e se ainda não foi,
				 * encontrada anteriormente na stream, satisfazendo assim parte da regra e incluindo-a na lista de elegíveis.  
				 */
				if(TextoUtils.isVogal(letraPosicaoMenosDois) && !TextoUtils.isVogal(letraPosicaoMenosUm) && !vogaisEncontradasAnteriormente.contains(letraComoStringPreparada)){
					possiveisResultadosParaRetorno.add(letraPosicaoAtual);
					possiveisResultadosParaComparacao.add(letraComoStringPreparada);
				}
				// Adiciona a vogal na lista de variáveis já encontradas.
				vogaisEncontradasAnteriormente.add(letraComoStringPreparada);
				
			}
			
		}
		
		// Se a lista estiver vazia, então não existem vogais elegíveis para retorno
		// Caso contrário, existem vogais elegíveis e a primeira deve ser retornada.
		if(possiveisResultadosParaRetorno.isEmpty()){
			return 0;
		}else{
			return possiveisResultadosParaRetorno.get(0);
		}
		
	}
	
	private static List<Stream> getStreams(){
		List<Stream> streams = new ArrayList<Stream>();
		streams.add(new StreamImpl("aAbBABacafe"));
		streams.add(new StreamImpl("aAbBABacafea"));
		streams.add(new StreamImpl("aAbuBABacafea"));
		streams.add(new StreamImpl("aAbuBABacafeaoeffou"));
		streams.add(new StreamImpl("aAbuBAoBacafeaoo"));
		streams.add(new StreamImpl("aAbuiBAoBacafeaoo"));
		streams.add(new StreamImpl(""));
		streams.add(new StreamImpl("as"));
		streams.add(new StreamImpl("uuu"));
		streams.add(new StreamImpl("agi"));
		streams.add(new StreamImpl("iiiiiiiiiiiiiii"));
		return streams;
	}
	
}
