package com.eartrainer.session;


import com.eartrainer.core.QAPair;

import java.util.ArrayList;

/**
 *
 */
public class Session {

    private static Session instance = new Session();
    private ArrayList<QAPair> qaHistory;

    private Session() {
        qaHistory = new ArrayList<>();
    }

    public static Session getInstance() {
        return instance;
    }

    public ArrayList<QAPair> getQAHistory() {
        return qaHistory;
    }
}
