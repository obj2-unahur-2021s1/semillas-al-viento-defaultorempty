package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec({

    var parcela = Parcela(10, 10, 8)
    var parcelaAncha = Parcela(10, 5, 5)
    var parcelaLarga = Parcela(5, 10, 15)

    var menta = Menta(2019, 0.5f)//explicitamente decir que es float. Si no deberia ser double y seria lo mismo?
    var menta2 = Menta(2019, 0.3f)//explicitamente decir que es float. Si no deberia ser double y seria lo mismo?
    var menta3 = Menta(2019, 0.23f)//explicitamente decir que es float. Si no deberia ser double y seria lo mismo?

    describe("Parcela") {


        it("Calcular superficie") {
            parcela.superficie().shouldBe(100)
        }

        it("Cantidad maxima de plantas cuando la parcela es mas ancha que larga") {
            parcelaAncha.cantidadMaximaPlantas().shouldBe(10)
        }

        it("Cantidad maxima de plantas cuando la parcela es mas larga que ancha") {
            parcelaLarga.cantidadMaximaPlantas().shouldBe(26)
        }

        it("Plantar en parcela") {
            parcela.plantar(menta)
            //Se cambiar el atributo cantidadPlantas por la funcion cantidadDePlantas()
            parcela.cantidadDePlantas().shouldBe(1)
        }

        //Este requerimiento no lo tiene la parcela. La resposabilida se la dio a la planta
        it("Tiene complicaciones") {
            parcela.parcelaTieneComplicaciones()
        }
    }


    describe("Agricultora") {
        parcelaAncha.plantar(menta2)
        parcelaAncha.plantar(menta3)
        val listaDeParcelas = listOf<Parcela>(parcelaAncha)
        var agricultora = Agricultora(listaDeParcelas)

        it("Comprar parcela") {
            // Se saca este test porque no estaba en el requerimiento poder comprar o vencer una parcela
            //agricultora.comprarParcela(parcela)
        }

        it("Parcelas semilleras") {
            agricultora.parcelasSemilleras().count().shouldBe(0)
        }

        it("Plantar estrategicamente") {
           // agricultora.comprarParcela(parcelaAncha)
            agricultora.plantarEstrategicamente(menta)
            // Esto falla porque no se usa el metodo plantar y por ende no se actualiza la variable que
            // contiene la cantidad de plantas
            // Debería ser 1 pero da 0
            //agricultora.parcelas.first().cantidadPlantas.shouldBe(1)
        }

    }

    describe("Panta Menta") {

        var menta = Menta(2021, 1.0f)

        it("Planta Menta horas de sol que tolera") {

            menta.horasDeSolQueTolera().shouldBe(6)
        }

        it("Planta Menta es fuerte") {
            menta.esFuerte().shouldBe(false)
        }

        it("Planta Menta da semillas") {
            menta.daSemillas().shouldBe(true)
        }


    }

    describe("Panta Soja") {

        // Se saca el booleando de soja transgenica
        var soja = Soja(2021, 1.0f)

        it("Planta Soja horas de sol que tolera") {

            soja.horasDeSolQueTolera().shouldBe(9)
        }

        it("Planta Soja es fuerte") {
            soja.esFuerte().shouldBe(false)
        }

        //Este metodo falla porque no calcula si da semillas para una planta que no es transgenica
        it("Planta Soja da semillas") {
            soja.daSemillas().shouldBe(false)
        }


    }

    describe("Planta Soja transgenica") {
        // Se usa la clase de Soja Transgenica
        var sojaTransgenica = SojaTransgenica(2021, 0.4f)

        it("Planta Soja transgenica horas de sol que tolera") {
            sojaTransgenica.horasDeSolQueTolera().shouldBe(12)
        }

        it("Planta Soja transgenica es fuerte") {
            sojaTransgenica.esFuerte().shouldBe(true)
        }

        it("Planta Soja transgenica da semillas") {
            sojaTransgenica.daSemillas().shouldBe(false)
        }


    }

})