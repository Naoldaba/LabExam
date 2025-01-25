package com.trading.hibretstock.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;

import com.trading.hibretstock.Models.Trade;
import com.trading.hibretstock.Models.Transaction;
import com.trading.hibretstock.Models.User;
import com.trading.hibretstock.Services.UserDashboardSvc;
import com.trading.hibretstock.Utils.FileReaderUtil;
import com.trading.hibretstock.Utils.SessionMgmtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDashboardController
{
    private static final Logger logger = LoggerFactory.getLogger(UserDashboardController.class);

    @Autowired
    private UserDashboardSvc userDashboardService;

    @Autowired
    private SessionMgmtUtil sessionMgmtUtils;

    @GetMapping(value = "/dashboard" )
    public ModelAndView retrieveWatchList (HttpServletRequest request )
    {
        if (!this.sessionMgmtUtils.doesSessionExist(request)) 
        {
            logger.info("Please login to access this page");
            ModelAndView mv = new ModelAndView("signin-form");
            mv.addObject("user", new User());
            mv.addObject("errorMessage", "Please login to access this page");
            return mv;

        }

        String email = (String) request.getSession().getAttribute("user");
        User user = this.userDashboardService.getProfileAttributes(email);

        Map<String, List<? extends Object>> stockList = this.userDashboardService.retrieveSellList(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("stockList", stockList);
        mv.setViewName("user-dashboard");
        return mv;
    }

    @PostMapping(value = "/dashboard/sell" )
    public ModelAndView sellShares (HttpServletRequest request )
    {
        logger.info("sellShares::");
        String email = (String) request.getSession().getAttribute("user");
        User user = this.userDashboardService.getProfileAttributes(email);

        String[] selling = request.getParameterValues("checkedRows");

        Map<String, List<? extends Object>> stockList = (Map<String, List<? extends Object>>) request.getSession().getAttribute("stockList");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("dashboard-transaction");

        mv.addObject("transactionBuyId", new Random().nextInt(1000000));
        mv.addObject("transactionSellId", new Random().nextInt(1000000));

        Transaction t = this.userDashboardService.completeTransaction(user, selling);
        logger.info("showTradeTransaction::"+t.getId());

        List<Trade> currentSellTrades = new ArrayList<Trade>();
        currentSellTrades = this.userDashboardService.getSellTradesUsingCurrentTransaction (user , t);
        
        mv.addObject("tradeSellList", currentSellTrades);

        Double serviceCharge = Double.parseDouble( FileReaderUtil.readServiceChargeValue() );
        Double sellTax = (serviceCharge/100 * this.userDashboardService.getTotalPrice(currentSellTrades));
        mv.addObject("serviceSellTax", sellTax);

        mv.addObject("totalSellPrice", this.userDashboardService.getTotalPrice(currentSellTrades) + sellTax );

        return mv;
    }



}
