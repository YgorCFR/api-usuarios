#Author: ygorcarlos@id.uff.br
#language: pt
Funcionalidade: Gerenciar Usuario
  Gerenciar ações das funcionalidades
  que fazem parte da api de gerenciamento de usuarios

  Cenario: Entrar
    Dado que eu queira logar
    Quando eu preencho o primeiro campo com o valor "admin@email.com"
    E eu preencho o segundo campo com o valor "admin"
    Entao eu devo ver o resultado não nulo Usuario

  Cenario: Verificar_existencia
    Dado que queira adicionar um usuario
    Quando eu clicar no botao adicionar usuario
    E entre com o email "nightmonkey@email.com"
    E verifique a existencia desse email
    Entao eu devo ver o resultado  nulo
