package com.eartrainer.view;


import android.content.Context;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.eartrainer.core.QAType;

public abstract class AnswerSelectionPanel extends TableLayout {

    private RadioGroup radioGroup;
    private ButtonSelectionGrid buttonSelectionGrid;

    public AnswerSelectionPanel(Context context, String[] radioButtonsContent, String[] buttonsGridContent) {
        super(context);

        createAndAddRadioGroup(radioButtonsContent);
        createAndAddButtonsGrid(buttonsGridContent);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < radioGroup.getChildCount(); ++i)
            radioGroup.getChildAt(i).setEnabled(enabled);
        buttonSelectionGrid.setEnabled(enabled);
    }

    public String getSelectedRadioButtonContent() {
        int checkedButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedButtonId != -1) {
            RadioButton selectedButton = (RadioButton) radioGroup.findViewById(checkedButtonId);
            return selectedButton.getText().toString();
        } else {
            return null;
        }
    }

    public String getSelectedGridButtonContent() {
        return buttonSelectionGrid.getSelectedItem();
    }

    public abstract QAType getSelectedAnswer();

    private void createAndAddRadioGroup(String[] radioButtonsContent) {
        Context context = getContext();
        radioGroup = new RadioGroup(context);
        radioGroup.setOrientation(TableLayout.HORIZONTAL);
        for (String content : radioButtonsContent) {
            RadioButton radioBtn = new RadioButton(context);
            radioBtn.setText(content);
            radioGroup.addView(radioBtn);
        }

        TableRow tableRow = new TableRow(context);
        tableRow.addView(radioGroup);
        tableRow.setGravity(Gravity.CENTER);
        addView(tableRow);
    }

    private void createAndAddButtonsGrid(String[] buttonsContent) {
        buttonSelectionGrid = new ButtonSelectionGrid(getContext(), buttonsContent);
        addView(buttonSelectionGrid);
    }
}
