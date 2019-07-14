# api-usuarios
Api com dados em memória que simula gerenciamento de usuários


link para documentação:
https://documenter.getpostman.com/view/4765208/SVSBxD1o


### Observações:
- Projeto deve ser executado de preferência no eclipse.
- É uma Rest API com dados fictícios
- Possui os seguintes scripts de teste:

  ## Testes unitários e de cobertura  
    Sendo necessário Instalar a ferramenta Eclemma no Eclipse. A realização dele se dá 
    pelo arquivo ```CadastroCoberturaUnitario.java```, localizado no pacote ```br.uff.qet.test``` .
  
  ## Testes estruturais 
  Possuem explicações em seus comentários no script. Localizado em ```/ApiCadastros/src/test/java/br/uff/qet/test/CadastrosEstruturalTeste.java``` .
  
  ## Testes funcionais 
  Realizados em ```/ApiCadastros/src/test/java/br/uff/qet/test/CadastroFuncionalTestCucumber.java``` com a ferramenta Cucumber, sendo dividido em stepDefinitions que se localiza logo abaixo do pacote ```br.uff.qet.test``` e o arquivo .feature localizado em ```/ApiCadastros/src/test/resource/cadastro.feature```.
  
  ## Testes de mutantes 
  Localizado em /ApiCadastros/src/test/java/br/uff/qet/test/CadastrosMutantes.java sendo necessário, quando for executado, clicar com botão direito em cima do projeto, maven, build path... , setando o goal como: ```org.pitest:pitest-maven:scmMutationCoverage -Dinclude=ADDED,UNKNOWN -DmutationThreshold=85``` ou
  ```org.pitest:pitest-maven:mutationCoverage``` ou
   ```-DwithHistory org.pitest:pitest-maven:mutationCoverage```
   
  ## Testes automatizados 
  Com selenium hq localizados em ```/ApiCadastros/src/test/java/br/uff/qet/test/CadastroSeleniumTest.java```, para executá-lo basta clicar com botão direito no projeto, run, run onserver e escolher o servidor de sua preferência. Estando pronto e executado, você deve ir até o arquivo de teste de selenium, botão de direito, run, JUnit test, para isso você já deve ter em sua máquina o front-end do projeto, que está localizado em um repositório separado, com sufixo client e nome semelhante a esse repositório. Modifique o nome do caminho do seu front-end no script de teste automatizado em selenium e o execute novamente, reiniciando o servidor que esteja utilizando e executando o teste após iniciar a API no servidor.
    
