# Desafio API Marvel

##Intruções:

- Clonar o repositório através do comando git clone https://github.com/luizrenato2012/desafio-marvel-api.git .

- O banco de dados do projeto é o H2 em arquivo, o que dispensa a instalação de gerenciador de banco.
- A configuração do projeto no src/main/resources/application.properties tem a linha spring.jpa.hibernate.ddl-auto=create que gera automaticamente as tabelas do banco, caso se queira manter as tabelas deve-se comentá-la (atentando-se em desativar a importação dos script's SQL conforme próxima isntrução.
- Os script's com os dados de teste então na pasta src/main/resources os quais são: import_main.sql e import_relations.sql; esses arquivos são importados para o banco todas as vezes que a aplicação for executada, caso se queira deixar de importá-los, comente a linha spring.jpa.properties.hibernate.hbm2ddl.import_files=import_main.sql,import_relations.sql do arquivo application.properties.

