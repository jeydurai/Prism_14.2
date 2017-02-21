package prism14;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PicturedPanel extends JPanel {
    protected int strokeSize = 1;
    protected Color _shadowColor = Color.BLACK;
    protected boolean shadowed = true;
    protected boolean _highQuality = true;
    protected Dimension _arcs = new Dimension(30, 30);
    protected int _shadowGap = 5;
    protected int _shadowOffset = 4;
    protected int _shadowAlpha = 150;

    protected Color _backgroundColor = Color.LIGHT_GRAY;
    protected BufferedImage image;

    public PicturedPanel(BufferedImage img)
    {
        super();
        setOpaque(false);

        if(img != null)
        {
            image = img;
        }
    }

    @Override
    public void setBackground(Color c)
    {
        _backgroundColor = c;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int shadowGap = this._shadowGap;
        Color shadowColorA = new Color(_shadowColor.getRed(), _shadowColor.getGreen(), _shadowColor.getBlue(), _shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if(_highQuality)
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if(shadowed)
        {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(_shadowOffset, _shadowOffset, width - strokeSize - _shadowOffset,
                    height - strokeSize - _shadowOffset, _arcs.width, _arcs.height);
        }
        else
        {
            _shadowGap = 1;
        }

        RoundRectangle2D.Float rr = new RoundRectangle2D.Float(0, 0, (width - shadowGap), (height - shadowGap), _arcs.width, _arcs.height);

        Shape clipShape = graphics.getClip();

        if(image == null)
        {
            graphics.setColor(_backgroundColor);
            graphics.fill(rr);
        }
        else
        {
            RoundRectangle2D.Float rr2 =  new RoundRectangle2D.Float(0, 0, (width - strokeSize - shadowGap), (height - strokeSize - shadowGap), _arcs.width, _arcs.height);

            graphics.setClip(rr2);
            graphics.drawImage(this.image, 0, 0, null);
            graphics.setClip(clipShape);
//            graphics.setComposite(AlphaComposite.Src);
        }

        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.draw(rr);
        graphics.setStroke(new BasicStroke());
//        graphics.setComposite(AlphaComposite.SrcAtop);
    }
}