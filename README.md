# so-atv3-ex4

## Sobre o projeto

Exercício 4 da 3ª lista de exercícios da disciplina Sistemas Operacionais I ministrada pelo profº Leandro Colevati

Consiste numa pequena aplicação que usa Threads e Semaphores. A aplicação realiza as seguintes ações:
* cria uma subclasse de Thread chamada ThreadCarro;
* cada carro de Formula 1 tem as informações de numero e escuderia;
* cada carro realiza 3 voltas na pista em tempos aleatórios e cruza a linha de chegada
* é salvo o melhor tempo de cada carro;
* os Semaphores aplicam as seguintes restrições:
    * apenas 5 carros podem estar presentes na pista;
    * somente 1 carro de cada escuderia pode estar competindo;
* por fim, é apresentado em ordem de menor para maior os tempos no grid de largada.
