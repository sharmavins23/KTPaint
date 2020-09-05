import java.awt.*

/**
 * Abstract class defining a drawn shape
 *
 * @author sharmavins23
 */
abstract class Shapes {
    lateinit var startPoint: Point
    lateinit var endPoint: Point
    var paint: Paint
    var stroke: Stroke

    /**
     * Default constructor for shape object
     */
    constructor() {
        // Initializing our drawing configuration
        stroke = BasicStroke(5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
        paint = Color.BLACK
    }

    /**
     * Overloaded constructor for shape object
     *
     * @param startPoint: Starting point of current drawn object
     * @param endPoint:   Ending point of current drawn object
     * @param paint:      Paint color and settings provided
     * @param stroke:     Brush stroke settings provided
     */
    constructor(startPoint: Point, endPoint: Point, paint: Paint, stroke: Stroke) {
        this.startPoint = startPoint
        this.endPoint = endPoint
        this.paint = paint
        this.stroke = stroke
    }

    /**
     * Abstract function to draw the current object
     *
     * @param g2d: Graphics2D object
     */
    abstract fun draw(g2d: Graphics2D?)
}