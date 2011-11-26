/*
  - Script de exclusão dos dados
  - Script de exclusão das tabelas
*/

/* Exclusão dos dados */
use Controle_Financeiro
go

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Parcelas')
BEGIN
  DELETE FROM dbo.Parcelas;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Movimentacao')
BEGIN
  DELETE FROM dbo.Movimentacao;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Usuarios')
BEGIN
  DELETE FROM dbo.Usuarios;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Forma_Movimentacao')
BEGIN
  DELETE FROM dbo.Forma_Movimentacao;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Categorias')
BEGIN
  DELETE FROM dbo.Categorias;
END
GO

/* Exclusão das tabelas */
IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Parcelas')
BEGIN
  DROP TABLE dbo.Parcelas;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Movimentacao')
BEGIN
  DROP TABLE dbo.Movimentacao;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Forma_Movimentacao')
BEGIN
  DROP TABLE dbo.Forma_Movimentacao;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'U' AND Name = 'Usuarios')
BEGIN
  DROP TABLE dbo.Usuarios;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'V' AND Name = 'Saldos')
BEGIN
  DROP VIEW dbo.Saldos;
END
GO