import ar.edu.unahur.obj2.impostoresPaises.api.Country
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI

object TransformadorPaises {

    lateinit var countries: RestCountriesAPI

    fun transformarColleccion() {
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

    }
}