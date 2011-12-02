/*
  - Script para criar as procedures
  - Lista de Procedures existentes:
    -
*/

use Controle_Financeiro
go

IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'P' AND name = 'Registro_Movimento')
BEGIN
  DROP PROCEDURE dbo.Registro_Movimento;
END
GO

CREATE PROCEDURE dbo.Registro_Movimento ( @Data_Movimentacao         DateTime,
                                          @ID_Forma_Movimentacao     Char(2),
                                          @Descricao                 VarChar(1000), 
                                          @ID_Usuario                Integer,
                                          @Qtde_Parcela              SmallInt,
                                          @Valor_Parcela_Entrada     Money,
                                          @Valor_Total               Money,
                                          @Parcela_Entrada           Char(1) )                              
                                                                                                            
WITH ENCRYPTION
AS
BEGIN
  /* Validações */
  DECLARE @RaisError Char(1),
          @Erro      VarChar(1000);
  
  SELECT @RaisError = 'N',
         @Erro      = '';

  IF (@Data_Movimentacao IS NULL)         
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: ID_Movimentacao' + char(13),
           @RaisError = 'S';
  END 
  
  IF (@Data_Movimentacao IS NULL)         
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Data_Movimentacao' + char(13),
           @RaisError = 'S';
  END 

  IF (@ID_Forma_Movimentacao IS NULL)     
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: ID_Forma_Movimentacao' + char(13), 
           @RaisError = 'S';
  END

  IF (@ID_Usuario IS NULL)                
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: ID_Usuario' + char(13), 
           @RaisError = 'S';
  END

  IF (@Qtde_Parcela IS NULL)              
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Qtde_Parcela' + char(13),
           @RaisError = 'S';
  END

  IF (@Valor_Parcela_Entrada IS NULL)     
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Valor_Parcela_Entrada' + char(13), 
           @RaisError = 'S'; 
  END

  IF (@Valor_Total IS NULL)               
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Valor_Total' + char(13), 
           @RaisError = 'S';
  END

  IF (@Parcela_Entrada IS NULL)           
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Parcela_Entrada' + char(13), 
           @RaisError = 'S';
  END

  IF (@Parcela_Entrada NOT IN ('S', 'N')) 
  BEGIN 
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Parcela_Entrada (Sim ou Não)' + char(13), 
           @RaisError = 'S';
  END

  IF (@Qtde_Parcela = 0)
  BEGIN
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Quantidade de Parcelas (Zero)' + char(13), 
           @RaisError = 'S';
  END

  IF (@Valor_Total < 0)
  BEGIN
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Valor Total (Menor que Zero)' + char(13),
           @RaisError = 'S';
  END
   
  IF NOT EXISTS(SELECT 1 FROM Forma_Movimentacao WHERE ID_Forma_Movimentacao = @ID_Forma_Movimentacao)
  BEGIN
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: ID_Forma_Movimentacao inválida' + char(13),
           @RaisError = 'S';
  END

  IF (@Valor_Parcela_Entrada > @Valor_Total )
  BEGIN
    SELECT @Erro = @Erro + 'Proc Registro_Movimento..: Valor de Entrada é maior que o Valor Total ' + char(13),
           @RaisError = 'S';
  END

  IF @RaisError = 'S' 
  BEGIN 
    RAISERROR 30002 @Erro 
    RETURN 
  END;
  /* Fim das Validações */
  
  /* Declaração das Variaveis */
  DECLARE @I                        SmallInt,
          @Numero_Parcela_Aux       SmallInt,
          @Valor_Parcela_Aux        Money,
          @Valor_Restante           Money,
          @ID_Movimentacao          Integer,
          @Data_Vencimento_Aux      DateTime;
   
  INSERT INTO dbo.Movimentacao ( ID_Usuario,
                                 Data_Movimentacao,
                                 ID_Forma_Movimentacao,
                                 Valor_Total,
                                 Descricao )
  VALUES ( @ID_Usuario,
           @Data_Movimentacao,
           @ID_Forma_Movimentacao,
           @Valor_Total,
           @Descricao );
  
  SET @ID_Movimentacao = @@Identity;
 
  /* Primeira Parcela (Entrada) */
  IF (@Parcela_Entrada = 'S')
  BEGIN
    INSERT INTO dbo.Parcelas ( ID_Movimentacao,
                               Numero_Parcela,
                               Valor_Parcela,
                               Parcela_Entrada,
                               Data_Vencimento,
                               Data_Pagamento,
                               Atualizado )
    VALUES ( @ID_Movimentacao,
             1,
             @Valor_Parcela_Entrada,
             'S',
             @Data_Movimentacao,
             @Data_Movimentacao,
             'S' );
  END
  
  IF (@Qtde_Parcela > 1)
  BEGIN 
    /* Se não for parcela de entrada, então é feito somente um registro na tabela de "Parcelas"
       para o Movimento, agora se for mais, devemos recalcular o valor de cada parcela. */
    
    SET @Data_Vencimento_Aux = @Data_Movimentacao; 
    IF (@Parcela_Entrada = 'S')
    BEGIN
      SELECT @I = 2, --// Começa na 2 Pois a primeira parcela é a de "Entrada";
             @Valor_Parcela_Aux = ROUND((@Valor_Total - @Valor_Parcela_Entrada),2),
             @Valor_Parcela_Aux = ROUND((@Valor_Parcela_Aux / (@Qtde_Parcela - 1)),2);
    END
    ELSE
    BEGIN
      SELECT @I = 1,
             @Valor_Parcela_Aux = @Valor_Total;
    END
    
    WHILE @I <= @Qtde_Parcela
    BEGIN
      IF (@Qtde_Parcela > 1) AND
         (@I > 1)
      BEGIN
        SET @Data_Vencimento_Aux = DATEADD(MM,1, @Data_Vencimento_Aux);
      END
      
      INSERT INTO dbo.Parcelas ( ID_Movimentacao,
                                 Numero_Parcela,
                                 Valor_Parcela,
                                 Parcela_Entrada,
                                 Data_Vencimento,
                                 Data_Pagamento,
                                 Atualizado )
        VALUES ( @ID_Movimentacao,
                 @I,
                 @Valor_Parcela_Aux,
                 'N',
                 @Data_Vencimento_Aux,
                 NULL,
                 'N' );

      SET @I = @I + 1;
    END 
  
  SELECT @Valor_Restante = (@Valor_Total - (dbo.Recalculo_Movimento(@ID_Movimentacao)))

  IF (@Valor_Restante <> 0)
  BEGIN             
    UPDATE Parcelas
    SET Valor_Parcela = @Valor_Parcela_Aux + @Valor_Restante
    WHERE ID_Movimentacao = @ID_Movimentacao AND
          Numero_Parcela = 2;
  END
  
  END

END
GO

--EXEC dbo.Registro_Movimento  @Data_Movimentacao        = '20111024',
--                             @ID_Forma_Movimentacao    = 'CH',
--                             @Descricao                = 'Nono Teste',
--                             @ID_Usuario               = 1,
--                             @Qtde_Parcela             = 5,
--                             @Valor_Parcela_Entrada    = 1000,
--                             @Valor_Total              = 2000,
--                             @Parcela_Entrada          = 'S';
--GO


IF EXISTS(SELECT 1 FROM SysObjects WHERE Type = 'P' AND Name = 'Recalculo_Movimento')
BEGIN
  DROP PROCEDURE dbo.Recalculo_Movimento;
END
GO

CREATE PROCEDURE dbo.Recalculo_Movimento ( @ID_Movimento Integer,
                                           @Parcela      Char(1) )
WITH ENCRYPTION
AS  
BEGIN
  DECLARE @Valor_Parcela                Money,
          @Valor_Totdas_Parcela         Money,
          @Valor_Aux_Parc               Money,
          @Mensagem                     VarChar(1000),
          @Qtde_Parcela                 SmallInt,
          @Qtde_Parcela_Nao_Atualizada  SmallInt;
          
  SET @Mensagem = '';
  
  IF NOT EXISTS(SELECT 1
                FROM dbo.Movimentacao
                WHERE ID_Movimentacao = @ID_Movimento)
  BEGIN
    SET @Mensagem = 'Não existe a Movimentação informada.';
    SELECT @Mensagem AS Mensagem;
    RETURN;
  END
  
  IF NOT EXISTS(SELECT 1
                FROM dbo.Parcelas
                WHERE ID_Movimentacao = @ID_Movimento)
  BEGIN
    SET @Mensagem = 'Não existe nenhuma Parcela, referente a Movimentação informada.';
    SELECT @Mensagem AS Mensagem;
    RETURN;
  END
 
  SELECT @Valor_Parcela = ROUND(SUM(Valor_Total),2)         
  FROM dbo.Movimentacao
  WHERE ID_Movimentacao = @ID_Movimento;
  
  SELECT @Valor_Totdas_Parcela = ROUND(SUM(Valor_Parcela),2),
         @Qtde_Parcela         = COUNT(Numero_Parcela)
  FROM dbo.Parcelas
  WHERE ID_Movimentacao = @ID_Movimento;
  
  IF (@Parcela = 'N')
  BEGIN        
    IF (@Valor_Totdas_Parcela <> @Valor_Parcela)
    BEGIN
      SELECT @Qtde_Parcela_Nao_Atualizada = COUNT(Numero_Parcela)
      FROM dbo.Parcelas
      WHERE ID_Movimentacao = @ID_Movimento;
    
      SET @Valor_Aux_Parc = 0;
      
      SET @Valor_Aux_Parc = @Valor_Parcela / @Qtde_Parcela_Nao_Atualizada;     

      UPDATE dbo.Parcelas
      SET Valor_Parcela = @Valor_Aux_Parc
      WHERE ID_Movimentacao = @ID_Movimento 
        AND Atualizado      = 'N';
      
      IF (@@ERROR <> 0)
      BEGIN
        SET @Mensagem = 'Erro de atualização na tabela de Movimentação...';
        SELECT @Mensagem AS Mensagem;
        RETURN;
      END
    END
  END
  ELSE
  BEGIN
    IF (@Valor_Totdas_Parcela <> @Valor_Parcela)
    BEGIN
      SELECT @Qtde_Parcela_Nao_Atualizada = COUNT(Numero_Parcela)
      FROM dbo.Parcelas
      WHERE ID_Movimentacao = @ID_Movimento;
    
      SET @Valor_Aux_Parc = 0;
      
      SET @Valor_Aux_Parc = @Valor_Totdas_Parcela / @Qtde_Parcela_Nao_Atualizada;
      
      UPDATE dbo.Parcelas
      SET Valor_Parcela = @Valor_Aux_Parc
      WHERE ID_Movimentacao = @ID_Movimento 
        AND Atualizado      = 'N';
        
      UPDATE dbo.Movimentacao
      SET Valor_Total = @Valor_Totdas_Parcela
      WHERE ID_Movimentacao = @ID_Movimento
      
      IF (@@ERROR <> 0)
      BEGIN
        SET @Mensagem = 'Erro de atualização na tabela Parcelas';
        SELECT @Mensagem AS Mensagem;
        RETURN
      END      
    END
  END
  
  SET @Mensagem = 'Atualizada com sucesso todas as Movimentações e Parcelas';
  SELECT @Mensagem AS Mensagem;
END
GO

EXEC dbo.Recalculo_Movimento @ID_Movimento = 18,
                             @Parcela      = 'N'
                         
                         
                         
                         
                         