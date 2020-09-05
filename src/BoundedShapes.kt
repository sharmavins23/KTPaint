import java.awt.Paint
import java.awt.Point
import java.awt.Stroke

/**
 * Abstract class defining a bounded drawn shape
 *
 * @author sharmavins23
 *
 * @param startPoint: Starting point of current drawn object
 * @param endPoint:   Ending point of current drawn object
 * @param paint:      Paint color and settings provided
 * @param stroke:     Brush stroke settings provided
 * @param filled:     Whether the shape is filled in
 */
abstract class BoundedShapes(
    startPoint: Point?,
    endPoint: Point?,
    paint: Paint?,
    stroke: Stroke?,
    var isFilled: Boolean
) : Shapes(startPoint!!, endPoint!!, paint!!, stroke!!) {
    /**
     * @return topLeftX: Top-left-most point's X value
     */
    fun getTopLeftX(): Int {
        return Math.min(startPoint.getX().toInt(), endPoint.getX().toInt())
    }

    /**
     * @return topLeftY: Top-left-most point's Y value
     */
    fun getTopLeftY(): Int {
        return Math.min(startPoint.getY().toInt(), endPoint.getY().toInt())
    }

    /**
     * @return width: Width of the object
     */
    fun getWidth(): Int {
        return Math.abs(startPoint.getX().toInt() - endPoint.getX().toInt())
    }

    /**
     * @return height: Height of the object
     */
    fun getHeight(): Int {
        return Math.abs(startPoint.getY().toInt() - endPoint.getY().toInt())
    }
}