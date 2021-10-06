/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmv.service;

import java.util.Date;
import java.util.List;

/**
 *
 * @author PC
 */
public interface StatsService {
    List<Object> cateStats();
    List<Object> productStats(String kw, Date fromtDate, Date toDate);
    List<Object> productMonthStats(String kw, Date fromDate, Date toDate);
}
