import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Point
import java.awt.Stroke
import java.awt.geom.Rectangle2D

/**
 * Defines a rectangle object
 *
 * @author sharmavins23
 *
 * @param startPoint: Starting point of current drawn object
 * @param endPoint:   Ending point of current drawn object
 * @param paint:      Paint color and settings provided
 * @param stroke:     Brush stroke settings provided
 * @param filled:     Whether the shape is filled in
 */
class Rectangle(startPoint: Point?, endPoint: Point?, paint: Paint?, stroke: Stroke?, filled: Boolean) :
    BoundedShapes(startPoint, endPoint, paint, stroke, filled) {

    /**
     * Draw the current object onto the canvas
     *
     * @param g2d: Graphics2D object
     */
    override fun draw(g2d: Graphics2D?) {
        // Set the graphics variables
        g2d!!.paint = paint
        g2d.stroke = stroke

        // Draw either a filled or empty rectangle
        if (isFilled) {
            g2d.fill(
                Rectangle2D.Double(
                    getTopLeftX().toDouble(),
                    getTopLeftY().toDouble(),
                    getWidth().toDouble(),
                    getHeight().toDouble()
                )
            )
        } else {
            g2d.draw(
                Rectangle2D.Double(
                    getTopLeftX().toDouble(),
                    getTopLeftY().toDouble(),
                    getWidth().toDouble(),
                    getHeight().toDouble()
                )
            )
        }
    }
}