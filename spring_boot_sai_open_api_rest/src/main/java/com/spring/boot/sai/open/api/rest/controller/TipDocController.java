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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                //chairAccountRepositoryImpl.insertarGlen(obteinTipDocByClass, Integer.valueOf(batch));
                for (int i = 0; i < createAccountingEntryRequest.getLine_ids().size(); i++) {
                    //Queda la duda de sacar el acct y ccost
                    int ccost = 0;
                    int debit = (int) createAccountingEntryRequest.getLine_ids().get(i).getDebit();
                    int credit = (int) createAccountingEntryRequest.getLine_ids().get(i).getCredit();
                    String id_n = createAccountingEntryRequest.getLine_ids().get(i).getPartner_pacific_ident().substring(3);
                    chairAccountRepositoryImpl.insertarGlDet(id_n, 1000333, obteinTipDocByClass, Integer.valueOf(batch), ccost, debit, credit, timestamp);
                    chairAccountRepositoryImpl.insertarGl(id_n,10087033,obteinTipDocByClass,Integer.valueOf(batch),timestamp,timestamp,ccost,debit,credit,"PERIODO","DESCRIPCION");
                }

            }else if(obteinTipDocByClass.equals("FP")) {
                //chairAccountRepositoryImpl.insertarCarproen(obteinTipDocByClass, Integer.valueOf(batch),id_n,timestamp,100.000,timestamp,"observ",timestamp,"letras");
                for (int i = 0; i < createAccountingEntryRequest.getLine_ids().size(); i++) {
                    double debit =  createAccountingEntryRequest.getLine_ids().get(i).getDebit();
                    double credit = createAccountingEntryRequest.getLine_ids().get(i).getCredit();
                    String id_n = createAccountingEntryRequest.getLine_ids().get(i).getPartner_pacific_ident().substring(3);
                    chairAccountRepositoryImpl.insertarCarprode(obteinTipDocByClass, Integer.valueOf(batch),id_n,1000333, timestamp,timestamp,"DESCRIPCION",credit,debit);

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

    @Autowired
    private AcctRepository acttRepository;

    @Autowired
    private GlenRepository glenRepository;

    @PostMapping("/actt/{acct}")
    public void getTipAcct(@PathVariable String acct) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formatter.parse("2020-01-01");

        //glenRepository.insertGlen(
              //  "XLS",
            //    "983",
          //      fecha
        //);
        //date

        Timestamp duedate = Timestamp.valueOf("2022-01-01 00:00:00");

        chairAccountRepositoryImpl.insertarGlDet("800203189", 53050101, "C3", 77, 0, 50000000, 19265637, duedate);

    }

}
