object Observatorio {

    var listaDePaises = mutableListOf<Tp_ws.PaisInter>()

    fun paisConNombre(nombrePais: String): Tp_ws.PaisInter {
        return if (!listaDePaises.any{it.nombre == nombrePais}) {
            error("No existe el pais")
        } else {
            listaDePaises.find { it.nombre == nombrePais }!!
        }
    }

    fun sonLimitrofes(primerPais:String, segundoPais: String): Boolean {
        return paisConNombre(primerPais).esLimitrofeDe(paisConNombre(segundoPais))
    }


}