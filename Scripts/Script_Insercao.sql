use master;
go
IF NOT EXISTS(SELECT 1 FROM master.dbo.SysDatabases WHERE Name = 'Controle_Financeiro')
BEGIN
  CREATE DATABASE Controle_Financeiro;
END
GO

/*
  - Script de criação das tabelas
  - Script de criação dos dados (Tabelas Forma_Movimentacao e Categorias)
*/

/* - Script de criação das tabelas */
use Controle_Financeiro;
go
IF NOT EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Usuarios')
BEGIN
  CREATE TABLE dbo.Usuarios ( ID_Usuario       Integer Identity(1,1) NOT NULL,
                              Nome             VarChar(100),
                              Sexo             Char(1),
                              Salario          Money,
                              Data_Nascimento  DateTime, 
                              Usuario_Login    VarChar(20),
                              Senha            VarChar(50),
                              Ativo            Char(1)
  CONSTRAINT pkUsuarios_IDUsuario PRIMARY KEY (ID_Usuario));

END
GO

IF NOT EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Forma_Movimentacao')
BEGIN
  CREATE TABLE dbo.Forma_Movimentacao ( ID_Forma_Movimentacao   Char(2) NOT NULL,
                                        Descricao               VarChar(200),
                                        Tipo_Pagamento          Char(1),
                                        Aceita_Parcela          Char(1),

  CONSTRAINT pkFormaMovimentacao_IDFormaMovimentacao PRIMARY KEY (ID_Forma_Movimentacao));
END
GO

IF NOT EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Movimentacao')
BEGIN
  CREATE TABLE dbo.Movimentacao ( ID_Movimentacao         Integer Identity(1,1) NOT NULL,
                                  ID_Usuario              Integer, 
                                  Data_Movimentacao       DateTime,
                                  ID_Forma_Movimentacao   Char(2),
                                  Valor_Total             Money,
                                  Descricao               VarChar(1000),
  CONSTRAINT pkMovimentacao_IDMovimentacao PRIMARY KEY (ID_Movimentacao),
  
  CONSTRAINT fkMovimentacao_IDFormaMovimentacao 
     FOREIGN KEY (ID_Forma_Movimentacao) 
     REFERENCES Forma_Movimentacao(ID_Forma_Movimentacao)
     ON UPDATE NO ACTION ON DELETE NO ACTION,
    
  CONSTRAINT fkMovimentacao_IDUsuario 
    FOREIGN KEY (ID_Usuario) 
    REFERENCES Usuarios(ID_Usuario)
    ON UPDATE NO ACTION ON DELETE NO ACTION 
   
);                                  
END
GO

	

IF NOT EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Parcelas')
BEGIN
  CREATE TABLE dbo.Parcelas ( ID_Movimentacao  Integer,  --// Ligado a tabela "Movimentação" 
                              Numero_Parcela   SmallInt, 
                              Valor_Parcela    Money,
                              Parcela_Entrada  Char(1),
                              Data_Vencimento  DateTime,
                              Data_Pagamento   DateTime,
                              Atualizado       Char(1)                  
            
  CONSTRAINT pkParcelas_IDMovimentacao_NumeroParcela PRIMARY KEY (ID_Movimentacao, Numero_Parcela),

  CONSTRAINT fkParcelas_IDMovimentacao FOREIGN KEY (ID_Movimentacao) 
    REFERENCES Movimentacao(ID_Movimentacao)
    ON UPDATE NO ACTION ON DELETE NO ACTION

);
END
GO






/*
  - Inserção em "Forma_Movimentacao"
*/

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Forma_Movimentacao')
BEGIN
  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''CH'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''CH'''+', ''Cheque (Crédito)'', '+'''C'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''DI'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''DI'''+', ''Dinheiro (Crédito)'', '+'''C'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''CC'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''CC'''+', ''Cartão de Crédito (Crédito)'', '+'''C'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''CD'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''CD'''+', ''Cartão de Débito (Crédito)'', '+'''C'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''CA'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''CA'''+', ''Cheque (Débito)'', '+'''D'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''DA'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''DA'''+', ''Dinheiro (Débito)'', '+'''D'''+'); ' +
          'END');    


  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''CP'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''CP'''+', ''Cartão de Crédito (Débito)'', '+'''D'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''CO'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''CO'''+', ''Cartão de Débito (Débito)'', '+'''D'''+'); ' +
          'END');    


  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''BO'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''BO'''+', ''Boleto'', '+'''D'''+'); ' +
          'END');    

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = '+'''PO'''+') ' +
          'BEGIN ' +
          'INSERT INTO dbo.Forma_Movimentacao (ID_Forma_Movimentacao, Descricao, Tipo_Pagamento) ' +
          'VALUES ('+'''PO'''+', ''Pagamento Online'', '+'''D'''+'); ' +
          'END');    
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Usuarios')
BEGIN
  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Usuarios WHERE Nome = '+'''Renato Oda'''+') ' +
           'BEGIN ' +
           'INSERT INTO dbo.Usuarios (Nome, Sexo, Salario, Data_Nascimento, Senha, Usuario_Login) ' +
           'VALUES ('+'''Renato Oda'''+', ''M'', 100, '+'''19910715'''+' , '+'''J/6iz3NNwnU='''+', '+'''renato_oda'''+'); ' +
           'END');           
 
  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Usuarios WHERE Nome = '+'''Guilherme De Almeida'''+') ' +
           'BEGIN ' +
           'INSERT INTO dbo.Usuarios (Nome, Sexo, Salario, Data_Nascimento, Senha, Usuario_Login) ' +
           'VALUES ('+'''Guilherme De Almeida'''+', ''M'', 100, '+'''19900101'''+' , '+'''J/6iz3NNwnU='''+', '+'''guilherme_almeida'''+'); ' +
           'END');

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Usuarios WHERE Nome = '+'''Luan Labegalini'''+') ' +
           'BEGIN ' +
           'INSERT INTO dbo.Usuarios (Nome, Sexo, Salario, Data_Nascimento, Senha, Usuario_Login) ' +
           'VALUES ('+'''Luan Labegalini'''+', ''M'', 100, '+'''19900101'''+' , '+'''J/6iz3NNwnU='''+', '+'''luan_labegalini'''+'); ' +
           'END');

  EXECUTE('IF NOT EXISTS(SELECT 1 FROM Usuarios WHERE Nome = '+'''Bruno Gonçalves'''+') ' +
           'BEGIN ' +
           'INSERT INTO dbo.Usuarios (Nome, Sexo, Salario, Data_Nascimento, Senha, Usuario_Login) ' +
           'VALUES ('+'''Bruno Gonçalves'''+', ''M'', 100, '+'''19900101'''+' , '+'''J/6iz3NNwnU='''+', '+'''bruno_goncalves'''+'); ' +
           'END');
END
GO