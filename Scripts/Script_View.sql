/*
  - Script para criar as View do Sistema
*/
use Controle_Financeiro
go

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'V' AND Name = 'Movimentacao_Parcela')
BEGIN
  DROP VIEW dbo.Movimentacao_Parcela;
END
GO

IF NOT EXISTS (SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Parcelas')
BEGIN
  RAISERROR 30002 'CREATE VIEW dbo.Movimentacao_Parcela..: Não existe a tabela "Parcelas"'
  RETURN;
END
GO

IF NOT EXISTS (SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Movimentacao')
BEGIN
  RAISERROR 30002 'CREATE VIEW dbo.Movimentacao_Parcela..: Não existe a tabela "Movimentacao"'
  RETURN;
END
GO

IF NOT EXISTS (SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Forma_Movimentacao')
BEGIN
  RAISERROR 30002 'CREATE VIEW dbo.Movimentacao_Parcela..: Não existe a tabela "Forma_Movimentacao"'
  RETURN;
END
GO

CREATE VIEW dbo.Movimentacao_Parcela
AS
SELECT Movimentacao.ID_Movimentacao
      ,Movimentacao.ID_Usuario
      ,Movimentacao.Data_Movimentacao
      ,Movimentacao.Valor_Total
      ,Movimentacao.Descricao
      ,Parcelas.Numero_Parcela
      ,CASE Parcela_Entrada
         WHEN 'S' THEN 'Sim'
         WHEN 'N' THEN 'Não'
         ELSE 'Desconhecido'
       END AS Parcela_Entrada
      ,Parcelas.Data_Vencimento AS Data_Vencimento_Parcela
      ,Parcelas.Data_Pagamento AS Data_Pagamento_Parcela
      ,CASE Parcelas.Atualizado
         WHEN 'S' THEN 'Sim'
         WHEN 'N' THEN 'Não'
         ELSE 'Valor Inválido'
       END AS Parcela_Paga
      ,Forma_Movimentacao.Descricao AS Descricao_Forma_Movimentacao
      ,CASE Forma_Movimentacao.Tipo_Pagamento
         WHEN 'D' THEN 'Débito'
         WHEN 'C' THEN 'Crédito'
         ELSE 'Valor Inválido'
       END AS Forma_Movimentacao
FROM dbo.Movimentacao
     INNER JOIN dbo.Parcelas
       ON Movimentacao.ID_Movimentacao = Parcelas.ID_Movimentacao 
     INNER JOIN dbo.Forma_Movimentacao
       ON Movimentacao.ID_Forma_Movimentacao = Forma_Movimentacao.ID_Forma_Movimentacao
go

select * from Movimentacao_Parcela


IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'V' AND Name = 'Movimentacao_Home')
BEGIN
  DROP VIEW dbo.Movimentacao_Home;
END
GO

CREATE VIEW dbo.Movimentacao_Home
AS
SELECT Movimentacao.ID_Movimentacao
      ,Movimentacao.Descricao AS Descricao_Movimento
      ,ROUND(Movimentacao.Valor_Total,2) AS Valor_Total
      ,Forma_Movimentacao.Descricao AS Descricao_Forma
      ,Movimentacao.Data_Movimentacao
      ,Movimentacao.ID_Forma_Movimentacao
      ,Parc.Numero_Parcela
      ,Usuarios.ID_Usuario
      ,ROUND(ParcEntr.Valor_Parcela,2) AS Valor_Parc_Entrada
FROM dbo.Movimentacao
     INNER JOIN dbo.Usuarios 
       ON Usuarios.ID_Usuario = Movimentacao.ID_Usuario
     INNER JOIN dbo.Forma_Movimentacao
       ON Forma_Movimentacao.ID_Forma_Movimentacao = Movimentacao.ID_Forma_Movimentacao
     INNER JOIN ( SELECT Parcelas.ID_Movimentacao
                        ,COUNT(Parcelas.Numero_Parcela) AS Numero_Parcela
                  FROM dbo.Parcelas
                  GROUP BY Parcelas.ID_Movimentacao ) AS Parc
       ON Parc.ID_Movimentacao = Movimentacao.ID_Movimentacao
    INNER JOIN ( SELECT Parcelas.ID_Movimentacao
                       ,Parcelas.Valor_Parcela
                 FROM dbo.Parcelas
                 WHERE Parcelas.Numero_Parcela = 1
                 GROUP BY Parcelas.ID_Movimentacao
                         ,Parcelas.Valor_Parcela ) AS ParcEntr
      ON ParcEntr.ID_Movimentacao = Movimentacao.ID_Movimentacao
GO


select * from Parcelas