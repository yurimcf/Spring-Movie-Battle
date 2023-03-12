As classes com o final em Controller representam o View do MVC.  
As classes com o final Service representam o Controller do MVC.  
As classes com o final Repository ou sem final representam o Modelo do MVC.


## Descrição
Crie uma aplicativo (REST e Front) para uma aplicação ao estilo card game, onde serão informados dois filmes e o jogador deve acertar aquele que possui melhor avaliação no IMDB.

## Requisitos
Cada rodada do jogo consiste eminformar um par de filmes,
O jogador deve tentar acertar qual filme possui maior pontuação, composta pela nota (0.0-10.0).
Se escolher o vencedor correto, conta 1 ponto. Após responder, terá acesso a novo par de filmes quando acessar o endpoint do quiz.
Na listagem de jogadores deve ser exibido considerando o ranking em ordem decrescente.
Explore os frameworks Spring:Web, Boot, Data.