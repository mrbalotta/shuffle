#Exerc�cio Mobile - Shuffle Songs

Voc� decidiu lan�ar um novo app de m�sicas que ser� um sucesso! O app consiste em mostrar m�sicas populares de alguns dos artistas mais tocados no momento.

Para isso, o primeiro passo � listar m�sicas de uma API aberta com o seguinte endpoint:

https://us-central1-tw-exercicio-mobile.cloudfunctions.net/lookup?id=<id>,<id>,<id>&limit=5 (aonde <id> � o ID do artista)

E, claro, voc� escolheu alguns artistas para sua lista:

##Artista / ID:

John Dollar / 909253

Charlie and the Chewie Humans / 1171421960

Bloco T�tiOQue / 358714030

MC Arianne / 1419227

Decimais MC�s / 264111789

Al�m disso, todo app de sucesso possui um incr�vel algoritmo de shuffle, que consiste em embaralhar a lista de m�sicas respeitando algumas regras:

O algoritmo deve ser rand�mico e gerar listas diferentes a cada execu��o;
M�sicas do mesmo artista n�o devem aparecer juntas;
 

#Exemplo:

##Lista original:

John Dollar - Broken Vibrations
John Dollar - Journey of Eternity
MC Arianne - Boom Boom
MC Arianne - Sorriso Fant�stico
Decimais MC�s - Rua da Perfei��o

##Lista embaralhada:

MC Arianne - Boom Boom
John Dollar - Broken Vibrations
Decimais MC�s - Rua da Perfei��o
John Dollar - Journey of Eternity
MC Arianne - Sorriso Fant�stico


Por fim, seu aplicativo ter� uma identidade visual �nica! Voc� pode encontrar as telas e assets para iOS e Android aqui.
Use bibliotecas de terceiro apenas quando estritamente necess�rio. Por favor, n�o utilize bibliotecas como: Alamofire, Moya, Kingfisher, RxSwift, etc.
Os seguintes pontos ser�o avaliados na sua aplica��o:

Arquitetura
Qualidade do c�digo
Interface
Testes (lembre-se de escrever ao menos alguns testes unit�rios)
Sua aten��o a detalhes
 

Para a sua solu��o, voc� deve usar: Swift, Java, Kotlin ou React Native


At� breve!

Time de Recrutamento

ThoughtWorks Brasil