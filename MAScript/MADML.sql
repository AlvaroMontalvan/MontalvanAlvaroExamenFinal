-- database: ../MADataBase/MAHormiguero.sqlite

INSERT INTO MACatalogoTipo 
  ( Nombre               ,Descripcion) VALUES
  ('País'               ,'Información de países')                
 ,('Región'             ,'Información de regiones de un país')   
 ,('Provincia'          ,'Provincias de un país')                
 ,('IngestaNativa'      ,'Tipos de ingesta de alimentos')        
 ,('GenoAlimento'       ,'Tipos de genotipo de alimentos')       
 ,('Sexo'               ,'Tipos de sexo de las hormigas');


INSERT INTO MACatalogo 
(IdCatalogoTipo,    Nombre,         Descripcion) VALUES
(1,                 'Ecuador',      'País Ecuador'),                 --1

(2,                 'Costa',        'Región Costa de Ecuador'),      --2
(2,                 'Sierra',       'Región Sierra de Ecuador'),     --3
(2,                 'Oriente',      'Región Oriente de Ecuador'),    --4
(2,                 'Insular',      'Región Galápagos de Ecuador'),  --5

(3,                 'Azuay',         'Provincia de Azuay'),           --6
(3,                 'Bolívar',       'Provincia de Bolívar'),         --7
(3,                 'Cañar',         'Provincia de Cañar'),           --8
(3,                 'Carchi',        'Provincia de Carchi'),          --9
(3,                 'Chimborazo',    'Provincia de Chimborazo'),      --10
(3,                 'Cotopaxi',      'Provincia de Cotopaxi'),        --11
(3,                 'El Oro',        'Provincia de El Oro'),          --12
(3,                 'Esmeraldas',    'Provincia de Esmeraldas'),      --13
(3,                 'Galápagos',     'Provincia de Galápagos'),       --14
(3,                 'Guayas',        'Provincia de Guayas'),          --15
(3,                 'Imbabura',      'Provincia de Imbabura'),        --16
(3,                 'Loja',          'Provincia de Loja'),            --17
(3,                 'Los Ríos',      'Provincia de Los Ríos'),        --18
(3,                 'Manabí',        'Provincia de Manabí'),          --19
(3,                 'Morona Santiago','Provincia de Morona Santiago'), --20
(3,                 'Napo',          'Provincia de Napo'),            --21
(3,                 'Orellana',      'Provincia de Orellana'),        --22
(3,                 'Pastaza',       'Provincia de Pastaza'),         --23
(3,                 'Pichincha',     'Provincia de Pichincha'),       --24
(3,                 'Santa Elena',   'Provincia de Santa Elena'),     --25
(3,                 'Santo Domingo de los Tsáchilas', 'Provincia de Santo Domingo de los Tsáchilas'), --26
(3,                 'Sucumbíos',     'Provincia de Sucumbíos'),       --27
(3,                 'Tungurahua',    'Provincia de Tungurahua'),      --28
(3,                 'Zamora-Chinchipe', 'Provincia de Zamora-Chinchipe'), --29

(4,                 'Carnívoro',    'Alimento carnívoro'),           --30
(4,                 'Herbívoro',    'Alimento herbívoro'),           --31
(4,                 'Omnívoro',     'Alimento omnívoro'),            --32
(4,                 'Insectívoro',  'Alimento insectívoro'),         --33

(5,                 'X',            'Genotipo X de alimento'),       --34
(5,                 'XX',           'Genotipo XX de alimento'),      --35
(5,                 'XY',           'Genotipo XY de alimento'),      --36

(6,                 'Macho',        'Sexo masculino'),               --37
(6,                 'Hembra',       'Sexo femenino'),                --38
(6,                 'Asexual',      'Sexo asexual');                 --39

INSERT INTO MAAlimento
 (IdCatalogoIngestaNativa, IdCatalogoGenoAlimento) VALUES
 (30, 34), (31, 35), (32, 36);


INSERT INTO MAHormiga 
 (TipoHormiga,           IdCatalogoSexo,    IdCatalogoProvincia,   IdCatalogoGenoAlimento, IdCatalogoIngestaNativa) VALUES
 ('Hormiga Roja'         ,37                ,29                    ,34                     ,30)
,('Hormiga Negra'        ,38                ,28                    ,35                     ,31)
,('Hormiga Guerrera'     ,39                ,27                    ,36                     ,32)
,('Hormiga Exploradora'  ,37                ,25                    ,34                     ,33); 


SELECT 
    h.TipoHormiga,
    s.Nombre AS Sexo,
    p.Nombre AS Provincia,
    ga.Nombre AS GenoAlimento,
    i.Nombre AS IngestaNativa
FROM 
    MAHormiga h
    JOIN MACatalogo s ON h.IdCatalogoSexo = s.IdCatalogo
    JOIN MACatalogo p ON h.IdCatalogoProvincia = p.IdCatalogo
    JOIN MACatalogo ga ON h.IdCatalogoGenoAlimento = ga.IdCatalogo
    JOIN MACatalogo i ON h.IdCatalogoIngestaNativa = i.IdCatalogo
WHERE 
    s.IdCatalogoTipo = 6  -- IdCatalogoTipo para Sexo
    AND p.IdCatalogoTipo = 3  -- IdCatalogoTipo para Provincia
    AND ga.IdCatalogoTipo = 5  -- IdCatalogoTipo para GenoAlimento
    AND i.IdCatalogoTipo = 4; -- IdCatalogoTipo para IngestaNativa



