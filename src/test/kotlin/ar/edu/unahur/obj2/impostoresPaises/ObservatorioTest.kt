package ar.edu.unahur.obj2.impostoresPaises

import Observatorio
import Pais
import TransformadorPaises
import ar.edu.unahur.obj2.impostoresPaises.api.RestCountriesAPI
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec({

    Observatorio.listaDePaises.clear()
    //TransformadorPaises.transformarColleccion()


    val argentina = Pais("Argentina", "ARG", 45380000, 2780000.0, "America", "SRA", 128.22)

    val brasil = Pais("Brasil", "BRA", 212600000, 851600000.0, "America", "BRL", 5.40)

    val uruguay = Pais("Uruguay", "URY", 3474000, 176215.0, "America", "UYU", 41.09)

    val bolivia = Pais("Bolivia", "BOL", 11670000, 1099000000.0, "America", "BOB", 6.88)

    val cuba = Pais("Cuba", "CUB", 11330000, 113860.0, "America", "CUP", 23.96)

    val japon = Pais("Japon", "JPN", 125800000, 377975.0, "Asia", "JPN", 136.13)



    //Idiomas Paises
    argentina.idiomasOficiales.add("Español")
    brasil.idiomasOficiales.add("Portugues")
    uruguay.idiomasOficiales.add("Español")
    bolivia.idiomasOficiales.add("Español")
    bolivia.idiomasOficiales.add("Aimara")
    bolivia.idiomasOficiales.add("guaraní")
    cuba.idiomasOficiales.add("Español")
    japon.idiomasOficiales.add("Japones")

    //Paises Limitrofes
    argentina.paisesLimitrofes.add(uruguay)
    argentina.paisesLimitrofes.add(bolivia)
    brasil.paisesLimitrofes.add(uruguay)
    uruguay.paisesLimitrofes.add(argentina)
    uruguay.paisesLimitrofes.add(brasil)
    bolivia.paisesLimitrofes.add(argentina)
    bolivia.paisesLimitrofes.add(brasil)

    //Bloques regionales
    argentina.bloquesRegionales.add("OEA")
    brasil.bloquesRegionales.add("OEA")
    uruguay.bloquesRegionales.add("OEA")
    bolivia.bloquesRegionales.add("OEA")
    cuba.bloquesRegionales.add("UNASUR")
    japon.bloquesRegionales.add("G7")

    Observatorio.listaDePaises.add(argentina)
    Observatorio.listaDePaises.add(brasil)
    Observatorio.listaDePaises.add(uruguay)
    Observatorio.listaDePaises.add(bolivia)
    Observatorio.listaDePaises.add(cuba)
    Observatorio.listaDePaises.add(japon)



    describe("Observatorio tests") {
        it("Pais con nombre") {
            Observatorio.paisConNombre("Brasil").shouldBe(brasil)
            Observatorio.paisConNombre("Argentina").shouldBe(argentina)
            Observatorio.paisConNombre("Uruguay").shouldBe(uruguay)
        }

        it("Son Limitrofes") {
            Observatorio.sonLimitrofes("Argentina", "Uruguay").shouldBeTrue()
            Observatorio.sonLimitrofes("Argentina", "Brasil").shouldBeFalse()
            Observatorio.sonLimitrofes("Argentina", "Cuba").shouldBeFalse()
        }

        it("Necesitan traduccion") {
            Observatorio.necesitanTraduccion("Argentina", "Uruguay").shouldBeFalse()
            Observatorio.necesitanTraduccion("Argentina", "Brasil").shouldBeTrue()
            Observatorio.necesitanTraduccion("Argentina", "Bolivia").shouldBeFalse()
        }

        it("Son potenciales aliados") {
            Observatorio.sonPotencialesAliados("Argentina", "Uruguay").shouldBeTrue()
            Observatorio.sonPotencialesAliados("Argentina", "Cuba").shouldBeFalse()
            Observatorio.sonPotencialesAliados("Argentina", "Brasil").shouldBeFalse()
        }

        it("Conviene ir de compras") {
            Observatorio.convieneIrDeCompras("Argentina", "Brasil").shouldBeFalse()
            Observatorio.convieneIrDeCompras("Brasil", "Argentina").shouldBeTrue()
        }

        it("Equivalencias entre monedas") {
            Observatorio.cuantoEquivaleLaMonedaEntre("Argentina", "Brasil", 100.0).shouldBe(4.21)
            Observatorio.cuantoEquivaleLaMonedaEntre("Brasil", "Argentina", 100.0).shouldBe(2374.44)

        }

        it("Codigos ISO de los paises mas poblados") {
            Observatorio.codigosISODeLos5MasPoblados().size.shouldBe(5)
            Observatorio.codigosISODeLos5MasPoblados().first().shouldBe("BRA")
            Observatorio.codigosISODeLos5MasPoblados().last().shouldBe("CUB")
        }

        it("Contienente con mas paises plurinacionales") {
            Observatorio.continenteConMasPaisesPlurinacionales().shouldBe("America")
        }

        it("Promedio de poblacion de paises insulares") {
            Observatorio.promedioDePoblacionDePaisesInsulares().shouldBe(68565000)

        }
    }
})