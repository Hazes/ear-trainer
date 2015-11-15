package com.eartrainer.view;


import android.content.Context;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.eartrainer.core.Answer;

public abstract class AnswerSelectionPanel<E extends Enum<E>> extends TableLayout {

    private RadioGroup radioGroup;
    private ButtonSelectionGrid buttonSelectionGrid;

    public AnswerSelectionPanel(Context context, E[] radioButtonsContent, String[] buttonsGridContent) {
        super(context);

        createAndAddRadioGroup(radioButtonsContent);
        createAndAddButtonsGrid(buttonsGridContent);
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

    public abstract Answer getSelectedAnswer();

    private void createAndAddRadioGroup(E[] radioButtonsContent) {
        Context context = getContext();
        radioGroup = new RadioGroup(context);
        radioGroup.setOrientation(TableLayout.HORIZONTAL);
        radioGroup.setGravity(Gravity.CENTER_HORIZONTAL);
        for (E content : radioButtonsContent) {
            RadioButton radioBtn = new RadioButton(context);
            radioBtn.setText(content.toString());
            radioGroup.addView(radioBtn);
        }

        TableRow tableRow = new TableRow(context);
        tableRow.addView(radioGroup);
        addView(tableRow);
    }

    private void createAndAddButtonsGrid(String[] buttonsContent) {
        buttonSelectionGrid = new ButtonSelectionGrid(getContext(), buttonsContent);
        addView(buttonSelectionGrid);
    }
}
