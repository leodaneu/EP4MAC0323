# README_simplificado

.xml to .vll
------------

* Exportando um mapa, teremos o formato **.xml**. ([wiki](http://wiki.openstreetmap.org/wiki/OSM_XML) explica o que seria um xml em OSM)

* Extraindo os dados necessarios desse .xml, poderiamos obter algo como o **USP.vll**, que seguiria esse formato: ``<node id> <latitude> <longitude>``.

* Usando uma versao modificada de **PlotFilter.java**, poderiamos ter a figura:

![alt text](https://www.ime.usp.br/~yoshi/2015i/mac323/sandbox/Data/EP4/USP/USPNodes.png)

Distance calculator
-------------------

Usando esse link (http://www.movable-type.co.uk/scripts/latlong.html), obteriamos o seguinte:

**Observação. Na verdade, supor que a terra é plana já seria suficiente neste EP. Você pode fazer isso.**

using this data:

| point			| mlat 		| mlon		|
|:---------------------:|:-------------:|:-------------:|
| point1		| -23.55727	| -46.73398	|
| point2		| -23.5633 	| -46.7216	|

result:

| info			| number			|
|:---------------------:|:-----------------------------:|
| Distance		| 1.429 km (to 4 SF*) 		|
| Initial bearing 	| 117°59′14″			|
| Final bearing 	| 117°58′57″			|
| Midpoint		| 23°33′37″S, 046°43′40″W	|

Grafos dirigidos a partir de arquivos XML de OSM
------------------------------------------------

O arquivo **xmltoadj.py** usa o **gistfile1.py**. Ele basicamente faz a conversao de um .xml para .adjlist

``$ python xmltoadj.py map.osm-USP.xml USP.adjlist``

O arquivo USP.adjlist tem um formato natural: por exemplo, uma linha da forma a b c significa que o vértice a manda arcos para b e c. 

Note que os nomes dos vértices que aparecem em **USP.adjlist** são os id dos nodes no arquivo XML (entretanto, nem todo node no arquivo XML ocorre no grafo).

O arquivo USP.adjlist pode ser lido usando-se **SymbolDigraph.java** (na verdade, o cabeçalho no arquivo USP.adjlist precisa ser removido).

![alt text](https://www.ime.usp.br/~yoshi/2015i/mac323/sandbox/Data/EP4/USP/USPGraph.png)

Esse grafo tem 1271 vértices e 1926 arcos.

Fazendo um zoom em uma região menor, podemos ver o grafo melhor:

![alt text](https://www.ime.usp.br/~yoshi/2015i/mac323/sandbox/Data/EP4/USP/BlocoC.png)

* **pontos pretos:** vértices do grafo (nodes from OSM map);

* **segmentos de reta:** arcos do grafo;

* **pontos vermelhos:** mão de ruas/orientação dos arcos (arco de **v** para **w**, há um ponto vermelho em **w** and e este arco representa uma via com mão única, de v para w. Dois pontos vermelhos no segmento entre v e w indicam que a via é de mão dupla.).

Shortest path: Dijkstra
-----------------------

Going from node 1931475238 to 1831379092:

![alt text](https://www.ime.usp.br/~yoshi/2015i/mac323/sandbox/Data/EP4/USP/MAC-IB.png)

We need to execute Dijkstra in the right graph to choose the shortest path. (path: aprox. 2.7 km}

Second example
--------------

Segment of Sao Paulo: http://www.openstreetmap.org/export?&bbox=-46.7357,-23.606,-46.5613,-23.5036

File **map.osm-SP.xml** has a graph of 62327 vértices and 98222 arcos.

![alt text](https://www.ime.usp.br/~yoshi/2015i/mac323/sandbox/Data/EP4/SP/MAC-Se.png)

The path has aprox. 12km. Sorting graph has 62327 vértices e 98222 arcos.


Requirements
============

Before program execution
------------------------

* Usuario escolhe o mapa e exporta ele, como map-osm.xml;

* Usuario executa **xmltoadj.py**. Exemplo: ``$ python xmltoadj.py map.osm-USP.xml USP.adjlist``;

* Usuario executa o **EP4.java** program, com inputs: map-osm.xml e G.adjlist.

Interactive mode
----------------

* User should be able to define map region to be pictured, giving 2 points: o ponto inferior esquerdo e o ponto superior direito. Esses pontos devem ser pares latitude/longitude;

* "Desenhar o mapa" significa desenhar o grafo dirigido da região (o mais fácil é simplesmente ignorar os pontos que caem fora do "canvas");

* **At the any moment**: user should be able to update picture. Usuario deve ter opção se deseja que arestas sejam desenhadas ou não. Vértices sempre serão desenhadas;

* So user can zoom in or out;

* User can ask shortest path between 2 points (start and destiny), giving datas like latitude/longitude;

* Seu programa deve encontrar os vértices do grafo mais próximos dos pontos dados, e deve encontrar um caminho mínimo entre eles (ou dizer que o destino não é acessível dessa origem). Uma vez encontrado um caminho mínimo, ele deve ser indicado com alguma cor diferente na figura atual (os vértices e as arestas devem ser dessa cor). Seu programa deve também dizer o comprimento do caminho encontrado;

* O usuário deverá ser capaz de dar os pontos de origem e destino com o **mouse**. Para tanto, o usuário deverá ter um modo de alternar entre interação via mouse e via teclado;

* O usuário deverá ser capaz de "limpar" a figura, removendo-se o caminho mínimo atual (o mais fácil é redesenhar a figura);

* No caso de mapas grandes, você pode optar por mostrar apenas os vértices do grafo, para a imagem não ficar muito poluída (essa opção poderia ser dada ao usuário);

Implementation
--------------

( É natural decompor seu sistema em várias classes. As seguintes classes são naturais. Se você encontrar outra decomposição melhor, você pode usá-la. Se você não usar uma boa decomposição, sua nota pode sofrer reduções)

* **EdgeWeightedDigraph.java**. A classe de S&W para grafos dirigidos com pesos nos arcos;

* **SymbolEWDigraph.java**. Deve ter a mesma relação com a classe EdgeWeightedDigraph.java como as classes SymbolDigraph.java e Digraph.java tem entre si;

* **Location.java**. Objetos dessa classe especificam um ponto na superfície da terra. Você poderia implementar como pares latitude/longitude;

* **GeoInto.java**. Um objeto dessa classe deve ter, como componente principal, uma tabela de símbolos com elementos da forma <node id, location>, onde node id é o identificador de um nó de um mapa OSM e location é a localização desse nó;

* **SymbolGeoEWDigraph.java**. Um objeto dessa classe deve ter, como elementos principais, um SymbolEWDigraph e um GeoInfo, este último contendo a localização geográfica dos vértices no SymbolEWDigraph;

Updates
-------

[2015-06-23 Tue 06:50] Requisito sobre interação via mouse adicionado.

[2015-06-21 Sun 19:06] Requisitos do EP definidas. Enunciado próximo de completo.

[2015-06-21 Sun 18:00] Exemplo com mapa de parte da cidade de São Paulo adicionada.

[2015-06-20 Sat 16:07] Exemplo de caminho mínimo adicionado. Especificação da saída do EP adicionada.

[2015-06-20 Sat 14:50] gistfile1.py refinado, para levar em conta oneway tags que são -1. Grafos do enunciado gerados novamente de acordo.

[2015-06-20 Sat 13:31] Os grafos nos enunciado foram gerados novamente, com a nova versão de gistfile1.py.

[2015-06-20 Sat 13:30] O script gistfile1.py foi refinado, para levar em conta a mão das rotatórias.

[2015-06-20 Sat 13:30] O script gistfile1.py foi refinado, para não incluir passagens de pedestres, ciclovias, etc.
