package com.spring.boot.sai.open.api.rest.model.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Repository
public class ChairAccountRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertarGlen(String tipo, Integer batchNumber, String idn) {
        String insertQuery = "INSERT INTO GLEN (E, S, TIPO, BATCH, FECHA, USERNAME, REVISADO, REVISOR, FECHA_REVISION, EXPORTADA, ESTADO, DESCRIPCION, ID_N) " +
                "VALUES (1, 1, :tipo, :batchNumber, :fecha, 'SAI', 'N', NULL, NULL, 'N', NULL, NULL, :idn)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("tipo", tipo)
                .setParameter("batchNumber", batchNumber)
                .setParameter("idn", idn)
                .setParameter("fecha", new java.sql.Date(new Date().getTime()));

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarGlDet(String id_n, Double acct, String tipo, Integer batch, Integer ccost, Integer debit, Integer credit, Date duedate) {
        String insertQuery = "INSERT INTO GLDET (CONTEO, ID_N, ACCT, E, S, TIPO, BATCH, CUOTA, INVC, DEPTO, CCOST, ACTIVIDAD, DEBIT, CREDIT, PERIOD, BASE, DESCRIPCION, CLOSING, CONCIL, CRUCE, DESTINO, DUEDATE, PROYECTO) " +
                "VALUES ((SELECT COALESCE(MAX(CONTEO), 0) + 1 FROM GLDET), :id_n, :acct, 1, 1, :tipo, :batch, 0, :batch, 0, :ccost, '', :debit, :credit, '', 0, '', '', '', :tipo, 1, :duedate, '')";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("id_n", id_n)
                .setParameter("acct", acct)
                .setParameter("tipo", tipo)
                .setParameter("batch", batch)
                .setParameter("ccost", ccost)
                .setParameter("debit", debit)
                .setParameter("credit", credit)
                .setParameter("duedate", duedate);

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarGl(String id_n, Double acct, String tipo, Integer batch, Date fecha, Date duedate, Integer ccost, Integer credit, Integer debit, String period, String descripcion) {
        String insertQuery = "INSERT INTO GL (ID_N, ACCT, E, S, TIPO, BATCH, FECHA, DUEDATE, INVC, DEPTO, CCOST, ACTIVIDAD, PERIOD, DESCRIPCION, CLOSING, CONCIL, CRUCE, USERNAME, DESTINO, PROYECTO, BASE, CREDIT, DEBIT, CUOTA, PRORRATEADO, ESTADO, MAYOR_VALOR, COD_FLUJOEFE, CONSEC_CARPRODE) " +
                "VALUES (:id_n, :acct, 1, 1, :tipo, :batch, :fecha, :duedate, :batch, 0, :ccost, NULL, :period, :descripcion, 'False', 'False', :tipo, NULL, 1, NULL, 0, :credit, :debit, 1, 'N', NULL, NULL, NULL, NULL)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("id_n", id_n)
                .setParameter("acct", acct)
                .setParameter("tipo", tipo)
                .setParameter("batch", batch)
                .setParameter("fecha", fecha)
                .setParameter("duedate", duedate)
                .setParameter("ccost", ccost)
                .setParameter("credit", credit)
                .setParameter("debit", debit)
                .setParameter("period", period)
                .setParameter("descripcion", descripcion);

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarCarproen(String tipo, int batch, String id_n, LocalDate fecha, Double total, String fecha_hora, String observ, LocalDate duedate, String letras) {
        String insertQuery = "INSERT INTO CARPROEN (E, S, TIPO, BATCH, ID_N, FECHA, TOTAL, USERNAME, FECHA_HORA, OBSERV, BANCO, CHEQUE, DUEDATE, LETRAS, IDVEND, SHIPTO, EXPORTADA, ENTREGADO, REVISADO, REVISOR, FECHA_REVISION, IMPRESO, DOC_FISICO, CHEQUE_POSTF, FECHA_CHEQUE, PROYECTO, SALDO_DEUDA, SALDO_DEUDA_ABONO, PONUMBER, INTERES_IMPLICITO, DETALLE, FECHA_CONTAB_CONSIG, DETERIORO_ESFA, CONCEPTO_NOTAFE, ENVIADO, CUFE, SUBTOTAL, SALESTAX, IMPCONSUMO, TOTAL_REAL, FECHA_RESPUESTA_DIAN, ID_BINARIO, SIN_CRUCE) " +
                "VALUES (1, 1, :tipo, :batch, :id_n, :fecha, :total, 'SAI', :fecha_hora, :observ, '', '', :duedate, :letras, 1, 0, 'N', 'N', 'N', NULL, NULL, 'N', NULL, 'false', NULL, NULL, NULL, NULL, '', 'N', '', 'N', 'N', NULL, 'N', NULL, 0, 0, 0, 0, NULL, NULL, NULL)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("tipo", tipo)
                .setParameter("batch", batch)
                .setParameter("id_n", id_n)
                .setParameter("fecha", fecha)
                .setParameter("total", total)
                .setParameter("fecha_hora", fecha_hora)
                .setParameter("observ", observ)
                .setParameter("duedate", duedate)
                .setParameter("letras", letras);

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarCarprode(String tipo, Integer batch, String id_n, Double acct, Date fecha, Date duedate, String descripcion, Double credit, Double debit) {
        String insertQuery = "INSERT INTO CARPRODE (TIPO, BATCH, ID_N, ACCT, E, S, CRUCE, INVC, FECHA, DUEDATE, DPTO, CCOST, ACTIVIDAD, DESCRIPCION, DIAS, DESTINO, TIPO_REF, REFERENCIA, TIPO_IMP, NRO_IMP, CONCEPTO_IMP, BANCO, CHEQUE, PROYECTO, CONCEPTO_PAGO, ID_TIPOCARTERA, INVC_ENTERO, CHEQUE_POSTF, FECHA_CHEQUE, SALDO, CREDIT, TASA_CAMBIO, CREDITO_US, DEBITO_US, BASE, DEBIT, CUOTA, FECHA_CONSIG, FECHA_FACTURA, MAYOR_VALOR, VALOR_IMPUESTO, IMPORT, COD_FLUJOEFE, IDVEND, PORC_TASA, TIEMPO_MESES, PAGO_DISP) " +
                "VALUES (:tipo, :batch, :id_n, :acct, 1, 1, :tipo, :batch, :fecha, :duedate, NULL, NULL, '', :descripcion, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, '', 0, 'false', NULL, 0, :credit, 0, 0, 0, 0, :debit, 1, NULL, NULL, NULL, NULL, 'N', 0, NULL, 0, 0, 'N')";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("tipo", tipo)
                .setParameter("batch", batch)
                .setParameter("id_n", id_n)
                .setParameter("acct", acct)
                .setParameter("fecha", fecha)
                .setParameter("duedate", duedate)
                .setParameter("descripcion", descripcion)
                .setParameter("credit", credit)
                .setParameter("debit", debit);

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarCust(String id_n, String company, String company_extendido, String addr1, String phone1, String phone2, String pais, String medico, BigDecimal creditlmt, String cliente, String proveedor, String city, String nit, String id_tipocartera, String regimen, String inactivo, short zona, short idvend) {
        String insertQuery = "INSERT INTO CUST (ID_N, COMPANY, COMPANY_EXTENDIDO, ADDR1, PHONE1, PHONE2, PAIS, MEDICO, CREDITLMT, CLIENTE, PROVEEDOR, CITY, NIT, ID_TIPOCARTERA, REGIMEN, INACTIVO, ZONA, IDVEND) " +
                "VALUES (:id_n, :company, :company_extendido, :addr1, :phone1, :phone2, :pais, :medico, :creditlmt, :cliente, :proveedor, :city, :nit, :id_tipocartera, :regimen, :inactivo, :zona, :idvend)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("id_n", id_n)
                .setParameter("company", company)
                .setParameter("company_extendido", company_extendido)
                .setParameter("addr1", addr1)
                .setParameter("phone1", phone1)
                .setParameter("phone2", phone2)
                .setParameter("pais", pais)
                .setParameter("medico", medico)
                .setParameter("creditlmt", creditlmt)
                .setParameter("cliente", cliente)
                .setParameter("proveedor", proveedor)
                .setParameter("city", city)
                .setParameter("nit", nit)
                .setParameter("id_tipocartera", id_tipocartera)
                .setParameter("regimen", regimen)
                .setParameter("inactivo", inactivo)
                .setParameter("zona", zona)
                .setParameter("idvend", idvend);

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarShipto(String id_n, String company, String company_extendido, String primer_nombre, String segundo_nombre,
                               String primer_apellido, String segundo_apellido, String addr1, String phone1, String phone2,
                               String email, String email_fac_elec, BigDecimal creditlmt, String comment1, String city,
                               Integer succliente, Integer IDVEND) {

        String insertQuery = "INSERT INTO SHIPTO (ID_N, COMPANY, COMPANY_EXTENDIDO, PRIMER_NOMBRE, SEGUNDO_NOMBRE, " +
                "PRIMER_APELLIDO, SEGUNDO_APELLIDO, ADDR1, PHONE1, PHONE2, EMAIL, EMAIL_FAC_ELEC, CREDITLMT, COMMENT1, " +
                "CITY, SUCCLIENTE, IDVEND) " +
                "VALUES (:id_n, :company, :company_extendido, :primer_nombre, :segundo_nombre, :primer_apellido, " +
                ":segundo_apellido, :addr1, :phone1, :phone2, :email, :email_fac_elec, :creditlmt, :comment1, :city, " +
                ":succliente, :idvend)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("id_n", id_n)
                .setParameter("company", company)
                .setParameter("company_extendido", company_extendido)
                .setParameter("primer_nombre", primer_nombre)
                .setParameter("segundo_nombre", segundo_nombre)
                .setParameter("primer_apellido", primer_apellido)
                .setParameter("segundo_apellido", segundo_apellido)
                .setParameter("addr1", addr1)
                .setParameter("phone1", phone1)
                .setParameter("phone2", phone2)
                .setParameter("email", email)
                .setParameter("email_fac_elec", email_fac_elec)
                .setParameter("creditlmt", creditlmt)
                .setParameter("comment1", comment1)
                .setParameter("city", city)
                .setParameter("succliente", succliente)
                .setParameter("idvend", IDVEND);

        insertNativeQuery.executeUpdate();
    }
    @Transactional
    public void insertarCarpro(String id_n, Double acct, String tipo, int batch, String descripcion, Date fecha,
                               int ccost, String period, double saldo, double credit, double debit) {

        String insertQuery = "INSERT INTO CARPRO (ID_N, ACCT, TIPO, BATCH, E, S, DESCRIPCION, FECHA, DUEDATE, INVC, DEPTO, CCOST, ACTIVIDAD, PERIOD, " +
                "CONCIL, CRUCE, BENEF, ABONO, IDVEND, CONCEPTO, USUARIO, TIPO_IMP, NRO_IMP, CONCEPTO_IMP, SALDO_REPORTE, PROYECTO, BANCO, CHEQUE, " +
                "CONCEPTO_PAGO, ID_TIPOCARTERA, COMENTARIO, CHEQUE_POSTF, FECHA_CHEQUE, VENCIMIENTO, DIAPAGO, SALDO, BASE, CREDIT, DEBIT, " +
                "TASA_CAMBIO, SALDO_US, CREDITO_US, DEBITO_US, CUOTA, SHIPTO, FECHA_CONSIG, ESTADO_DEUDA, COD_FLUJOEFE, PORC_TASA, TIEMPO_MESES) " +
                "VALUES (:id_n, :acct, :tipo, :batch, 1, 1, :descripcion, :fecha, :fecha, :batch, 0, :ccost, '', :period, NULL, :tipo, :id_n, NULL, 1, " +
                "NULL, NULL, '', 0, 0, 0, '', NULL, NULL, NULL, NULL, NULL, 'false', NULL, NULL, 'false', :saldo, 0, :credit, :debit, 0, 0, 0, 0, 1, 0, NULL, NULL, NULL, 0, 0)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("id_n", id_n)
                .setParameter("acct", acct)
                .setParameter("tipo", tipo)
                .setParameter("batch", batch)
                .setParameter("descripcion", descripcion)
                .setParameter("fecha", fecha)
                .setParameter("ccost", ccost)
                .setParameter("period", period)
                .setParameter("saldo", saldo)
                .setParameter("credit", credit)
                .setParameter("debit", debit);

        insertNativeQuery.executeUpdate();
    }

    @Transactional
    public void insertarTributaria(String id_n, String company, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, Short tipo_contribuyente, Short tdoc) {
        String insertQuery = "INSERT INTO TRIBUTARIA (ID_N, COMPANY, PRIMER_NOMBRE, SEGUNDO_NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, TIPO_CONTRIBUYENTE, TDOC) " +
                "VALUES (:id_n, :company, :primer_nombre, :segundo_nombre, :primer_apellido, :segundo_apellido, :tipo_contribuyente, :tdoc)";

        Query insertNativeQuery = entityManager.createNativeQuery(insertQuery)
                .setParameter("id_n", id_n)
                .setParameter("company", company)
                .setParameter("primer_nombre", primer_nombre)
                .setParameter("segundo_nombre", segundo_nombre)
                .setParameter("primer_apellido", primer_apellido)
                .setParameter("segundo_apellido", segundo_apellido)
                .setParameter("tipo_contribuyente", tipo_contribuyente)
                .setParameter("tdoc", tdoc);

        insertNativeQuery.executeUpdate();
    }

}
