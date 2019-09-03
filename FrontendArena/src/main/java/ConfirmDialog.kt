import org.uqbar.arena.aop.windows.TransactionalDialog
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class ConfirmDialog(owner: WindowOwner, model: ApplicationModel) : TransactionalDialog<ApplicationModel>(owner, model) {
    private var message = ""

    override fun createFormPanel(mainPanel: Panel) {
        Label(mainPanel)
                .setText(message)

        val buttonsPanel = Panel(mainPanel)
        buttonsPanel.setLayout(ColumnLayout(2))

        Button(buttonsPanel)
                .setCaption("Aceptar")
                .onClick { accept() }
                .setWidth(100)

        Button(buttonsPanel)
                .setCaption("Cancelar")
                .onClick { cancel() }
                .setWidth(100)
    }

    fun setMessage(mes : String){
        message = mes
    }
}