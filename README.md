# MinecraftDungeonSystems

<<<<<<< HEAD
Um plugin de Minecraft com algumas funções úteis na criação de sisteminhas especiais!

Uma descrição melhor será adicionada em breve!
=======

Um plugin de Minecraft com algumas funções úteis na criação de sisteminhas especiais úteis em dungeons!  


Como funciona:  
    A função principal desse plugin é utilizar eventos de ativação de redstone do minecraft para desencadear outros acontecimentos. É possível colocar blocos, executar comandos e ativar outros eventos de redstone depois de determinado período de tempo. Também é possível configurar algumas funcionalidades como cancelamento do evento de clique, registro assíncrono de blocos (para salvar mais eficientemetne grandes quantidades de blocos), e se deve ou não haver atualização dos blocos ao redor da área em que os blocos são alterados. Tudo isso será explicado melhor na área que descreve os comandos.  

    De forma resumida, esse plugin observa os blocos que foram registrados, e se houver ativação de sinal de redstone no bloco ele executa uma série de instruções.  


O que o plugin é capaz de observar:  
    Mudanças que ativem o evento BlockRedstoneEvent, como ativação de uma alavanca, botão, placa de pressão ou sensor de luz, além de ativação de fios de redstone, repetidores, comparadores, dentre muitos outros.  


Que tipo de instruções o plugin é capaz de executar:  
    Atualmente, existem três tipos de instruções que o plugin é capaz de executar, sendo elas:  
-Colocar um bloco em uma coordenada específica (Só é salvo o BlockState, portanto dados como quais itens estão dentro de um baú não serão salvos).  
-Ativar um BlockRedstoneEvent em outro bloco depois de determinado período de tempo, o que permite que seja feita a animação de uma porta abrindo, por exemplo.  
-Executar um comando do console do servidor.  
    Devido à possibilidade de executar mihares de instruções a partir de um mesmo BlockRedstoneEvent, é possível, por exemplo, alterar uma área inteira de blocos, enviar uma mensagem para um player específico e ativar um outro envento de redstone depois de determinado período de tempo.  


O que pode ser configurado no plugin:
-Cancelamento do evento de clique do player. Por exemplo, caso o player ative uma alavanca que esteja sendo observada pelo plugin, é possível cancelar esse evento de clique, praticamente como se o player não tivesse clicado. Isso permite alterar o próprio bloco da alavanca a partir do plugin. Essa configuração só é aplicável para blocos de redstone em que o player deve clicar para ativar um evento de redstone (sensor de luz do dia está incluído).  
-Registro assíncrono de blocos: faz com que áreas de blocos sejam salvas como instruções de forma assíncrona, impedindo que o servidor pare completamente enquanto grandes quantidades de blocos estão sendo salvas e, ao mesmo tempo, acelerando o processo de registro dos blocos.  
-Atualização de blocos: torna possível evitar atualizações de blocos ao redor dos blocos alterados pelo plugin, o que pode evitar alguns bugs de drop de itens de blocos como alavancas que são removidas pelo plugin depois do bloco em que elas estão apoiadas, além de evitar atualizações dos blocos ao redor, como por exemplo para impedir que blocos de água atualizem e que haja uma corrente de água indesejada no local.  


Comandos:
/state => Informa o BlockState do bloco que o player está (pode ser utilizado para saber se um dado importante de um bloco poderá ser registrado pelo plugin)  
/addcord => Adiciona uma nova coordenada para ser observado pelo plugin. É necessário utilizar esse comando antes que o plugin possa detectar eventos de redstone na coordenada.  
/remcord => Remove uma coordenada que já estava registrada para ser observada pelo plugin, removendo também todos os arquivos que corresponddem às instruções da coordenada.  
/addcomb => Adiciona uma instrução do tipo "bloco" a uma coordenada observada, fazendo com que sempre que o evento de ativação de redstone for observado nessa coordenada, um bloco específico seja colocado pelo plugin.  
/addtruc => Adiciona uma instrução do tipo "comando" a uma coordenada observada, fazendo com que sempre que o evento de ativação de redstone for observado nessa coordenada, um comando específico seja executado pelo servidor.  
/addcombs => Adiciona uma instrução do tipo "bloco" a uma coordenada observada pra cada bloco numa determinada área, fazendo com que sempre que o evento de ativação de redstone for observado nessa coordenada, todos os blocos dessa área sejam colocados pelo plugin.  
/addtemp => Adiciona uma instrução do tipo "tempo" a uma coordenada observada, fazendo com que sempre que o evento de ativação de redstone for observado nessa coordenada, haja um tempo de espera determinado pelo usuário e depois um outro evento de redstone seja ativado pelo plugin em outra coordenada observada.  
/fastarea => Ativa e desativa a configuração de registro assíncrono de blocos.  
/setcanc => Ativa e desativa a configuração de cancelamento do evento de clique do player para o bloco observado.  
/upblocks => Ativa e desativa a configuração de atualização de blocos para o bloco observado.  
>>>>>>> BolaEditions
