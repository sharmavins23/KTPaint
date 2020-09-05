import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Point
import java.awt.Stroke
import java.awt.geom.Line2D

/**
 * Defines a line object
 *
 * @author sharmavins23
 *
 * @param startPoint: Starting point of current drawn object
 * @param endPoint:   Ending point of current drawn object
 * @param paint:      Paint color and settings provided
 * @param stroke:     Brush stroke settings provided
 */
class Line(startPoint: Point?, endPoint: Point?, paint: Paint?, stroke: Stroke?) :
    Shapes(startPoint!!, endPoint!!, paint!!, stroke!!) {

    /**
     * Draw the current object onto the canvas
     *
     * @param g2d: Graphics2D object
     */
    override fun draw(g2d: Graphics2D?) {
        g2d!!.paint = paint
        g2d.stroke = stroke
        g2d.draw(
            Line2D.Double(
                startPoint.x.toDouble(),
                startPoint.y.toDouble(),
                endPoint.x.toDouble(),
                endPoint.y.toDouble()
            )
        )
    }
}