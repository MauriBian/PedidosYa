package Transformers

import org.uqbar.arena.bindings.ValueTransformer
import Category

class EnumTransformer : ValueTransformer<Category,String>{
    override fun getModelType(): Class<Category> {
        return Category::class.java
    }

    override fun viewToModel(p0: String?): Category {
        if (p0 == "Bebida"){
            return Category.BEBIDA
        }else if (p0 == "Entrada"){
            return Category.ENTRADA
        }else if (p0 == "Postre"){
            return Category.POSTRE
        }else{
            return Category.PLATO_PRINCIPAL
        }
    }

    override fun modelToView(p0: Category?): String {
        if (p0 == Category.BEBIDA){
            return "Bebida"
        }else if (p0 == Category.ENTRADA){
            return "Entrada"
        }else if (p0 == Category.POSTRE){
            return "Postre"
        }else{
            return "Menu principal"
        }
    }

    override fun getViewType(): Class<String> {
        return String::class.java
    }


}
