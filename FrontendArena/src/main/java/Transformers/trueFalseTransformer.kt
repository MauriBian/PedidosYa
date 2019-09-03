package Transformers

import org.apache.commons.collections15.Transformer
import org.uqbar.arena.bindings.ValueTransformer

class TrueFalseTransformer : ValueTransformer<Boolean, String>, Transformer<Any, String> {
    override fun transform(p0: Any?): String {
        when (p0){
            true -> return "✔"
            false -> return "✖"
            else -> { return ""}
        }

    }

    override fun getModelType(): Class<Boolean> = Boolean::class.java

    override fun viewToModel(p0: String?): Boolean {
       return p0 == "✔"
    }

    override fun modelToView(p0: Boolean): String {
        if (p0) {
            return "✔"
        }else{
            return "✖"
        }
    }

    override fun getViewType(): Class<String> = String::class.java

}
