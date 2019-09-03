import exceptions.ExceptionSignup
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.ControlBuilder
import java.awt.Color



class LoginWindow : SimpleWindow<ApplicationModel> {
    constructor (owner: WindowOwner, model: ApplicationModel) : super(owner, model)

    override fun addActions(actionsPanel: Panel) { /* Actions Panel */
    }

    override fun createFormPanel(mainPanel: Panel) {
        this.title = "Login"

        Label(mainPanel).setText("Bienvenidos a Planeta YA").setFontSize(20)


        createLabelAndTextBox("User:", "user", mainPanel)
        createLabelAndPasswordField("Password:", "password", mainPanel)

        Button(mainPanel).setCaption("LogIn").onClick {
            try {
                modelObject.autenticar()
                this.close()
                MainWindow(owner, modelObject).open()


            } catch (e: ExceptionSignup) {
                throw UserException(e.message)
            }
        }

    }

    fun createLabelAndTextBox(labelText: String, bindProp: String, mainPanel: Panel) {
        val panelGrid: Panel = Panel(mainPanel)
        panelGrid.setLayout(HorizontalLayout())
        Label(panelGrid).setText(labelText).setWidth(80).alignRight()
        TextBox(panelGrid).setWidth(200).bindValueToProperty<String, ControlBuilder>(bindProp)
    }

    fun createLabelAndPasswordField(labelText: String,bindProp: String,mainPanel: Panel){
        val panelGrid: Panel = Panel(mainPanel)
        panelGrid.setLayout(HorizontalLayout())
        Label(panelGrid).setText(labelText).setWidth(80).alignRight()
        PasswordField(panelGrid).setWidth(200).bindValueToProperty<String, ControlBuilder>(bindProp)
    }


}