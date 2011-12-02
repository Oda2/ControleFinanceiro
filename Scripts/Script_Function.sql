/*
  - Script de criação das Funções
  - Lista de Funções existentes
  -
*/
use Controle_Financeiro
go

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'FN' AND Name = 'Recalculo_Movimento')
BEGIN
  DROP FUNCTION dbo.Recalculo_Movimento;
END
GO

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'FN' AND Name = 'Saldo_Usuario')
BEGIN
  DROP FUNCTION dbo.Saldo_Usuario;
END
GO

CREATE FUNCTION dbo.Saldo_Usuario ( @ID_Usuario  Integer,
                                    @Especifico  Char(1),
                                    @Ano         SmallInt,
                                    @Mes         SmallInt,
                                    @Data        DateTime )
RETURNS Money
WITH ENCRYPTION
AS
BEGIN
  DECLARE @tmp TABLE ( IdentificadorTmp        Integer Identity(1,1),
                       ID_Movimentacao         Integer,
                       ID_Forma_Movimentacao   Char(2),
                       Valor_Total             Money );  
  
  DECLARE @Valor_Aux              Money,
          @ID_Movimentacao        Integer,
          @ID_Forma_Movimentacao  Char(2),
          @IdentificadorTmp       Integer,
          @IdentificadorTmp2      Integer,
          @Tipo_Pagamento         Char(1),
          @Valor_Total            Money,
          @Valor_Aux2             Money;
   
  SET @Valor_Aux = 0;
  /* Se for especifico ele verifica pelo "Ano" e "Mes" */
  IF (@Especifico = 'S')
  BEGIN
    INSERT INTO @tmp ( ID_Movimentacao,
                       ID_Forma_Movimentacao,
                       Valor_Total )
    SELECT ID_Movimentacao,
           ID_Forma_Movimentacao,
           Valor_Total
    FROM Movimentacao
    WHERE ID_Usuario = @ID_USuario
      AND YEAR(Data_Movimentacao) = @Ano AND
          MONTH(Data_Movimentacao) = @Mes;      
  END
  ELSE
  BEGIN
    INSERT INTO @tmp ( ID_Movimentacao,
                       ID_Forma_Movimentacao,
                       Valor_Total )
    SELECT ID_Movimentacao,
           ID_Forma_Movimentacao,
           Valor_Total
    FROM Movimentacao
    WHERE ID_Usuario = @ID_USuario
      AND Data_Movimentacao <= @Data;    
  END

  SELECT TOP 1
         @IdentificadorTmp      = IdentificadorTmp,
         @ID_Movimentacao       = ID_Movimentacao,
         @ID_Forma_Movimentacao = ID_Forma_Movimentacao
  FROM @tmp
  ORDER BY IdentificadorTmp;

  WHILE @@ROWCOUNT > 0
  BEGIN
    SELECT @Tipo_Pagamento = '';
    
    SELECT @Valor_Aux2 = SUM(Valor_Parcela)
    FROM Parcelas
    WHERE ID_Movimentacao = @ID_Movimentacao;

    SELECT @Tipo_Pagamento = Tipo_Pagamento
    FROM Forma_Movimentacao
    WHERE ID_Forma_Movimentacao = @ID_Forma_Movimentacao;

    IF @Tipo_Pagamento = 'D'
    BEGIN
      SET @Valor_Aux2 = @Valor_Aux2 * -1;
    END
   
    SET @Valor_Aux = ROUND(@Valor_Aux + @Valor_Aux2,4);
    SELECT TOP 1
           @IdentificadorTmp      = IdentificadorTmp,
           @ID_Movimentacao       = ID_Movimentacao,
           @ID_Forma_Movimentacao = ID_Forma_Movimentacao
    FROM @tmp
    WHERE IdentificadorTmp > @IdentificadorTmp
    ORDER BY IdentificadorTmp;
  END


  RETURN @Valor_Aux;
END
GO