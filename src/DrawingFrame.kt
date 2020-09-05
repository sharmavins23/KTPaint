import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import java.util.*
import javax.swing.*

/**
 * Main application frame definition
 *
 * @author sharmavins23
 */
class DrawingFrame : JFrame("Kotlin Paint") {
    // Top widget panel - contains two lines of widgets
    private val topPanel: JPanel

    // 'First line' panel and widgets
    private val firstLine: JPanel

    private val undo: JButton
    private val clear: JButton
    private val shapeLabel: JLabel
    private val shapeSelect: JComboBox<String>
    private val shapeChoices = arrayOf("Line", "Oval", "Rectangle")
    private val filled: JCheckBox

    // 'Second line' panel and widgets
    private val secondLine: JPanel

    private val useGradient: JCheckBox
    private val firstColor: JButton
    private val secondColor: JButton
    private val lineWidth: JLabel
    private val lineWidthInput: JTextField
    private val dashLength: JLabel
    private val dashLengthInput: JTextField
    private val dashed: JCheckBox

    // Initialize objects in drawing panel
    private var paint: Paint? = null
    private var stroke: Stroke? = null
    private var isFilled = false
    private var mouseLocation: Point? = null
    private val shapes = ArrayList<Shapes?>()
    private var currentShape: Shapes? = null

    // Initialize values for drawing
    private var color1 = Color.BLACK
    private var color2 = Color.WHITE
    private var lineWidthValue = 5
    private val dashLengthValue = floatArrayOf(5f)

    // Bottom status label
    private val statusLabel: JLabel

    /**
     * Private inner class for drawing panel
     */
    private inner class DrawPanel : JPanel() {
        /**
         * Redraws all components on screen
         *
         * @param g: Graphics object
         */
        public override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            val g2d = g as Graphics2D

            // Loop through and draw each shape in the shapes ArrayList
            for (shape in shapes) {
                shape!!.draw(g2d)
            }
        }

        /**
         * Private inner class for handling mouse events
         */
        private inner class MouseHandler : MouseAdapter(), MouseMotionListener {
            /**
             * Called when the mouse is first pressed down
             *
             * @param event: Event where the mouse is pressed
             */
            override fun mousePressed(event: MouseEvent) {
                // Create a paint variable, setting its value based on the colors/gradients
                paint = if (useGradient.isSelected) {
                    GradientPaint(0F, 0F, color1, 50F, 50F, color2, true)
                } else {
                    color1
                }

                // Stroke variable to set width and dashedness
                try { // Sanitizing lineWidthInput
                    lineWidthValue = lineWidthInput.text.toInt()
                } catch (e: IllegalArgumentException) { // invalid value from the textArea
                    lineWidthValue = 5 // sets back to simple defaults
                    lineWidthInput.text = "5"
                }
                try { // Sanitizing dashLengthInput
                    dashLengthValue[0] = dashLengthInput.text.toInt().toFloat()
                } catch (e: IllegalArgumentException) { // invalid value from the textArea
                    dashLengthValue[0] = 5F // sets back to simple defaults
                    dashLengthInput.text = "5"
                }

                stroke = if (dashed.isSelected) {
                    BasicStroke(
                        lineWidthValue.toFloat(),
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        10F,
                        dashLengthValue,
                        0F
                    )
                } else {
                    BasicStroke(lineWidthValue.toFloat(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
                }

                // Filled variable on whether or not it's filled
                isFilled = filled.isSelected
                // Mouse location for start and end point
                mouseLocation = event.point

                // Create the drawn object either line, rectangle, oval
                currentShape = if (Objects.requireNonNull(shapeSelect.selectedItem).toString() == "Rectangle") {
                    Rectangle(mouseLocation, mouseLocation, paint, stroke, isFilled)
                } else if (Objects.requireNonNull(shapeSelect.selectedItem).toString() == "Oval") {
                    Oval(mouseLocation, mouseLocation, paint, stroke, isFilled)
                } else { // Line
                    Line(mouseLocation, mouseLocation, paint, stroke)
                }


                // Add to ArrayList
                shapes.add(currentShape)
            }

            /**
             * Called when the mouse is released
             *
             * @param event: Event where the mouse is released
             */
            override fun mouseReleased(event: MouseEvent) {
                // setEndpoint() of current shape being drawn
                mouseLocation = event.point
                currentShape!!.endPoint = mouseLocation as Point

                // Update the bottom status label
                statusLabel.text = String.format("(%d, %d)", mouseLocation!!.x, mouseLocation!!.y)

                // Update ArrayList with the current shape setup
                shapes[shapes.size - 1] = currentShape
                repaint()

                // Remove currentShape value
                currentShape = null
            }

            /**
             * Called when the mouse is dragged
             *
             * @param event: Event where the mouse is dragged
             */
            override fun mouseDragged(event: MouseEvent) {
                // setEndpoint() of current shape being drawn
                mouseLocation = event.point
                currentShape!!.endPoint = mouseLocation as Point

                // Update the bottom status label
                statusLabel.text = String.format("(%d, %d)", mouseLocation!!.x, mouseLocation!!.y)

                // Update ArrayList with the current shape setup
                shapes[shapes.size - 1] = currentShape
                repaint()
            }

            /**
             * Called when the mouse is moved, but not necessarily clicked in
             *
             * @param event: Event where the mouse is moved
             */
            override fun mouseMoved(event: MouseEvent) {
                // Update status label and mouse position
                mouseLocation = event.point
                statusLabel.text = String.format("(%d, %d)", mouseLocation!!.x, mouseLocation!!.y)
            }
        }

        init {
            val mouseHandler = MouseHandler()
            addMouseListener(mouseHandler)
            addMouseMotionListener(mouseHandler)
        }
    }

    /**
     * Constructor for drawing frame
     */
    init {
        // Set frame layout
        layout = BorderLayout()

        // Construct first line panel
        firstLine = JPanel()
        firstLine.layout = FlowLayout()

        undo = JButton("Undo")
        clear = JButton("Clear")
        shapeLabel = JLabel("Shape:")
        shapeSelect = JComboBox(shapeChoices)
        filled = JCheckBox("Filled")

        firstLine.add(undo)
        firstLine.add(clear)
        firstLine.add(shapeLabel)
        firstLine.add(shapeSelect)
        firstLine.add(filled)

        // Construct second line panel
        secondLine = JPanel()
        secondLine.layout = FlowLayout()

        useGradient = JCheckBox("Use Gradient")
        firstColor = JButton("1st Color...")
        secondColor = JButton("2nd Color...")
        lineWidth = JLabel("Line Width:")
        lineWidthInput = JTextField("5", 2)
        dashLength = JLabel("Dash Length:")
        dashLengthInput = JTextField("5", 2)
        dashed = JCheckBox("Dashed")

        secondLine.add(useGradient)
        secondLine.add(firstColor)
        secondLine.add(secondColor)
        secondLine.add(lineWidth)
        secondLine.add(lineWidthInput)
        secondLine.add(dashLength)
        secondLine.add(dashLengthInput)
        secondLine.add(dashed)

        // Construct top panel
        topPanel = JPanel()
        topPanel.layout = BorderLayout()

        topPanel.add(firstLine, BorderLayout.NORTH)
        topPanel.add(secondLine, BorderLayout.CENTER)

        // Construct main JFrame
        add(topPanel, BorderLayout.NORTH)
        val drawPanel = DrawPanel()
        drawPanel.background = Color.WHITE
        add(drawPanel, BorderLayout.CENTER)
        statusLabel = JLabel("(0, 0)")
        add(statusLabel, BorderLayout.SOUTH)

        // Widget event handlers
        undo.addActionListener {
            if (shapes.size > 0) shapes.removeAt(shapes.size - 1) // If some value exists, remove it.
            repaint()
        }
        clear.addActionListener {
            shapes.clear()
            repaint()
        }
        firstColor.addActionListener {
            color1 = JColorChooser.showDialog(this@DrawingFrame, "Choose a color", color1)
            if (color1 == null) color1 = Color.BLACK
        }
        secondColor.addActionListener {
            color2 = JColorChooser.showDialog(this@DrawingFrame, "Choose a color", color2)
            if (color2 == null) color2 = Color.WHITE
        }
    }
}