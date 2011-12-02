use Controle_Financeiro;
GO

/*
	--Script das exclusões dos Defaults
*/

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

/*
	--Script das exclusões ligações das rules
*/
	sp_unbindrule 'usuarios.sexo'
GO
	sp_unbindrule 'usuarios.ativo'
GO
	sp_unbindrule 'Forma_Movimentacao.Tipo_pagamento'
GO
	sp_unbindrule 'Forma_Movimentacao.Aceita_Parcela'
GO
	sp_unbindrule 'Parcelas.Parcela_Entrada'
GO
	sp_unbindrule 'Parcelas.Atualizado'
GO

/*
  -- Script das rules criadas
*/
IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'R' AND Name = 'Sim_Nao')
BEGIN
  DROP RULE dbo.Sim_Nao
END
GO

CREATE RULE dbo.Sim_Nao
AS
  @SimOuNao in ('S','N');

GO

IF EXISTS (SELECT 1 FROM SysObjects WHERE TYPE = 'R' AND NAME = 'Masculino_Feminino')
BEGIN
	DROP RULE dbo.Masculino_Feminino
END
GO

  CREATE RULE dbo.Masculino_Feminino
  AS 
  @MF IN ('M','F');

GO

IF EXISTS (SELECT 1 FROM SysObjects WHERE TYPE = 'R' AND NAME = 'Debito_Credito')
BEGIN
	DROP RULE dbo.Debito_Credito
END
GO

  CREATE RULE dbo.Debito_Credito
  AS
  @DebCred IN ('D','C');
GO

/* Criação das Ligações com as Rules */
	sp_bindrule 'Masculino_Feminino', 'usuarios.sexo'
GO
	sp_bindrule 'Sim_Nao', 'usuarios.ativo'
GO
	sp_bindrule 'Debito_Credito', 'Forma_Movimentacao.Tipo_pagamento'
GO
	sp_bindrule 'Sim_Nao', 'Forma_Movimentacao.Aceita_Parcela'
GO
	sp_bindrule 'Sim_Nao', 'Parcelas.Parcela_Entrada'
GO
	sp_bindrule 'Sim_Nao', 'Parcelas.Atualizado'
GO

/*Criação das ligações com os defaults*/
	sp_bindefault 's', 'usuarios.ativo'
GO
	sp_bindefault 'D', 'Forma_Movimentacao.Tipo_pagamento'
GO
	sp_bindefault 'n', 'Forma_Movimentacao.Aceita_Parcela'
GO
	sp_bindefault 'n',	'Parcelas.Parcela_Entrada'
GO
	sp_bindefault 'n', 'Parcelas.Atualizado'


