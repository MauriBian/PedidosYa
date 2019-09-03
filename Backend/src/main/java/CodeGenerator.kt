object CodeGenerator {
    var lastCode = 0

    fun newCode() : Int{
        lastCode++
        return lastCode
    }
}