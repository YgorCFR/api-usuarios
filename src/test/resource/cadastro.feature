#Author: ygorcarlos@id.uff.br
#language: pt
Funcionalidade: Gerenciar Usuario
		Usuário vai entrar com senha e email 
		Com o objetivo de entrar na aplicação
		Se estiver cadastrado
		Receber um resultado não nulo do usuário
	

Cenario: Entrar
	Dado que eu queira logar
	Quando eu preencho o primeiro campo com o valor "admin@email.com"
	E eu preencho o segundo campo com o valor "admin"
	Entao eu devo ver o resultado não nulo Usuario
