import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder


class AdminPanelWindow(owner : WindowOwner, model : ApplicationModel): SimpleWindow<ApplicationModel>(owner,model) {
    override fun addActions(actionsPanel : Panel) { /* Actions Panel */ }
    override fun createFormPanel(mainPanel : Panel) {
        this.title = "Login"

        Label(mainPanel).setText("Admin Panel").setFontSize(20)
    }


}