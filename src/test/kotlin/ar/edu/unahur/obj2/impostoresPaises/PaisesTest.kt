package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec

class PaisesTest: DescribeSpec(){

    val argentina = Tp_ws.Pais("Argentina", "ARG", 45380000, 2780000.0, "America", "SRA", 128.22)

    val brasil = Tp_ws.Pais("Brasil", "BRA", 212600000, 851600000.0, "America", "BRL", 5.40)

    val uruguay = Tp_ws.Pais("Uruguay", "URY", 3474000, 176215.0, "America", "UYU", 41.09)

}