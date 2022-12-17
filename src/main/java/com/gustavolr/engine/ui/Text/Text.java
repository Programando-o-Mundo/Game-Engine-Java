package com.gustavolr.engine.ui.Text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;

public class Text extends Entity {

    String label;
    Color textColor;
    Font labelFont;
    byte fontSize;
    FontMetrics metrics;

    public Text(Vector position, String label) {
        super(position, 0, 0);
        
        this.label = label;
        this.fontSize = (byte)TextConstants.DEFAULT_FONT_SIZE;
        this.textColor = TextConstants.DEFAULT_TEXT_COLOR;
        this.labelFont = TextConstants.DEFAULT_TEXT_FONT;

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Font getLabelFont() {
        return labelFont;
    }

    public void setLabelFont(Font labelFont) {
        this.labelFont = labelFont;
    }

	public void render(Graphics g) {
		
		g.setColor(this.textColor);
		g.setFont(labelFont);
		metrics = g.getFontMetrics(labelFont);

		g.drawString(this.label, (int)this.position.x, (int)this.position.y );
	}

}
