package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.AccountingEntryResponse;
import com.spring.boot.sai.open.api.rest.dto.CreateAccountingEntryRequest;
import com.spring.boot.sai.open.api.rest.dto.CreateAccountingEntryResponse;
import com.spring.boot.sai.open.api.rest.model.repository.AcctRepository;
import com.spring.boot.sai.open.api.rest.model.repository.GlenRepository;
import com.spring.boot.sai.open.api.rest.model.repository.TipDocRepository;
import com.spring.boot.sai.open.api.rest.model.repository.impl.ChairAccountRepositoryImpl;
import com.spring.boot.sai.open.api.rest.model.service.AccountingService;
import com.spring.boot.sai.open.api.rest.util.NumeroEnLetras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api")
public class TipDocController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AccountingService accountingService;
    @GetMapping("/account.move")
    public ResponseEntity<AccountingEntryResponse> getTipDocByClase(@RequestHeader("Access-Token") String token,@RequestBody Filters accountEntry) {
        if (!jwtUtil.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }try {
            List<String> filter = accountEntry.getFilters().get(0);
            String[] classAndBashValues = new String[]{filter.get(2)};
            String[] extractclassAndBashSplit = classAndBashValues[0].split("/");
            String clase = extractclassAndBashSplit[0];
            Integer bash = Integer.valueOf(extractclassAndBashSplit[1]);
            AccountingEntryResponse accountingEntryResponse = accountingService.getAccountingEntryResponse(clase, bash);
            if (accountingEntryResponse == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(accountingEntryResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private TipDocRepository tipDocRepository;
    @Autowired
    private AcctRepository acttRepository;
    @Autowired
    private ChairAccountRepositoryImpl chairAccountRepositoryImpl;
    @PostMapping("/account.move")
    public ResponseEntity<CreateAccountingEntryResponse> createAccountingEntry( @RequestBody CreateAccountingEntryRequest createAccountingEntryRequest) {

        try {
            CreateAccountingEntryResponse createAccountingEntryResponse = new CreateAccountingEntryResponse();
            String clase = createAccountingEntryRequest.getName().split("/")[0];
            String batch = createAccountingEntryRequest.getName().split("/")[1];
            String obteinTipDocByClass = tipDocRepository.findTipDocByClase(clase);
            if (Objects.isNull(obteinTipDocByClass)) {
                return ResponseEntity.notFound().build();
            }

            Timestamp timestamp = new Timestamp(new Date().getTime());
            if(obteinTipDocByClass.equals("CC")) {
                String id_n_local = createAccountingEntryRequest.getLine_ids().get(0).getPartner_pacific_ident().substring(3);
                chairAccountRepositoryImpl.insertarGlen(obteinTipDocByClass, Integer.valueOf(batch),id_n_local);
                for (int i = 0; i < createAccountingEntryRequest.getLine_ids().size(); i++) {
                    int ccost = 1;
                    int debit = (int) createAccountingEntryRequest.getLine_ids().get(i).getDebit();
                    int credit = (int) createAccountingEntryRequest.getLine_ids().get(i).getCredit();
                    double acct =  createAccountingEntryRequest.getLine_ids().get(i).getAccount_id();
                    String id_n = createAccountingEntryRequest.getLine_ids().get(i).getPartner_pacific_ident().substring(3);
                    String period = createAccountingEntryRequest.getDate().substring(0,7).replace("-", "");
                    chairAccountRepositoryImpl.insertarGlDet(id_n, acct, obteinTipDocByClass, Integer.valueOf(batch), ccost, debit, credit, timestamp);
                    chairAccountRepositoryImpl.insertarGl(id_n,acct,obteinTipDocByClass,Integer.valueOf(batch),timestamp,timestamp,ccost,debit,credit,period,"SERVICIO DE WEBSERVICES");
                }

            }else if (obteinTipDocByClass.equals("FP")) {
                String id_n_local = createAccountingEntryRequest.getLine_ids().get(0).getPartner_pacific_ident().substring(3);
                int sumDebit = 0;
                int sumCredit = 0;
                String period = createAccountingEntryRequest.getDate().substring(0,7).replace("-", "");
                int ccost = 1;

                // Primer ciclo para calcular la suma total de créditos y débitos
                for (int i = 0; i < createAccountingEntryRequest.getLine_ids().size(); i++) {
                    int debit = (int) createAccountingEntryRequest.getLine_ids().get(i).getDebit();
                    int credit = (int) createAccountingEntryRequest.getLine_ids().get(i).getCredit();
                    sumDebit += debit;
                    sumCredit += credit;
                }

                if(sumCredit != sumDebit){
                    //Colocar mensaje de error asiento descuadrado
                    return ResponseEntity.badRequest().build();

                }
                String sumCreditEnLetras = NumeroEnLetras.convertir(sumCredit);
                LocalDate currentDate = LocalDate.now();
                chairAccountRepositoryImpl.insertarCarproen(obteinTipDocByClass, Integer.valueOf(batch),id_n_local,currentDate, (double)sumCredit,currentDate.toString(),"SERVICIO DE WEBSERVICES",currentDate,sumCreditEnLetras);

                // Segundo ciclo para insertar registros
                for (int i = 0; i < createAccountingEntryRequest.getLine_ids().size(); i++) {
                    int debit = (int) createAccountingEntryRequest.getLine_ids().get(i).getDebit();
                    int credit = (int) createAccountingEntryRequest.getLine_ids().get(i).getCredit();
                    String id_n = createAccountingEntryRequest.getLine_ids().get(i).getPartner_pacific_ident().substring(3);
                    double acct =  createAccountingEntryRequest.getLine_ids().get(i).getAccount_id();
                    chairAccountRepositoryImpl.insertarCarprode(obteinTipDocByClass, Integer.valueOf(batch),id_n,acct, timestamp,timestamp,"SERVICIO DE WEBSERVICES",(double) credit,(double) debit);
                    chairAccountRepositoryImpl.insertarGl(id_n,acct,obteinTipDocByClass,Integer.valueOf(batch),timestamp,timestamp,ccost,debit,credit,period,"SERVICIO DE WEBSERVICES");
                    String TPOAPLCCION = acttRepository.findAcctByAcct(String.valueOf(acct));
                    if(TPOAPLCCION.equals("CC") || TPOAPLCCION.equals("CP")){
                        chairAccountRepositoryImpl.insertarCarpro(id_n, acct, obteinTipDocByClass, Integer.valueOf(batch), "SERVICIO DE WEBSERVICES", timestamp, ccost, period, sumCredit, credit, debit);
                    }
                }
            }
            if (createAccountingEntryResponse == null) {
                return ResponseEntity.notFound().build();
            }
            createAccountingEntryResponse.setId(1);
            return ResponseEntity.ok().body(createAccountingEntryResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
