# Desafio API Marvel

##Intruções:
*Clonar o repositório através do comando 'git clone https://github.com/luizrenato2012/desafio-marvel-api.git' .
-* No diretório criado executar os comandos:
  'mvnw install'
  'java -jar target/mavel-api-desafio-0.0.1-SNAPSHOT.jar'
* Com a aplicação executando, abra o navegador no endereço 'http://localhost:8080/v1/public/characters' que mostrar os personagens cadastrados e a partir do json gerado, obtenha os links para 'comics', 'series', 'stories' e 'events'.

##Endpoints
Os seguintes endpoint's estão disponíveis:
*/v1/public/characters
*/v1/public/characters/{characterId}
*/v1/public/characters/{characterId}/comics
*/v1/public/characters/{characterId}/events
*/v1/public/characters/{characterId}/series
*/v1/public/characters/{characterId}/stories

##Banco de Dados:
- O banco de dados do projeto é o H2 em arquivo, o que dispensa a instalação de gerenciador de banco.
-  No arquivo de configuração do projeto src/main/resources/application.properties tem a linha spring.jpa.hibernate.ddl-auto=create que gera automaticamente as tabelas do banco, caso se queira manter as tabelas deve-se comentá-la (atentando-se em desativar a importação dos script's SQL conforme próxima isntrução.
- Os script's com os dados de teste então na pasta src/main/resources os quais são: import_main.sql e import_relations.sql; esses arquivos são importados para o banco todas as vezes que a aplicação for executada, caso se queira deixar de importá-los, comente a linha spring.jpa.properties.hibernate.hbm2ddl.import_files=import_main.sql,import_relations.sql do arquivo application.properties.

