package com.dylan.projet.ApiDemo.controllers;

import com.dylan.projet.ApiDemo.services.interfaces.StatisticService;
import com.dylan.projet.ApiDemo.utils.TransactionSumDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {


    private final StatisticService statisticService;

    @GetMapping("/sumByDate/users/{id}")
    public ResponseEntity<List<TransactionSumDetails>> findSumTransactionByDay(
            @PathVariable("id") Integer id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate) {
        return ResponseEntity.ok(statisticService.findSumTransactionByDay(startDate, endDate, id));
    }
    @GetMapping("/accountBalance/users/{id}")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(statisticService.getAccountBalance(userId));
    }
    @GetMapping("/highestTransfert/users/{id}")
    public ResponseEntity<BigDecimal> highestTransfert(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(statisticService.highestTransfert(userId));
    }

    @GetMapping("/highestDeposit/users/{id}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(statisticService.highestDeposit(userId));
    }


}
