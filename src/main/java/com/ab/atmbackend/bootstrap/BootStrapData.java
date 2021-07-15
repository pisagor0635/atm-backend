package com.ab.atmbackend.bootstrap;

import com.ab.atmbackend.model.ATMInitialMoneyReserve;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        ATMInitialMoneyReserve banknotesFifty = new ATMInitialMoneyReserve(50, 10);
        ATMInitialMoneyReserve banknotesTwenty = new ATMInitialMoneyReserve(20, 30);
        ATMInitialMoneyReserve banknotesTen = new ATMInitialMoneyReserve(10, 30);
        ATMInitialMoneyReserve banknotesFive = new ATMInitialMoneyReserve(5, 20);

    }
}
