-- ============================================================================
-- Script: Party Reference Data Directory - SQL Server
-- Service Domain: Party Reference Data Directory (BIAN)
-- Descripción: Tabla única derivada del contrato API BS Customer Service V1
-- Base de datos: SQL Server 2019+
-- ============================================================================

-- ============================================================================
-- MAPEO: Contrato API → Columnas DB
-- ============================================================================
-- API (partyId)                            → customer_id (PK, autogenerado)
-- API (partyIdentificationType)            → document_type (CHAR(1))
-- API (identifierValue)                    → document_number (VARCHAR(15))
-- API (partyNameType: Name)                → name (Persona: nombre de pila)
-- API (partyNameType: LegalName)           → name (Organizacion: razón social)
-- API (partyNameType: LastNamePaternal)    → paternal_surname (solo Persona)
-- API (partyNameType: LastNameMaternal)    → maternal_surname (solo Persona, opcional)
-- API (partyNameType: TradeName)           → trade_name (solo Organizacion, opcional)
-- API (partyNameType: FullName)            → Derivado: se calcula por código
-- API (partyType)                          → customer_type (CHAR(1): P/O)
-- API (residencyStatus)                    → residency_status (CHAR(1): N/E)
-- API (DirectoryEntryDate: Creacion)       → created_at
-- API (DirectoryEntryDate: Modificacion)   → updated_at
-- ============================================================================

-- ============================================================================
-- MAPEO: document_type ↔ PartyIdentificationTypeValues
-- ============================================================================
-- Código | Enum API                                    | Longitud | Numérico
-- -------|---------------------------------------------|----------|----------
--  1     | Nationalregistrationidentificationnumber    | 8 exacto | Sí
--  2     | Identitycardnumber                          | 12 max   | No
--  3     | Passportnumber                              | 12 max   | No
--  6     | Registrationauthorityidentification         | 11 exacto| Sí
-- ============================================================================


-- USE master;

-- CREATE DATABASE db_customer_desa;

-- USE db_customer_desa;


CREATE TABLE customers
(
    customer_id         BIGINT          NOT NULL IDENTITY(1,1),
    document_type       CHAR(1)         NOT NULL,
    document_number     VARCHAR(15)     NOT NULL,
    name                NVARCHAR(150)   NOT NULL,
    paternal_surname    NVARCHAR(100)   NULL,
    maternal_surname    NVARCHAR(100)   NULL,
    trade_name          NVARCHAR(150)   NULL,
    customer_type       CHAR(1)         NOT NULL,
    residency_status    CHAR(1)         NOT NULL,
    created_at          DATETIME        NOT NULL DEFAULT SYSUTCDATETIME(),
    updated_at          DATETIME        NULL,

    -- PK
    CONSTRAINT PK_customers
        PRIMARY KEY (customer_id),

    -- Unicidad: tipo + número de documento
    CONSTRAINT UQ_customers_document
        UNIQUE (document_type, document_number),

    -- document_type: códigos internos alineados al enum API
    CONSTRAINT CK_customers_document_type
        CHECK (document_type IN ('1','2','3','6')),

    -- customer_type: P=Persona, O=Organizacion
    CONSTRAINT CK_customers_customer_type
        CHECK (customer_type IN ('P','O')),

    -- residency_status: N=Nacional, E=Extranjero
    CONSTRAINT CK_customers_residency_status
        CHECK (residency_status IN ('N','E')),
);
GO

-- ============================================================================
-- ÍNDICES (alineados a los filtros del endpoint Retrieve list)
-- ============================================================================

-- Filtro por document_number (query param: identifierValue)
CREATE NONCLUSTERED INDEX IX_customers_document_number
    ON customers (document_number)
    INCLUDE (document_type, customer_type, residency_status);
GO

-- Filtro por customer_type (query param: partyType)
CREATE NONCLUSTERED INDEX IX_customers_customer_type
    ON customers (customer_type)
    INCLUDE (document_number, residency_status);
GO

-- Filtro por residency_status (query param: residencyStatus)
CREATE NONCLUSTERED INDEX IX_customers_residency_status
    ON customers (residency_status)
    INCLUDE (document_number, customer_type);
GO