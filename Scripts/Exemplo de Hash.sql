DECLARE @HashThis nvarchar(4000),
        @Teste nvarchar(4000);

SELECT @HashThis = CONVERT(nvarchar(4000),'Teste 2');
SELECT HashBytes('SHA', @HashThis);
SELECT @Teste = CONVERT(nvarchar(4000),'Teste 2');
SELECT HashBytes('SHA', @Teste);
GO


