insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into cozinha (id,nome) values (1,'Tailandesa');
insert into cozinha (id,nome) values (2,'Japonesa');
insert into cozinha (id,nome) values (3,'Chinesa');

insert into restaurante (id,nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento,endereco_bairro,endereco_cidade_id) values (1,'Thai Gourmet', 10, 1, '09090-000', 'rua 1', '1', 'casa a', 'jardim', 1 );
insert into restaurante (id,nome,taxa_frete,cozinha_id) values (2,'Gendai',8,2);
insert into restaurante (id,nome,taxa_frete,cozinha_id) values (3,'JapanFood',0,2);


insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_cozinhaS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_cozinhaS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1,2), (1, 3), (2,1), (3,2), (3,3);
    