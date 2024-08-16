-- database: ../MADataBase/MAHormiguero.sqlite

DROP TABLE IF EXISTS MACatalogoTipo;
DROP TABLE IF EXISTS MACatalogo;
DROP TABLE IF EXISTS MAAlimento;
DROP TABLE IF EXISTS MAHormiga;

CREATE TABLE MACatalogoTipo
(
    IdCatalogoTipo      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
    ,Nombre             VARCHAR(20) NOT NULL UNIQUE
    ,Descripcion        VARCHAR(90) NOT NULL UNIQUE

    ,Estado             VARCHAR(1) DEFAULT('A') CHECK (ESTADO IN('A','X'))
    ,FechaCreacion      DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModificacion  DATETIME
);

CREATE TABLE MACatalogo
(
    IdCatalogo          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
    ,IdCatalogoTipo     INTEGER NOT NULL
    ,Nombre             VARCHAR(20) NOT NULL UNIQUE
    ,Descripcion        VARCHAR(90) NOT NULL

    ,Estado             VARCHAR(1) DEFAULT('A') CHECK (ESTADO IN('A','X'))
    ,FechaCreacion      DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModificacion  DATETIME

    ,CONSTRAINT fk_CatalogoTipo_Catalogo FOREIGN KEY (IdCatalogoTipo) REFERENCES MACatalogoTipo(IdCatalogoTipo)
);

CREATE TABLE MAAlimento
(
    IdAlimento                  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT          
    ,IdCatalogoIngestaNativa    INTEGER NOT NULL    
    ,IdCatalogoGenoAlimento     INTEGER NOT NULL

    ,Estado                     VARCHAR(1) DEFAULT('A') CHECK (ESTADO IN('A','X'))  
    ,FechaCreacion              DATETIME DEFAULT(datetime('now','localtime'))       
    ,FechaModificacion          DATETIME

    ,CONSTRAINT fk_Alimento_IngestaNativa FOREIGN KEY (IdCatalogoIngestaNativa) REFERENCES MACatalogo(IdCatalogo)
    ,CONSTRAINT fk_Alimento_GenoAlimento FOREIGN KEY (IdCatalogoGenoAlimento) REFERENCES MACatalogo(IdCatalogo)
);

CREATE TABLE MAHormiga
(
    IdHormiga                   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT  
    ,TipoHormiga                VARCHAR(50) NOT NULL                                
    ,IdCatalogoSexo             VARCHAR(50) NOT NULL                                    
    ,IdCatalogoProvincia        VARCHAR(50) NULL                                    
    ,IdCatalogoGenoAlimento     VARCHAR(50) NULL                                    
    ,IdCatalogoIngestaNativa    VARCHAR(50) NULL

    ,Estado                     VARCHAR(1) DEFAULT('A') CHECK (ESTADO IN('A','X'))  
    ,FechaCreacion              DATETIME DEFAULT(datetime('now','localtime'))       
    ,FechaModificacion          DATETIME

    ,CONSTRAINT fk_Hormiga_Sexo FOREIGN KEY (IdCatalogoSexo) REFERENCES MACatalogo(IdCatalogo)                 
    ,CONSTRAINT fk_Hormiga_Provincia FOREIGN KEY (IdCatalogoProvincia) REFERENCES MACatalogo(IdCatalogo)       
    ,CONSTRAINT fk_Hormiga_GenoAlimento FOREIGN KEY (IdCatalogoGenoAlimento) REFERENCES MACatalogo(IdCatalogo) 
    ,CONSTRAINT fk_Hormiga_IngestaNativa FOREIGN KEY (IdCatalogoIngestaNativa) REFERENCES MACatalogo(IdCatalogo)
);



