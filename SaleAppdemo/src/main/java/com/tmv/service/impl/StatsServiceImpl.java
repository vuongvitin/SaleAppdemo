/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service.impl;

import com.tmv.repository.StatsRepository;
import com.tmv.service.StatsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepository;
    
    @Override
    public List<Object> cateStats() {
        return this.statsRepository.cateStats();
    }

    @Override
    public List<Object> productStats(String kw, Date fromDate, Date toDate) {
        return this.statsRepository.productStats(kw, fromDate, toDate);
    }

    @Override
    public List<Object> productMonthStats(String kw, Date fromDate, Date toDate) {
        return this.statsRepository.productMonthStats(kw, fromDate, toDate);
    }
    
}
