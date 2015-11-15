package com.eartrainer.core;


public class QAPair {
    private QAType question;
    private QAType answer;

    public QAPair(QAType question, QAType answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean isAnswerCorrect() {
        return question.equals(answer);
    }

    public QAType getQuestion() {
        return question;
    }
}
