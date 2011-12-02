USE Controle_Financeiro
go

/*Desvinculando os Defauts*/

	sp_unbindefault 'usuarios.ativo'
GO
	sp_unbindefault 'Forma_Movimentacao.Tipo_Pagamento'
GO
	sp_unbindefault 'Forma_Movimentacao.Aceita_Parcela'
GO
	sp_unbindefault 'Parcelas.Parcela_Entrada'
GO
	sp_unbindefault 'Parcelas.Atualizado'
GO


/* Desvinculando as Rules*/

	sp_unbindrule 'usuarios.ativo'
GO
	sp_unbindrule 'Forma_Movimentacao.Aceita_Parcela'
GO
	sp_unbindrule 'Parcelas.Parcela_Entrada'
GO
	sp_unbindrule 'Parcelas.Atualizado'
GO
	sp_unbindrule 'usuarios.sexo'
GO
	sp_unbindrule 'Forma_Movimentacao.Tipo_pagamento'
GO


/*Exclusao de rules*/

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'R' AND Name = 'Sim_Nao')
BEGIN
  DROP RULE dbo.Sim_Nao
END
GO

IF EXISTS (SELECT 1 FROM SysObjects WHERE TYPE = 'R' AND NAME = 'Masculino_Feminino')
BEGIN
	DROP RULE dbo.Masculino_Feminino
END
GO

IF EXISTS (SELECT 1 FROM SysObjects WHERE TYPE = 'R' AND NAME = 'Debito_Credito')
BEGIN
	DROP RULE dbo.Debito_Credito
END
GO