package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  // Redundancia mínima. El total de plantas es la cantidad de
  // elementos dentro de la lista de plantas en la parcela
  var cantidadPlantas = 0

  fun superficie() = ancho * largo

  //Simplicidad y Redundancia mínima, se repite varias veces la formular ancho * largo cuando
  //Se podría llamar a superficie() para resolverlo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  fun plantar(planta: Planta) {
    // Simplicidad
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)

      // Redundancia mínima
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

  //simplicidad / YAGNI
  // En el enunciado se dice que la tierra no puede ser comprada ni vendida
  // por lo tanto tenemos un método que nunca se va a usar

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    // Errores de Mutaciónes controladas
    // pasable a una función de obtener la parecela elegida
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!


    // No usa el método de plantar de la parcela,
    // por lo cual evita la validación que posea
    // y accede directamente al atributo plantas.
    laElegida.plantas.add(planta)
  }
}
