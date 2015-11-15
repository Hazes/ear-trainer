package com.eartrainer.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.eartrainer.utils.Utils;

/**
 *
 */
public class ButtonSelectionGrid
        extends TableLayout
        implements View.OnClickListener {

    private Button buttonClicked;
    private int selectionColor;

    public ButtonSelectionGrid(Context context, String[] contents) {
        this(context, contents, Color.CYAN);
    }

    public ButtonSelectionGrid(Context context, String[] contents, int selectionColor) {
        super(context);
        this.selectionColor = selectionColor;

        setStretchAllColumns(true);
        createButtonsGrid(context, contents);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < getChildCount(); ++i) {
            TableRow tableRow = (TableRow)getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); ++j)
                tableRow.getChildAt(j).setEnabled(enabled);
        }
    }

    private void createButtonsGrid(Context context, String[] contents) {
        // square grid
        int gridSize = (int)Math.ceil(Math.sqrt(contents.length));
        int currentIndex = 0;
        for (int row = 0; row < gridSize; ++row) {
            TableRow tableRow = new TableRow(context);
            for (int col = 0; col < gridSize && currentIndex < contents.length; ++col) {
                Button newButton = new Button(context);
                newButton.setOnClickListener(this);
                newButton.setText(contents[currentIndex]);
                tableRow.addView(newButton);
                currentIndex++;
            }
            addView(tableRow);
        }
    }

    public String getSelectedItem() {
        if (buttonClicked != null)
            return buttonClicked.getText().toString();
        else {
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        if (buttonClicked != null)
            buttonClicked.getBackground().clearColorFilter();
        buttonClicked = (Button)v;
        int darkenedSelectionColor = Utils.setAlpha(selectionColor, 128);
        buttonClicked.getBackground().setColorFilter(new PorterDuffColorFilter(darkenedSelectionColor, PorterDuff.Mode.MULTIPLY));
    }

    public int getSelectionColor() {
        return selectionColor;
    }

    public void setSelectionColor(int selectionColor) {
        this.selectionColor = selectionColor;
    }
}
