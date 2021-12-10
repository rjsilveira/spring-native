CREATE TABLE user
(
    id   BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'PK. Identificador unico definido com auto_increment.',
    name VARCHAR(255) NOT NULL COMMENT 'Nome do usuario.',
    age  INT          NOT NULL COMMENT 'Idade do usuario.',
    PRIMARY KEY user_pk (id)
) ENGINE = InnoDB COMMENT 'Tabela para armazenar os usuarios.';
