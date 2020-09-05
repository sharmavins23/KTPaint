import javax.swing.JFrame

/**
 * Paint application entry point
 *
 * @author sharmavins23
 */
object KTPaint {
    /**
     * Entry point function for command line
     *
     * @param args: Command line arguments
     */
    @JvmStatic
    fun main(args: Array<String>) {
        // Creating our frame object
        val frame = DrawingFrame()

        // Frame display settings
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(650, 500)
        frame.isVisible = true
    }
}