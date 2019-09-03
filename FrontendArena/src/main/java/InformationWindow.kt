import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner




class InformationWindow : SimpleWindow<ApplicationModel> {
    var titleView : String = ""
    var itemsListView :  MutableList<String> = mutableListOf()
    var resultView : String = ""

    constructor (owner: WindowOwner, model: ApplicationModel) : super(owner, model){
    }

    override fun addActions(actionsPanel: Panel) { /* Actions Panel */

    }

    fun setValues(title :String, items : MutableList<String>, result: String ){
        this.titleView = title
        this.itemsListView = items
        this.resultView = result
    }

    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel).setText(this.titleView).setFontSize(20)
        Label(mainPanel).setText("")

        for(item in this.itemsListView){
            Label(mainPanel).setText("* " + item).alignLeft()
        }

        Label(mainPanel).setText(this.resultView).setFontSize(20)

        val right = Panel(mainPanel)
        right.setLayout(ColumnLayout(4))
        Label(right).setText("")
        Label(right).setText("")
        Label(right).setText("")

        Button(right).setCaption("Cerrar").onClick {
            this.close()
        }.setWidth(50)
    }


}