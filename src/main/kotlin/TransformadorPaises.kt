import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI


object TransformadorPaises: ObjInter {

    lateinit var countries: RestCountriesAPI

    override fun paisConNombre(nombrePais: String): Pais {
        val country = countries.buscarPaisesPorNombre(nombrePais).first()
        var paisT = Pais(nombre = country.name, codigoAlfa3 = country.alpha3Code, poblacion = country.population.toInt(),
                        continente = country.region, bloquesRegionales = country.regionalBlocs!!.map { it.name } as MutableList<String>,
                        idiomasOficiales = country.languages.map { it.name } as MutableList<String>)

        if(country.area == null) {paisT.superficie = paisT.poblacion.toDouble() } else {paisT.superficie = country.area}

        if(country.currencies!!.isEmpty()) {paisT.codigoMonedaLocal = "USD"} else {paisT.codigoMonedaLocal = country.currencies!!.first().code}

        if(!country.borders.isNullOrEmpty()) {}

        return paisT
    }



    override fun sonLimitrofes(primerPais: String, segundoPais: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun necesitanTraduccion(primerPais: String, segundoPais: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun sonPotencialesAliados(primerPais: String, segundoPais: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun convieneIrDeCompras(primerPais: String, segundoPais: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun cuantoEquivaleLaMonedaEntre(
        primerPais: String,
        segundoPais: String,
        montoDelPrimerPais: Double,
    ): Double {
        TODO("Not yet implemented")
    }

    override fun codigosISODeLos5MasPoblados(): List<String> {
        TODO("Not yet implemented")
    }

    override fun continenteConMasPaisesPlurinacionales(): String {
        TODO("Not yet implemented")
    }

    override fun promedioDePoblacionDePaisesInsulares(): Int {
        TODO("Not yet implemented")
    }


    /*fun transformarColleccion() {
        var elemTransformados: MutableList<Pais> = mutableListOf()
        var pais = Pais()

        countries.todosLosPaises().forEach { nacion ->

            pais.nombre = nacion.name
            pais.codigoAlfa3 = nacion.alpha3Code
            pais.poblacion = nacion.population.toInt()
            pais.superficie = (nacion.area ?: nacion.population) as Double
            pais.continente = nacion.region
            pais.codigoMonedaLocal = if(nacion.currencies!!.isEmpty()) {"USD"} else ({
                nacion.currencies!![0]}).toString()
            pais.bloquesRegionales = nacion.regionalBlocs!!.map { it.name }.toMutableList()
            pais.idiomasOficiales = nacion.languages.map { it.name }.toMutableList()

            elemTransformados.add(pais)
            Observatorio.listaDePaises = elemTransformados
        }

    }*/
}