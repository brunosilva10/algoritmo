Ao ler o enunciado e o exemplo, tive uma dúvida, pois analisando a frase “No exemplo, 'e' é o primeiro 
caracter Vogal da stream que não se repete após a primeira Consoante 'f' o qual tem uma vogal 'a' como 
antecessora” para o input aAbBABacafe, percebi que a Vogal "a" adequa-se corretamente na regra, veja:

aAbBABacafe

No exemplo, "a" é o primeiro caracter Vogal da stream que não se repete após a primeira 
Consoante "c" o qual tem uma vogal "a" como antecessora 

Tendo isso, vejo uma possibilidade para que o exemplo do desafio esteja correto, sendo:

A vogal não deve se repetir durante toda a stream, e não apenas não se repetir após o 
encontro da consoante. Neste caso, "e" é a resposta correta, pois no trecho "aca", o "a" já 
havia se repetido na primeira posição da stream em "aAbBABacafe".


IMPORTANTE: Eu considerei como premissa que os inputs são compostos APENAS por letras do 
alfabeto (a-Z, conforme o exemplo do exercício). Desta forma, o algoritmo possui menos estruturas de testes 
e será mais performático. Caso a necessidade seja ler strings mais complexas contendo números, 
acentuações e caracteres especiais, será necessário realizar algumas alterações no algoritmo.

SEGUEM ABAIXO OS ALGORITMOS COM AS DUAS POSSIBILIDADES:

firstChar1: verificar se a vogal se repete APENAS APÓS a consoante
firstChar2: verificar se a vogal se repete DURANTE TODA a stream

